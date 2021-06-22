package com.qingcheng.pojo.order;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
/**
 * transactionReport实体类
 * @author Administrator
 *
 */
@Table(name="tb_transaction_report")
public class TransactionReport implements Serializable{

	@Id
	private java.util.Date date;//交易日期


	

	private Long userCount;//下单人数

	private Long orderCount;//订单数

	private Long orderGoodsCount;//下单件数

	private Long orderValidCount;//有效订单数

	private Long orderMoney;//下单金额

	private Long returnMoney;//退款金额

	private Long userPayedCount;//付款人数

	private Long orderPayedCount;//付款订单数

	private Long goodsPayedCount;//付款件数

	private Long moneyPayedCount;//付款金额

	
	public Long getUserCount() {
		return userCount;
	}
	public void setUserCount(Long userCount) {
		this.userCount = userCount;
	}

	public Long getOrderCount() {
		return orderCount;
	}
	public void setOrderCount(Long orderCount) {
		this.orderCount = orderCount;
	}

	public Long getOrderGoodsCount() {
		return orderGoodsCount;
	}
	public void setOrderGoodsCount(Long orderGoodsCount) {
		this.orderGoodsCount = orderGoodsCount;
	}

	public Long getOrderValidCount() {
		return orderValidCount;
	}
	public void setOrderValidCount(Long orderValidCount) {
		this.orderValidCount = orderValidCount;
	}

	public Long getOrderMoney() {
		return orderMoney;
	}
	public void setOrderMoney(Long orderMoney) {
		this.orderMoney = orderMoney;
	}

	public Long getReturnMoney() {
		return returnMoney;
	}
	public void setReturnMoney(Long returnMoney) {
		this.returnMoney = returnMoney;
	}

	public Long getUserPayedCount() {
		return userPayedCount;
	}
	public void setUserPayedCount(Long userPayedCount) {
		this.userPayedCount = userPayedCount;
	}

	public Long getOrderPayedCount() {
		return orderPayedCount;
	}
	public void setOrderPayedCount(Long orderPayedCount) {
		this.orderPayedCount = orderPayedCount;
	}

	public Long getGoodsPayedCount() {
		return goodsPayedCount;
	}
	public void setGoodsPayedCount(Long goodsPayedCount) {
		this.goodsPayedCount = goodsPayedCount;
	}

	public Long getMoneyPayedCount() {
		return moneyPayedCount;
	}
	public void setMoneyPayedCount(Long moneyPayedCount) {
		this.moneyPayedCount = moneyPayedCount;
	}

	public java.util.Date getDate() {
		return date;
	}
	public void setDate(java.util.Date date) {
		this.date = date;
	}


	
}
