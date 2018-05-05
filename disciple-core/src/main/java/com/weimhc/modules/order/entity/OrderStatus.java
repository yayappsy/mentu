package com.weimhc.modules.order.entity;

/**
 * 订单状态
 */
public enum OrderStatus {
	/**
	 * 未付款
	 * 
	 */
	waitPay,

	/**
	 * 已完成
	 */
	completed,

	/**
	 * 已取消
	 * <p>
	 * 您手动取消订单（操作：交易取消后，不能做任何操作）
	 * </p>
	 */
	cancelled
}