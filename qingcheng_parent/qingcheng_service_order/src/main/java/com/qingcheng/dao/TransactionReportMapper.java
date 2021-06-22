package com.qingcheng.dao;

import com.qingcheng.pojo.order.TransactionReport;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
import java.util.Map;

public interface TransactionReportMapper extends Mapper<TransactionReport> {
    //下单人数
    @Select("SELECT COUNT(a.num) userCount " +
            "FROM " +
            "(SELECT COUNT(DISTINCT(username)) num " +
            "FROM tb_order o "  +
            "WHERE " +
            "DATE_FORMAT(o.`pay_time`,'%Y-%m-%d' ) =#{date1} " +
            "GROUP BY username) a")
    public Long userCount(@Param("date1") String date1);
    //订单数
    @Select("SELECT COUNT(*) orderCount " +
            "FROM tb_order o " +
            "WHERE  " +
            "DATE_FORMAT(o.`pay_time`,'%Y-%m-%d' ) =#{date1}")
    public Long orderCount(@Param("date1") String date1);

    //下单件数
    @Select("SELECT sum(oi.num) orderGoodsCount " +
            "FROM tb_order o ,tb_order_item oi " +
            "WHERE o.id=oi.order_id AND " +
            "DATE_FORMAT(o.`pay_time`,'%Y-%m-%d' ) =#{date1}")
    public Long orderGoodsCount(@Param("date1") String date1);

    //有效订单数
    @Select("SELECT COUNT(*) orderValidCount " +
            "FROM tb_order o " +
            "WHERE o.pay_status='1' AND " +
            "DATE_FORMAT(o.`pay_time`,'%Y-%m-%d' ) =#{date1}")
    public Long orderValidCount(@Param("date1") String date1);

    //下单金额
    @Select("SELECT sum(oi.money) orderMoney " +
            "FROM tb_order o ,tb_order_item oi " +
            "WHERE o.id=oi.order_id AND o.pay_status='1' AND " +
            "DATE_FORMAT(o.`pay_time`,'%Y-%m-%d' ) =#{date1}")
    public Long orderMoney(@Param("date1") String date1);

    //退款金额
    @Select("SELECT sum(oi.money) returnMoney " +
            "FROM tb_order o ,tb_order_item oi " +
            "WHERE o.id=oi.order_id AND o.pay_status='2' AND " +
            "DATE_FORMAT(o.`pay_time`,'%Y-%m-%d' ) =#{date1}")
    public Long returnMoney(@Param("date1") String date1);

    //付款人数
    @Select("SELECT COUNT(DISTINCT(username)) userPayedCount " +
            "FROM tb_order o " +
            "WHERE o.`pay_status`='1' AND " +
            "DATE_FORMAT(o.`pay_time`,'%Y-%m-%d' ) =#{date1}")
    public Long userPayedCount(@Param("date1") String date1);

    //付款订单数
    @Select("SELECT COUNT(*) orderPayedCount " +
            "FROM tb_order o " +
            "WHERE o.`pay_status`='1' AND " +
            "DATE_FORMAT(o.`pay_time`,'%Y-%m-%d' ) =#{date1}")
    public Long orderPayedCount(@Param("date1") String date1);

    //付款件数,付款金额
    @Select("SELECT sum(oi.num) goodsPayedCount,sum(oi.money) moneyPayedCount " +
            "FROM tb_order o ,tb_order_item oi " +
            "WHERE o.id=oi.order_id AND o.pay_status='1' AND " +
            "DATE_FORMAT(o.`pay_time`,'%Y-%m-%d' ) =#{date1}")
    public List<Map> goodsPayedCountAndMoneyPayedCount(@Param("date1") String date1);

    @Select("SELECT sum(user_count) userCount,sum(order_count) orderCount,sum(order_goods_count) orderGoodsCount," +
            "sum(order_valid_count) orderValidCount,sum(order_money) orderMoney,sum(return_money) returnMoney,sum(user_payed_count) userPayedCount ," +
            "sum(order_payed_count) orderPayedCount,sum(goods_payed_count) goodsPayedCount,sum(money_payed_count) moneyPayedCount " +
            "FROM tb_transaction_report WHERE DATE_FORMAT(`date`,'%Y-%m-%d' )>=#{date1}" +
            " AND DATE_FORMAT(`date`,'%Y-%m-%d' )<=#{date2}")
    public List<Map> findByDateRange(@Param("date1") String date1,@Param("date2") String date2);
}
