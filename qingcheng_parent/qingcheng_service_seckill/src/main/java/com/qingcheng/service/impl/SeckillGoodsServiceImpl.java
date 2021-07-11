package com.qingcheng.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.qingcheng.pojo.seckill.SeckillGoods;
import com.qingcheng.service.seckill.SeckillGoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.List;

@Service
public class SeckillGoodsServiceImpl implements SeckillGoodsService {


    @Autowired
    private RedisTemplate redisTemplate;
    /**
     * 根据时间区间查询秒杀商品列表
     * @param time
     * @return
     */
    @Override
    public List<SeckillGoods> list(String time) {
        String key="SeckillGoods_"+time;
        List values = redisTemplate.boundHashOps(key).values();
        System.out.println(values);
        return values;
    }

    @Override
    public SeckillGoods one(String time, Long id) {
        return (SeckillGoods) redisTemplate.boundHashOps("SeckillGoods_"+time).get(id);
    }
}
