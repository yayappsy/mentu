/**
 * 
 */
package com.weimhc.modules.order.entity;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.javamg.common.persistence.DataEntity;
import com.weimhc.modules.coupon.entity.Coupon;

/**
 * 订单优惠券Entity
 * 
 * @author lc
 * @version 2017-06-22
 */
public class OrderCoupon extends DataEntity<OrderCoupon> {

	private static final long serialVersionUID = 1L;
	/**
	 * 订单
	 * 
	 */
	private Order order;
	/**
	 * 优惠券
	 * 
	 */
	private Coupon coupon;
	/**
	 * 优惠券名称
	 * 
	 */
	private String couponName;
	/**
	 * 优惠券数量
	 * 
	 */
	private Integer quantity;

	public OrderCoupon() {
		super();
	}

	public OrderCoupon(String id) {
		super(id);
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
	 * 获取优惠券
	 * 
	 * @return 优惠券
	 */
	@Length(min = 1, max = 64)
	public Coupon getCoupon() {
		return coupon;
	}

	/**
	 * 设置优惠券
	 * 
	 * @param coupon
	 *            优惠券
	 */
	public void setCoupon(Coupon coupon) {
		this.coupon = coupon;
	}

	/**
	 * 获取优惠券名称
	 * 
	 * @return 优惠券名称
	 */
	@Length(min = 0, max = 255)
	public String getCouponName() {
		return couponName;
	}

	/**
	 * 设置优惠券名称
	 * 
	 * @param couponName
	 *            优惠券名称
	 */
	public void setCouponName(String couponName) {
		this.couponName = couponName;
	}

	/**
	 * 获取优惠券数量
	 * 
	 * @return 优惠券数量
	 */
	public Integer getQuantity() {
		return quantity;
	}

	/**
	 * 设置优惠券数量
	 * 
	 * @param quantity
	 *            优惠券数量
	 */
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

}