package com.qingcheng.controller;


import com.alibaba.dubbo.config.annotation.Reference;
import com.qingcheng.pojo.skill.SeckillGoods;
import com.qingcheng.service.seckill.SeckillGoodsService;
import com.qingcheng.util.DateUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/seckill/goods")
public class SeckillGoodsController {

    @Reference
    private SeckillGoodsService seckillGoodsService;

    /**
     * 查询时间菜单
     * @return
     */
    @RequestMapping("/menus")
    public List<Date> loadMenus() {
        return DateUtil.getDateMenus();
    }

    @GetMapping("/list")
    public List<SeckillGoods> list(String time) {
        List<SeckillGoods> list = seckillGoodsService.list(time);
        return list;
    }

    /**
     * 根据商品ID查询商品详情
     */
    @GetMapping("/one")
    public SeckillGoods one(String time, Long id) {
        return seckillGoodsService.one(time, id);
    }

}
