package com.qingcheng.service.impl;
import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.qingcheng.dao.TransactionReportMapper;
import com.qingcheng.entity.PageResult;
import com.qingcheng.pojo.order.TransactionReport;
import com.qingcheng.service.order.TransactionReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service(interfaceClass = TransactionReportService.class)
public class TransactionReportServiceImpl implements TransactionReportService {

    @Autowired
    private TransactionReportMapper transactionReportMapper;

    /**
     * 返回全部记录
     * @return
     */
    public List<TransactionReport> findAll() {
        return transactionReportMapper.selectAll();
    }

    /**
     * 分页查询
     * @param page 页码
     * @param size 每页记录数
     * @return 分页结果
     */
    public PageResult<TransactionReport> findPage(int page, int size) {
        PageHelper.startPage(page,size);
        Page<TransactionReport> transactionReports = (Page<TransactionReport>) transactionReportMapper.selectAll();
        return new PageResult<TransactionReport>(transactionReports.getTotal(),transactionReports.getResult());
    }

    /**
     * 条件查询
     * @param searchMap 查询条件
     * @return
     */
    public List<TransactionReport> findList(Map<String, Object> searchMap) {
        Example example = createExample(searchMap);
        return transactionReportMapper.selectByExample(example);
    }

    /**
     * 分页+条件查询
     * @param searchMap
     * @param page
     * @param size
     * @return
     */
    public PageResult<TransactionReport> findPage(Map<String, Object> searchMap, int page, int size) {
        PageHelper.startPage(page,size);
        Example example = createExample(searchMap);
        Page<TransactionReport> transactionReports = (Page<TransactionReport>) transactionReportMapper.selectByExample(example);
        return new PageResult<TransactionReport>(transactionReports.getTotal(),transactionReports.getResult());
    }

    /**
     * 根据Id查询
     * @param date
     * @return
     */
    public TransactionReport findById(java.util.Date date) {
        return transactionReportMapper.selectByPrimaryKey(date);
    }

    /**
     * 新增
     * @param transactionReport
     */
    public void add(TransactionReport transactionReport) {
        transactionReportMapper.insert(transactionReport);
    }

    /**
     * 修改
     * @param transactionReport
     */
    public void update(TransactionReport transactionReport) {
        transactionReportMapper.updateByPrimaryKeySelective(transactionReport);
    }

    /**
     *  删除
     * @param date
     */
    public void delete(java.util.Date date) {
        transactionReportMapper.deleteByPrimaryKey(date);
    }

    @Override
    public Map transactionReport(String date1) {
        //下单人数
        Map maps =new HashMap();
        Long userCount = transactionReportMapper.userCount(date1);
        maps.put("userCount", userCount);
        return maps;
    }

    /**
     * 构建查询条件
     * @param searchMap
     * @return
     */
    private Example createExample(Map<String, Object> searchMap){
        Example example=new Example(TransactionReport.class);
        Example.Criteria criteria = example.createCriteria();
        if(searchMap!=null){


        }
        return example;
    }
    @Transactional
    public void createData() {
        System.out.println("-------------------b");
        String strdate="2019-05-25";
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate date1=LocalDate.parse(strdate,dtf);
        ZoneId zone = ZoneId.systemDefault();
        Instant instant = date1.atStartOfDay().atZone(zone).toInstant();
        java.util.Date date = Date.from(instant);
        Long userCount =transactionReportMapper.userCount(strdate);
        Long orderCount = transactionReportMapper.orderCount(strdate);
        Long orderGoodsCount = transactionReportMapper.orderGoodsCount(strdate);
        Long orderValidCount = transactionReportMapper.orderValidCount(strdate);
        Long orderMoney = transactionReportMapper.orderMoney(strdate);
        Long returnMoney = transactionReportMapper.returnMoney(strdate);
        Long userPayedCount = transactionReportMapper.userPayedCount(strdate);
        Long orderPayedCount = transactionReportMapper.orderPayedCount(strdate);
        List<Map> maps = transactionReportMapper.goodsPayedCountAndMoneyPayedCount(strdate);
        Map<String,BigDecimal> map = maps.get(0);
        BigDecimal goodsPayedCount = map.get("goodsPayedCount");
        BigDecimal moneyPayedCount = map.get("moneyPayedCount");
        TransactionReport transactionReport = transactionReportMapper.selectByPrimaryKey(date);

        if (transactionReport == null || "".equals(transactionReport)) {
            transactionReport=new TransactionReport();
            transactionReport.setUserCount(userCount);//1下单人数
            transactionReport.setOrderCount(orderCount);//2订单数
            transactionReport.setOrderGoodsCount(orderGoodsCount);//下单件数
            transactionReport.setOrderValidCount(orderValidCount);//有效订单数
            transactionReport.setOrderMoney(orderMoney==null?0:orderMoney);//下单金额
            transactionReport.setReturnMoney(returnMoney==null?0:returnMoney);//退款金额
            transactionReport.setUserPayedCount(userPayedCount);//付款人数
            transactionReport.setOrderPayedCount(orderPayedCount);//付款订单数
            transactionReport.setGoodsPayedCount(goodsPayedCount.longValue());//付款件数
            transactionReport.setMoneyPayedCount(moneyPayedCount==null?0:moneyPayedCount.longValue());//付款金额
            instant = date1.atStartOfDay().atZone(zone).toInstant();
            date = Date.from(instant);
            transactionReport.setDate(date);
            transactionReportMapper.insertSelective(transactionReport);
        }else {
            transactionReport.setUserCount(userCount);//1下单人数
            transactionReport.setOrderCount(orderCount);//2订单数
            transactionReport.setOrderGoodsCount(orderGoodsCount);//下单件数
            transactionReport.setOrderValidCount(orderValidCount);//有效订单数
            transactionReport.setOrderMoney(orderMoney);//下单金额
            transactionReport.setReturnMoney(returnMoney==null?0:returnMoney);//退款金额
            transactionReport.setUserPayedCount(userPayedCount);//付款人数
            transactionReport.setOrderPayedCount(orderPayedCount);//付款订单数
            transactionReport.setGoodsPayedCount(goodsPayedCount.longValue());//付款件数
            transactionReport.setMoneyPayedCount(moneyPayedCount==null?0:moneyPayedCount.longValue());//付款金额
            instant = date1.atStartOfDay().atZone(zone).toInstant();
            date = Date.from(instant);
            transactionReport.setDate(date);
            transactionReportMapper.updateByPrimaryKey(transactionReport);
        }

    }
    @Override
    public List<Map> findByDateRange(String date1, String date2) {
        return transactionReportMapper.findByDateRange(date1,date2);
    }
}
