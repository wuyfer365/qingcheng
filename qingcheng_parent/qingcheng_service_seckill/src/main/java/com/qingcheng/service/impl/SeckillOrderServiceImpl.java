package com.qingcheng.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.qingcheng.dao.SeckillGoodsMapper;
import com.qingcheng.pojo.skill.SeckillGoods;
import com.qingcheng.pojo.skill.SeckillOrder;
import com.qingcheng.service.seckill.SeckillOrderService;
import com.qingcheng.util.IdWorker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.Date;

@Service
public class SeckillOrderServiceImpl implements SeckillOrderService {
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private SeckillGoodsMapper seckillGoodsMapper;
    @Autowired
    private IdWorker idWorker;

    @Override
    public Boolean add(Long id, String time, String name) {

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
            goods.setStockCount(goods.getStockCount()-1);
            if (goods.getStockCount() <= 0) {
                //清理缓存
                seckillGoodsMapper.updateByPrimaryKeySelective(goods);
                redisTemplate.boundHashOps("SeckillGoods_" + time).delete(id);
            }else {
                redisTemplate.boundHashOps("SeckillGoods_"+time).put(id,goods);
            }
            return true;
        }
        return null;
    }
}
