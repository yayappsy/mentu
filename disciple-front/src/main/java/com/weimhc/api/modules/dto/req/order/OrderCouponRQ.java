/**
 * 
 */
package com.weimhc.api.modules.dto.req.order;

import com.weimhc.framework.dto.req.AbstractRQ;
import io.swagger.annotations.ApiModelProperty;

/**
 * 订单优惠券 请求
 * 
 * @author zsm
 * @version 2016-10-05
 */
public class OrderCouponRQ extends AbstractRQ {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 优惠券id
	 * 
	 */
	private String couponId;

	/**
	 * 获取优惠券id
	 * 
	 * @return the couponId
	 */
	@ApiModelProperty(value = "优惠券id")
	public String getCouponId() {
		return couponId;
	}

	/**
	 * 设置优惠券id
	 * 
	 * @param couponId
	 *            the couponId to set
	 */
	public void setCouponId(String couponId) {
		this.couponId = couponId;
	}

}