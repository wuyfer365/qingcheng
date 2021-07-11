package com.qingcheng.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.qingcheng.dao.SeckillOrderMapper;
import com.qingcheng.pojo.seckill.SeckillOrder;
import com.qingcheng.service.seckill.SeckillOrderService;
import com.qingcheng.task.MultiThreadingCreateOrder;
import com.qingcheng.util.SeckillStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.Date;

@Service
public class SeckillOrderServiceImpl implements SeckillOrderService {
    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private SeckillOrderMapper seckillOrderMapper;
    @Autowired
    private MultiThreadingCreateOrder multiThreadingCreateOrder;
    @Override
    public Boolean add(Long id, String time, String name) {

        Long userQueueCount = redisTemplate.boundHashOps("UserQueueCount").increment(name, 1);
        if (userQueueCount > 1) {
            //100:重复排队
            System.out.println("repeat buy...");
            throw new RuntimeException("100");
        }

        //减少无效排队
        Long size = redisTemplate.boundListOps("SeckillGoodsCountList_" + id).size();
        if (size <= 0) {
            throw new RuntimeException("101");//没有库存了
        }
        SeckillStatus seckillStatus = new SeckillStatus(name,new Date(),1,id,time);
        redisTemplate.boundListOps("SeckillOrderQueue").leftPush(seckillStatus);
        redisTemplate.boundHashOps("UserQueueStatus").put(name,seckillStatus);
        multiThreadingCreateOrder.CreateOrder();
        System.out.println("other program is running");

        return true;
    }

    /**
     * 用户抢单状态查询
     * @param username
     * @return
     */
    @Override
    public SeckillStatus queryStatus(String username) {
        return (SeckillStatus) redisTemplate.boundHashOps("UserQueueStatus").get(username);
    }


    /**
     * 修改订单状态
     * @param outtradeno
     * @param username
     * @param transaction
     */
    @Override
    public void updateStatus(String outtradeno, String username, String transaction) {
        //根据用户名查询订单数据
        SeckillOrder seckillOrder = (SeckillOrder) redisTemplate.boundHashOps("SeckillOrder").get(username);
        if (seckillOrder != null) {

            //修改订单-同步mysql
            seckillOrder.setPayTime(new Date());
            seckillOrder.setStatus("1");//已支付
            seckillOrderMapper.insertSelective(seckillOrder);
            //清理用户排队信息
            redisTemplate.boundHashOps("SeckillOrder").delete(username);
            //清理重复排队标识
            redisTemplate.boundHashOps("UserQueueCount").delete(username);
            //清理排队存储信息
            redisTemplate.boundHashOps("UserQueueStatus").delete(username);
        }
    }

    @Override
    public SeckillOrder queryByUserName(String name) {
        return (SeckillOrder)redisTemplate.boundHashOps("SeckillOrder").get(name);
    }
}
