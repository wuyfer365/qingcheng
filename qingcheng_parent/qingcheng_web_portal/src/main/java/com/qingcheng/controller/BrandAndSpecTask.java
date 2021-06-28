package com.qingcheng.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.qingcheng.service.goods.CategoryService;
import com.qingcheng.service.goods.SpecService;
import com.qingcheng.service.order.CategoryReportService;
import com.qingcheng.service.order.TransactionReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class BrandAndSpecTask {

    @Reference
    private CategoryReportService categoryReportService;
    @Reference
    private TransactionReportService transactionReportService;
    @Reference
    private CategoryService categoryService;
    @Reference
    private SpecService specService;

    @Scheduled(cron ="0 0 1 * * ?" )
    public void createCategoryReportData() {
        System.out.println("生成类目统计数据");
        System.out.println("-------------");
        categoryReportService.createData();
    }

    @Scheduled(cron ="0 0 0 * * ?" )//每天凌晨0点0分0秒执行一次
    public void saveBrandListToRedis() {
        System.out.println("根据分类名称生成品牌列表数据到redis");
        categoryService.saveBrandListToRedis();
    }

    @Scheduled(cron ="0 0 0 * * ?" )//每天凌晨0点0分0秒执行一次
    public void saveSpecListToRedis() {
        System.out.println("根据分类名称生成规格列表数据到redis");
        categoryService.saveSpecListToRedis();
    }
}
