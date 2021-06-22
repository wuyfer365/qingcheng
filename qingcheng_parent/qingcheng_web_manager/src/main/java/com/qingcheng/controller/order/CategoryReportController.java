package com.qingcheng.controller.order;

import com.alibaba.dubbo.config.annotation.Reference;
import com.qingcheng.pojo.order.CategoryReport;
import com.qingcheng.service.order.CategoryReportService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/categoryReport")
public class CategoryReportController {
    @Reference
    private CategoryReportService categoryReportService;

    @GetMapping("/yesterday")
    public List<CategoryReport> yesterday() {
        String strdate="2019-04-15";
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate date=LocalDate.parse(strdate,dtf);
//        LocalDate date = LocalDate.now().minusDays(1);
        return categoryReportService.categoryReport(date);
    }

    @GetMapping("categoryId1Count")
    public List<Map> categoryId1Count(String date1, String date2) {
        return categoryReportService.categoryId1Count(date1, date2);
    }
}
