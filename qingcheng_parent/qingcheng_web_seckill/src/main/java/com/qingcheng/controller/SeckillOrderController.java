package com.qingcheng.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.qingcheng.entity.Result;
import com.qingcheng.service.seckill.SeckillOrderService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/seckill/order")
public class SeckillOrderController {

    @Reference
    private SeckillOrderService seckillOrderService;

    @GetMapping("/add")
    public Result add(Long id,String time) {
        String name = SecurityContextHolder.getContext().getAuthentication().getName();

        if (name.equals("anonymousUser")) {
            return new Result(403, "未登录请先登录");
        }
        try {
            Boolean b = seckillOrderService.add(id, time, name);

            if (b) {
                return new Result(0, "抢单成功！");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(1,"抢单失败！");
        }

        return new Result(1,"抢单失败！");
    }
}
