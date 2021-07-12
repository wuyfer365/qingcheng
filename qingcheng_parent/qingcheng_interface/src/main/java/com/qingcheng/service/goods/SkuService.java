package com.qingcheng.service.goods;
import com.qingcheng.entity.PageResult;
import com.qingcheng.pojo.goods.Sku;
import com.qingcheng.pojo.order.OrderItem;

import java.util.*;

/**
 * sku业务逻辑层
 */
public interface SkuService {


    public List<Sku> findAll();


    public PageResult<Sku> findPage(int page, int size);


    public List<Sku> findList(Map<String,Object> searchMap);


    public PageResult<Sku> findPage(Map<String,Object> searchMap,int page, int size);


    public Sku findById(String id);

    public void add(Sku sku);


    public void update(Sku sku);


    public void delete(String id);

    public void saveAllPriceToRedis();

    /**
     * 根据sku id查询价格
     * @param id
     * @return
     */
    public Integer findPrice(String id);

    public void savePriceToRedisById(String id,Integer price);

    public void deletePriceFromRedisById(String id);

    /**
     * 批量扣除库存
     * @param orderItemList
     */
    public boolean deductionStock(List<OrderItem> orderItemList);
    public boolean backStock(List<OrderItem> orderItemList);
}
