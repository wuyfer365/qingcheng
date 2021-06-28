package com.qingcheng.service.impl;

import com.qingcheng.service.goods.CategoryService;
import com.qingcheng.service.goods.SkuService;
import com.qingcheng.util.CacheKey;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

@Component
public class Init implements InitializingBean {
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    protected SkuService skuService;

    public void afterPropertiesSet() throws Exception {
        //缓存预热
        System.out.println("redis预热");
        categoryService.saveCategoryTreeToRedis();
        skuService.saveAllPriceToRedis();

        System.out.println("brand and spec save to redis right now");
        categoryService.saveBrandAndSpecListToRedisNow();

    }
}
