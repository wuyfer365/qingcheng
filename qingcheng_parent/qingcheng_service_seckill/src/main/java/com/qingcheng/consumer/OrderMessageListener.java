package com.qingcheng.consumer;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSON;
import com.qingcheng.dao.SeckillGoodsMapper;
import com.qingcheng.pojo.seckill.SeckillGoods;
import com.qingcheng.pojo.seckill.SeckillOrder;
import com.qingcheng.service.pay.WeixinPayService;
import com.qingcheng.util.SeckillStatus;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.Map;

public class OrderMessageListener implements MessageListener{

    @Autowired
    private RedisTemplate redisTemplate;
    @Reference
    private WeixinPayService weixinPayService;
    @Autowired
    private SeckillGoodsMapper seckillGoodsMapper;
    /**
     * 消息监听
     * @param message
     */
    @Override
    public void onMessage(Message message) {
        String content = new String( message.getBody());
        System.out.println("监听到的消息："+content);
        rollbackOrder(JSON.parseObject(content,SeckillStatus.class));
    }

    /**
     * 订单回滚
     * @param seckillStatus
     */
    public void rollbackOrder(SeckillStatus seckillStatus) {
        if (seckillStatus == null) {
            return;
        }
        //redis中是否有对应订单
        SeckillOrder seckillOrder = (SeckillOrder) redisTemplate.boundHashOps("SeckillOrder").get(seckillStatus.getUsername());

        //存在回滚
        if (seckillOrder != null) {
            //关闭微信支付
            Map<String,String> map = weixinPayService.closePay(seckillStatus.getOrderId().toString());
            if (map.get("return_code").equals("SUCCESS")&&map.get("result_code").equals("SUCCESS")) {
                //删除用户订单
                redisTemplate.boundHashOps("SeckillOrder").delete(seckillOrder.getUserId());
                //查询商品数据
                SeckillGoods goods = (SeckillGoods) redisTemplate.boundHashOps("SeckillGoods_" + seckillStatus.getTime()).get(seckillStatus.getGoodsId());
                if (goods == null) {
                    goods = seckillGoodsMapper.selectByPrimaryKey(seckillStatus.getGoodsId());
                }
                //递增库存
                Long seckillGoodsCount = redisTemplate.boundHashOps("SeckillGoodsCount").increment(seckillStatus.getGoodsId(), 1);
                goods.setStockCount(seckillGoodsCount.intValue());
//        同步数据到redis
                redisTemplate.boundHashOps("SeckillGoods_" + seckillStatus.getTime()).put(seckillStatus.getGoodsId(), goods);
                redisTemplate.boundListOps("SeckillGoodsCountList_" + seckillStatus.getGoodsId()).leftPush(seckillStatus.getGoodsId());

//        清理抢单排队信息
                redisTemplate.boundHashOps("UserQueueCount").delete(seckillStatus.getUsername());
                redisTemplate.boundHashOps("UserQueueStatus").delete(seckillStatus.getUsername());


            }

        }

    }
}
