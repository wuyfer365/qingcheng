package com.qingcheng.service.impl;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.dubbo.config.annotation.Service;
import com.qingcheng.pojo.goods.Category;
import com.qingcheng.pojo.goods.Sku;
import com.qingcheng.pojo.order.OrderItem;
import com.qingcheng.service.goods.CategoryService;
import com.qingcheng.service.goods.SkuService;
import com.qingcheng.service.order.CartService;
import com.qingcheng.service.order.PreferentialService;
import com.qingcheng.util.CacheKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class CartServiceImpl implements CartService {
    @Autowired
    private RedisTemplate redisTemplate;
    @Override
    public List<Map<String, Object>> findCartList(String username) {
        System.out.println("get cart from redis:"+username);
        List<Map<String, Object>> cartList=(List<Map<String, Object>>)redisTemplate.boundHashOps(CacheKey.CART_LIST).get(username);
        if (cartList == null) {
            cartList = new ArrayList<Map<String, Object>>();
        }
        return cartList;
    }

    @Reference
    private SkuService skuService;
    @Reference
    private CategoryService categoryService;
    @Override
    public void addItem(String username, String skuId, Integer num) {
        List<Map<String, Object>> cartList = findCartList(username);
        boolean flag=false;
        for (Map<String, Object> map : cartList) {
            OrderItem orderItem = (OrderItem) map.get("item");
            if (orderItem.getSkuId().equals(skuId)) {//购物车中有该商品
                if (orderItem.getNum() <= 0) {
                    cartList.remove(map);
                    break;
                }
                int weight=orderItem.getWeight() / orderItem.getNum();//单个商品重量
                orderItem.setNum(orderItem.getNum()+num);
                orderItem.setMoney(orderItem.getPrice()*orderItem.getNum());
                orderItem.setWeight(weight*orderItem.getNum());

                if (orderItem.getNum() <= 0) {
                    cartList.remove(map);
                    flag=true;
                    break;
                }
                flag=true;
                break;
            }
        }
        if (flag == false) {//购物车中没有该商品，添加
            Sku sku = skuService.findById(skuId);
            if (sku == null) {
                throw new RuntimeException("该商品不存在");
            }
            if (!sku.getStatus().equals("1")) {
                throw new RuntimeException("该商品状态不合法");
            }
            if (num <=0) {
                throw new RuntimeException("该商品数量不合法");
            }
            OrderItem orderItem = new OrderItem();


            orderItem.setSkuId(skuId);
            orderItem.setNum(num);
            orderItem.setSpuId(sku.getSpuId());
            orderItem.setImage(sku.getImage());
            orderItem.setPrice(sku.getPrice());
            orderItem.setName(sku.getName());
            if (sku.getPrice() == null) {
                sku.setPrice(0);
            }
            orderItem.setMoney(sku.getPrice()*num);
            if (sku.getWeight() == null) {
                sku.setWeight(0);
            }
            orderItem.setWeight(sku.getWeight()*num);
            //商品分类
            orderItem.setCategoryId3(sku.getCategoryId());
            Category category3 = (Category)redisTemplate.boundHashOps(CacheKey.CATEGORY).get(sku.getCategoryId());
            if (category3 == null) {
                category3 = categoryService.findById(sku.getCategoryId());
                redisTemplate.boundHashOps(CacheKey.CATEGORY).put(sku.getCategoryId(),category3);
            }
            orderItem.setCategoryId2(category3.getParentId());//二级分类

            Category category2 = (Category)redisTemplate.boundHashOps(CacheKey.CATEGORY).get(category3.getParentId());
            if (category2 == null) {
                category2 = categoryService.findById(category3.getParentId());
                redisTemplate.boundHashOps(CacheKey.CATEGORY).put(category2.getId(),category2);
            }
            orderItem.setCategoryId1(category2.getParentId());

            Map map = new HashMap();
            map.put("item", orderItem);
            map.put("checked", true);//默认选中
            cartList.add(map);
        }
        redisTemplate.boundHashOps(CacheKey.CART_LIST).put(username,cartList);
    }

    public boolean updateChecked(String username, String skuId, boolean checked) {
        List<Map<String, Object>> cartList = findCartList(username);
        boolean isOk=false;
        for (Map<String, Object> map : cartList) {
            if (((OrderItem) map.get("item")).getSkuId().equals(skuId)) {
                map.put("checked", checked);
                isOk=true;
                break;
            }
        }
        if (isOk) {
            redisTemplate.boundHashOps(CacheKey.CART_LIST).put(username,cartList);
        }

        return isOk;
    }

    public void deleteCheckedCart(String username) {
        List<Map<String, Object>> cartList = findCartList(username).stream().filter(cart -> (boolean) cart.get("checked") == false).collect(Collectors.toList());
        redisTemplate.boundHashOps(CacheKey.CART_LIST).put(username,cartList);
    }

    @Autowired
    private PreferentialService preferentialService;
    @Override
    public int preferential(String username) {
        //选中的购物车
        List<OrderItem> orderItemList = findCartList(username).stream()
                .filter(cart -> (boolean) cart.get("checked") == true)
                .map(cart -> (OrderItem) cart.get("item"))
                .collect(Collectors.toList());
        //按分类聚合统计每个分类的金额
        Map<Integer, IntSummaryStatistics> cartMap = orderItemList.stream().
                collect(Collectors.groupingBy(OrderItem::getCategoryId3, Collectors.summarizingInt(OrderItem::getMoney)));
        int allPreMoney=0;
        for (Integer categoryId : cartMap.keySet()) {
            int money = (int) cartMap.get(categoryId).getSum();
            int preMoney = preferentialService.findPreMoneyByCategoryId(categoryId, money);
            allPreMoney+=preMoney;
        }
        return allPreMoney;
    }

    @Override
    public List<Map<String, Object>> findNewOrderItemList(String username) {
        //获取购物车
        List<Map<String, Object>> cartList = findCartList(username);

        //刷新购物车价格
        for (Map<String, Object> map : cartList) {
            OrderItem orderItem = (OrderItem) map.get("item");
            Sku sku = skuService.findById(orderItem.getSkuId());
            orderItem.setPrice(sku.getPrice());
            orderItem.setMoney(sku.getPrice()*orderItem.getNum());
        }
        //保存最新购物车
        redisTemplate.boundHashOps(CacheKey.CART_LIST).put(username,cartList);
        return cartList;
    }
}
