/**
 * 
 */
package com.weimhc.modules.payment.entity;

import java.math.BigDecimal;
import java.util.Date;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.thinkgem.javamg.common.persistence.DataEntity;
import com.weimhc.modules.member.entity.Member;
import com.weimhc.modules.order.entity.Order;

/**
 * 订单支付流水Entity
 * 
 * @author lc
 * @version 2017-06-08
 */
public class PaymentTurnover extends DataEntity<PaymentTurnover> {

	private static final long serialVersionUID = 1L;

	/**
	 * 交易编号
	 * 
	 */
	private String sn;

	/**
	 * 订单
	 * 
	 */
	private Order order;

	/**
	 * 支付发起平台
	 * 
	 */
	private PaymentFrom paymentFrom;
	/**
	 * 支付方式
	 * 
	 */
	private PaymentMethod paymentMethod;
	/**
	 * 支付方式名称
	 * 
	 */
	private String paymentMethodName;

	/**
	 * 支付流水状态
	 * 
	 */
	private Status status;

	/**
	 * 交易金额
	 * 
	 */
	private BigDecimal amount;
	/**
	 * 交易类型
	 * 
	 */
	private TradeType tradeType;
	/**
	 * 买家
	 * 
	 */
	private Member buyer;
	/**
	 * 买家昵称
	 * 
	 */
	private String buyerNickname;

	/**
	 * 第三方订单号
	 */
	private String thirdTradeNo;

	/**
	 * 到期时间
	 * 
	 */
	private Date expire;

	/**
	 * 支付流水状态
	 */
	public enum Status {

		/** 等待支付 */
		wait,

		/** 支付成功 */
		success,

		/** 支付失败 */
		failure
	}

	public PaymentTurnover() {
		super();
	}

	public PaymentTurnover(String id) {
		super(id);
	}

	/**
	 * 获取交易编号
	 * 
	 * @return 交易编号
	 */
	@Length(min = 1, max = 64)
	public String getSn() {
		return sn;
	}

	/**
	 * 设置交易编号
	 * 
	 * @param sn
	 *            交易编号
	 */
	public void setSn(String sn) {
		this.sn = sn;
	}

	/**
	 * 获取订单
	 * 
	 * @return 订单
	 */
	public Order getOrder() {
		return order;
	}

	/**
	 * 设置订单
	 * 
	 * @param order
	 *            订单
	 */
	public void setOrder(Order order) {
		this.order = order;
	}

	/**
	 * 获取支付方式
	 * 
	 * @return 支付方式
	 */
	public PaymentMethod getPaymentMethod() {
		return paymentMethod;
	}

	/**
	 * 设置支付方式
	 * 
	 * @param paymentMethod
	 *            支付方式
	 */
	public void setPaymentMethod(PaymentMethod paymentMethod) {
		this.paymentMethod = paymentMethod;
	}

	/**
	 * 获取支付方式名称
	 * 
	 * @return 支付方式名称
	 */
	@Length(min = 1, max = 255)
	public String getPaymentMethodName() {
		return paymentMethodName;
	}

	/**
	 * 设置支付方式名称
	 * 
	 * @param paymentMethodName
	 *            支付方式名称
	 */
	public void setPaymentMethodName(String paymentMethodName) {
		this.paymentMethodName = paymentMethodName;
	}

	/**
	 * 获取支付流水状态
	 * 
	 * @return 支付流水状态
	 */
	public Status getStatus() {
		return status;
	}

	/**
	 * 设置支付流水状态
	 * 
	 * @param status
	 *            支付流水状态
	 */
	public void setStatus(Status status) {
		this.status = status;
	}

	/**
	 * 获取支付发起平台
	 * 
	 * @return 支付发起平台
	 */
	public PaymentFrom getPaymentFrom() {
		return paymentFrom;
	}

	/**
	 * 设置支付发起平台
	 * 
	 * @param paymentFrom
	 *            支付发起平台
	 */
	public void setPaymentFrom(PaymentFrom paymentFrom) {
		this.paymentFrom = paymentFrom;
	}

	/**
	 * 获取交易金额
	 * 
	 * @return 交易金额
	 */
	public BigDecimal getAmount() {
		return amount;
	}

	/**
	 * 设置交易金额
	 * 
	 * @param amount
	 *            交易金额
	 */
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	/**
	 * 获取交易类型
	 * 
	 * @return 交易类型
	 */
	public TradeType getTradeType() {
		return tradeType;
	}

	/**
	 * 设置交易类型
	 * 
	 * @param tradeType
	 *            交易类型
	 */
	public void setTradeType(TradeType tradeType) {
		this.tradeType = tradeType;
	}

	/**
	 * 获取买家
	 * 
	 * @return 买家
	 */
	public Member getBuyer() {
		return buyer;
	}

	/**
	 * 设置买家id
	 * 
	 * @param buyer
	 *            买家id
	 */
	public void setBuyer(Member buyer) {
		this.buyer = buyer;
	}

	/**
	 * 获取买家昵称
	 * 
	 * @return 买家昵称
	 */
	@Length(min = 1, max = 64)
	public String getBuyerNickname() {
		return buyerNickname;
	}

	/**
	 * 设置买家昵称
	 * 
	 * @param buyerNickname
	 *            买家昵称
	 */
	public void setBuyerNickname(String buyerNickname) {
		this.buyerNickname = buyerNickname;
	}

	/**
	 * 返回 第三方订单号
	 * 
	 * @return the thirdTradeNo
	 */
	public String getThirdTradeNo() {
		return thirdTradeNo;
	}

	/**
	 * 设置 第三方订单号
	 * 
	 * @param thirdTradeNo
	 *            the thirdTradeNo to set
	 */
	public void setThirdTradeNo(String thirdTradeNo) {
		this.thirdTradeNo = thirdTradeNo;
	}

	/**
	 * 获取到期时间
	 * 
	 * @return 到期时间
	 */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getExpire() {
		return expire;
	}

	/**
	 * 设置到期事假
	 * 
	 * @param expire
	 *            到期事假
	 */
	public void setExpire(Date expire) {
		this.expire = expire;
	}
}