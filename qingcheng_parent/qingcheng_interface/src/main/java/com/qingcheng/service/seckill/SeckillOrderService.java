package com.qingcheng.service.seckill;

import com.qingcheng.pojo.seckill.SeckillOrder;
import com.qingcheng.util.SeckillStatus;

public interface SeckillOrderService {

    /**
     * 下单
     * @param id
     * @param time
     * @param name
     * @return
     */
    Boolean add(Long id, String time, String name);

    SeckillStatus queryStatus(String username);


    void updateStatus(String outtradeno,String username,String transaction);

    /**
     * 根据用户名查询订单
     * @param name
     * @return
     */
    SeckillOrder queryByUserName(String name);

}

