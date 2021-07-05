package com.qingcheng.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.qingcheng.dao.SkuMapper;
import com.qingcheng.dao.StockBackMapper;
import com.qingcheng.pojo.goods.StockBack;
import com.qingcheng.pojo.order.OrderItem;
import com.qingcheng.service.goods.StockBackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service(interfaceClass = StockBackService.class)
public class StockBackServiceImpl implements StockBackService {
    @Autowired
    private StockBackMapper stockBackMapper;

    @Transactional
    public void addList(List<OrderItem> orderItemList) {
        for (OrderItem orderItem : orderItemList) {
            StockBack stockBack=new StockBack();
            stockBack.setOrderId(orderItem.getOrderId());
            stockBack.setSkuId(orderItem.getSkuId());
            stockBack.setStatus("0");
            stockBack.setNum(orderItem.getNum());
            stockBack.setCreateTime(new Date());
            stockBackMapper.insert(stockBack);
        }
    }
    @Autowired
    private SkuMapper skuMapper;


    @Transactional
    public void doBack() {
        System.out.println("stock back task begins...");
        StockBack stockBack = new StockBack();
        stockBack.setStatus("0");
        List<StockBack> stockBackList = stockBackMapper.select(stockBack);
        for (StockBack back : stockBackList) {
            //添加库存
            skuMapper.deductionStock(back.getSkuId(),-back.getNum());

            //减少销量
            skuMapper.addSaleNum(back.getSkuId(),-back.getNum());

            back.setStatus("1");
            stockBackMapper.updateByPrimaryKeySelective(back);
        }
        System.out.println("stock back task ends...");
    }
}
