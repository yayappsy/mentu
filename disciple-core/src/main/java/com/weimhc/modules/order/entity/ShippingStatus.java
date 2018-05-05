package com.weimhc.modules.order.entity;

/**
 * 配送状态
 */
public enum ShippingStatus {

	/** 未发货 */
	unshipped,

	/** 部分发货 */
	partialShipment,

	/** 已发货 */
	shipped,

	/** 部分退货 */
	partialReturns,

	/** 已退货 */
	returned
}