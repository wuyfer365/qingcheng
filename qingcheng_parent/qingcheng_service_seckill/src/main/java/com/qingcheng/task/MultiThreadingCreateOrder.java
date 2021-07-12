package com.qingcheng.task;

import com.alibaba.fastjson.JSON;
import com.qingcheng.dao.SeckillGoodsMapper;
import com.qingcheng.pojo.seckill.SeckillGoods;
import com.qingcheng.pojo.seckill.SeckillOrder;
import com.qingcheng.util.IdWorker;
import com.qingcheng.util.SeckillStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import java.util.Date;

/**
 * 异步操作类
 */
@Component
public class MultiThreadingCreateOrder {
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private SeckillGoodsMapper seckillGoodsMapper;
    @Autowired
    private IdWorker idWorker;
    /**
     * 异步操作方法
     */
    @Async
    public void CreateOrder() {
        try {
            System.out.println("prepare exec");
            Thread.sleep(10000);
            SeckillStatus seckillStatus = (SeckillStatus) redisTemplate.boundListOps("SeckillOrderQueue").rightPop();


            String name = seckillStatus.getUsername();
            String time = seckillStatus.getTime();
            Long id=seckillStatus.getGoodsId();

            Object sid = redisTemplate.boundListOps("SeckillGoodsCountList_" + id).rightPop();
            if (sid == null) {
                clearQueue(seckillStatus);
                return;
            }
            SeckillGoods goods = (SeckillGoods) redisTemplate.boundHashOps("SeckillGoods_" + time).get(id);
            if (goods != null && goods.getStockCount() > 0) {
                SeckillOrder seckillOrder=new SeckillOrder();
                seckillOrder.setId(idWorker.nextId());
                seckillOrder.setMoney(goods.getCostPrice());
                seckillOrder.setUserId(name);
                seckillOrder.setSellerId(goods.getSellerId());
                seckillOrder.setCreateTime(new Date());
                seckillOrder.setStatus("0");

                redisTemplate.boundHashOps("SeckillOrder").put(name,seckillOrder);
                //削减库存
                Long surplusCount = redisTemplate.boundHashOps("SeckillGoodsCount").increment(goods.getId(), -1);
                goods.setStockCount(surplusCount.intValue());
                if (surplusCount <= 0) {
                    //清理缓存
                    seckillGoodsMapper.updateByPrimaryKeySelective(goods);
                    redisTemplate.boundHashOps("SeckillGoods_" + time).delete(id);
                }else {
                    redisTemplate.boundHashOps("SeckillGoods_"+time).put(id,goods);
                }

                //下单完成变更下单状态
                seckillStatus.setOrderId(seckillOrder.getId());
                seckillStatus.setMoney(seckillOrder.getMoney().floatValue());
                seckillStatus.setStatus(2);//抢单成功待支付
                redisTemplate.boundHashOps("UserQueueStatus").put(name,seckillStatus);

                sendDelayMessage(seckillStatus);
            }
            System.out.println("is executing...");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * 清理用户排队信息
     * @param seckillStatus
     */
    private void clearQueue(SeckillStatus seckillStatus) {
        //清理重复排队标识
        redisTemplate.boundHashOps("UserQueueCount").delete(seckillStatus.getUsername());
        //清理排队存储信息
        redisTemplate.boundHashOps("UserQueueStatus").delete(seckillStatus.getUsername());
    }

    @Autowired
    private RabbitTemplate rabbitTemplate;
    /***
     * 延时消息发送
     * @param seckillStatus
     */
    public void sendDelayMessage(SeckillStatus seckillStatus){
        rabbitTemplate.convertAndSend(
                "exchange.delay.order.begin",
                "delay",
                JSON.toJSONString(seckillStatus),       //发送数据
                new MessagePostProcessor() {
                    @Override
                    public Message postProcessMessage(Message message) throws AmqpException {
                        //消息有效期30分钟
                        message.getMessageProperties().setExpiration(String.valueOf(60000));
                        return message;
                    }
                });
    }
}
