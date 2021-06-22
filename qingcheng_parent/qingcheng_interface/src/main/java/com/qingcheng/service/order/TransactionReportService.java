package com.qingcheng.service.order;
import com.qingcheng.entity.PageResult;
import com.qingcheng.pojo.order.TransactionReport;

import java.util.*;

/**
 * transactionReport业务逻辑层
 */
public interface TransactionReportService {


    public List<TransactionReport> findAll();


    public PageResult<TransactionReport> findPage(int page, int size);


    public List<TransactionReport> findList(Map<String, Object> searchMap);


    public PageResult<TransactionReport> findPage(Map<String, Object> searchMap, int page, int size);


    public TransactionReport findById(Date date);

    public void add(TransactionReport transactionReport);


    public void update(TransactionReport transactionReport);


    public void delete(Date date);
    public Map transactionReport(String date1);
    public void createData();
    public List<Map> findByDateRange(String date1, String date2);
}
