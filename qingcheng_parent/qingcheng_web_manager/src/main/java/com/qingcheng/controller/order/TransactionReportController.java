package com.qingcheng.controller.order;

import com.alibaba.dubbo.config.annotation.Reference;
import com.qingcheng.entity.PageResult;
import com.qingcheng.entity.Result;
import com.qingcheng.pojo.order.TransactionReport;
import com.qingcheng.service.order.TransactionReportService;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/transactionReport")
public class TransactionReportController {

    @Reference
    private TransactionReportService transactionReportService;

    @GetMapping("/findAll")
    public List<TransactionReport> findAll(){
        return transactionReportService.findAll();
    }

    @GetMapping("/findPage")
    public PageResult<TransactionReport> findPage(int page, int size){
        return transactionReportService.findPage(page, size);
    }

    @PostMapping("/findList")
    public List<TransactionReport> findList(@RequestBody Map<String,Object> searchMap){
        return transactionReportService.findList(searchMap);
    }
    @PostMapping("/findByDateRange")
    public List<Map> findByDateRange(String date1, String date2){
        System.out.println("---------x");
        return transactionReportService.findByDateRange(date1,date2);
    }
    @PostMapping("/findPage")
    public PageResult<TransactionReport> findPage(@RequestBody Map<String,Object> searchMap,int page, int size){
        return  transactionReportService.findPage(searchMap,page,size);
    }

    @GetMapping("/findById")
    public TransactionReport findById(Date date){
        return transactionReportService.findById(date);
    }


    @PostMapping("/add")
    public Result add(@RequestBody TransactionReport transactionReport){
        transactionReportService.add(transactionReport);
        return new Result();
    }

    @PostMapping("/update")
    public Result update(@RequestBody TransactionReport transactionReport){
        transactionReportService.update(transactionReport);
        return new Result();
    }

    @GetMapping("/delete")
    public Result delete(Date date){
        transactionReportService.delete(date);
        return new Result();
    }
    @GetMapping("/transactionReportCount")
    public List<Map> transactionReportCount(String date1) {
        Map map = transactionReportService.transactionReport(date1);
        List<Map> list = new ArrayList<>();
        list.add(map);
        return list;
    }
}
