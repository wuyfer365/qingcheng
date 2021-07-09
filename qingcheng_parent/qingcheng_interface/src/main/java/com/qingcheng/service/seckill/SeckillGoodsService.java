package com.qingcheng.service.seckill;

import com.qingcheng.pojo.skill.SeckillGoods;

import java.util.List;

public interface SeckillGoodsService {

    /**
     * 根据时间区间查询秒杀商品列表
     */
    List<SeckillGoods> list(String time);

    /**
     * 根据商品ID查询商品详情
     */
    SeckillGoods one(String time, Long id);
}
