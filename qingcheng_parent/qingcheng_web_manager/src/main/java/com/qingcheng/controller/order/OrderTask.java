package com.qingcheng.controller.order;

import com.alibaba.dubbo.config.annotation.Reference;
import com.qingcheng.service.order.CategoryReportService;
import com.qingcheng.service.order.TransactionReportService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.sound.midi.Soundbank;
import java.util.Date;

@Component
public class OrderTask {

    @Reference
    private CategoryReportService categoryReportService;
    @Reference
    private TransactionReportService transactionReportService;
    //    @Scheduled(cron ="* * * * * ?" )
//    public void createCategoryReportData() {
//        System.out.println(new Date());
//    }
    @Scheduled(cron ="0 0 1 * * ?" )
    public void createCategoryReportData() {
        System.out.println("生成类目统计数据");
        System.out.println("-------------");
        categoryReportService.createData();
    }
    @Scheduled(cron ="0 0 1 * * ?" )
    public void createTransactionReportData() {
        System.out.println("生成交易统计数据");
        transactionReportService.createData();
    }
}
