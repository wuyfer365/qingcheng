package com.qingcheng.service.impl;

import com.alibaba.dubbo.config.annotation.Reference;
import com.qingcheng.dao.OrderItemMapper;
import com.qingcheng.pojo.order.Order;
import com.qingcheng.pojo.order.OrderItem;
import com.qingcheng.service.goods.SkuService;
import com.qingcheng.service.order.OrderService;
import com.qingcheng.service.pay.WeixinPayService;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import tk.mybatis.mapper.entity.Example;

import java.util.List;
import java.util.Map;

public class NormalOrderMessageListener implements MessageListener{

    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private WeixinPayService weixinPayService;
    @Autowired
    private OrderService orderService;
    @Autowired
    private OrderItemMapper orderItemMapper;

    @Reference
    private SkuService skuService;
    /**
     * 消息监听
     * @param message
     */
    @Override
    public void onMessage(Message message) {
        String orderId = new String( message.getBody());
        System.out.println("监听到的消息："+orderId);
        rollbackOrder(orderId);
    }

    /**
     * 订单回滚
     * @param orderId
     */
    public void rollbackOrder(String orderId) {
        if (orderId == null) {
            return;
        }

        //查本地订单表状态
        Order order = orderService.findById(orderId);
        if (order == null) {
            return;
        }
        if("0".equals(order.getPayStatus())){//未支付查询微信平台状态
            Map map = weixinPayService.queryPayStatus(orderId);
            Object return_code = map.get("return_code");
            if ("SUCCESS".equals(return_code)) {
                Object result_code = map.get("result_code");
                if ("SUCCESS".equals(result_code)) {
                    String trade_state = (String) map.get("trade_state");
                    if ("NOTPAY".equals(trade_state)) {//未支付调用关闭订单
                        weixinPayService.closePay(orderId);
                        order.setOrderStatus("4");//4:已关闭
                        orderService.update(order);
                        //回滚库存
                        Example example = new Example(OrderItem.class);
                        Example.Criteria criteria = example.createCriteria();
                        criteria.andEqualTo("orderId", orderId);
                        List<OrderItem> orderItemList = orderItemMapper.selectByExample(example);
                        skuService.backStock(orderItemList);//回滚库存

                    } else if ("SUCCESS".equals(trade_state)) {//支付成功，修改订单状态并记录
                        order.setOrderStatus("3");//4:已关闭
                        order.setPayStatus("1");
                        orderService.update(order);

                        //记录订单日志
                    }
                }else if("FAIL".equals(result_code)){
                    String err_code = (String) map.get("err_code");
                    if ("ORDERNOTEXIST".equals(err_code)) {
                        weixinPayService.closePay(orderId);
                        order.setOrderStatus("4");//4:已关闭
                        orderService.update(order);
                        //回滚库存
                        Example example = new Example(OrderItem.class);
                        Example.Criteria criteria = example.createCriteria();
                        criteria.andEqualTo("orderId", orderId);
                        List<OrderItem> orderItemList = orderItemMapper.selectByExample(example);
                        skuService.backStock(orderItemList);//回滚库存
                    }
                    return;
                }
            } else {
                return;
            }
        }

    }
}
