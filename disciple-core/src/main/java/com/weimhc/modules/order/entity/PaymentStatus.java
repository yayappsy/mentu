package com.weimhc.modules.order.entity;

/**
 * 支付状态Entity
 * 
 * @author liuchao
 * @version 2016-10-13
 */
public enum PaymentStatus {
	/** 未支付 */
	unpaid,

	/** 部分支付 */
	partialPayment,

	/** 已支付 */
	paid,

	/** 部分退款 */
	partialRefunds,

	/** 已退款 */
	refunded
}