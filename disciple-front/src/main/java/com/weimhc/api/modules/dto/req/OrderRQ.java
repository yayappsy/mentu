/**
 * 
 */
package com.weimhc.api.modules.dto.req;

import com.weimhc.framework.dto.req.AbstractRQ;
import com.weimhc.modules.payment.entity.PaymentFrom;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;
import java.util.List;

/**
 * 订单 请求
 * 
 * @author zsm
 * @version 2016-10-05
 */
public class OrderRQ extends AbstractRQ {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 支付方式id
	 */
	private String paymentMethodId;
	/**
	 * 订单备注
	 * 
	 */
	private String orderRemarks;
	/**
	 * 是否属于分期订单
	 */
	private boolean isInstalmentOrder;
	/**
	 * 是否使用优惠券
	 */
	private Boolean isUseCoupons;
	/**
	 * 订单应付价格
	 * 
	 */
	private BigDecimal amountPayable;
	/**
	 * 总金额
	 * 
	 */
	private BigDecimal totalAmount;
	/**
	 * 订单项list
	 */
	private List<OrderItemRQ> orderItemRQs;
	/**
	 * 优惠券list
	 */
	private List<OrderCouponRQ> orderCouponRQs;

	private PaymentFrom paymentFrom;

	/**
	 * 获取支付方式id
	 * 
	 * @return the paymentMethodId
	 */
	@ApiModelProperty(value = "支付方式id")
	public String getPaymentMethodId() {
		return paymentMethodId;
	}

	/**
	 * 设置支付方式id
	 * 
	 * @param paymentMethodId
	 *            the paymentMethodId to set
	 */
	public void setPaymentMethodId(String paymentMethodId) {
		this.paymentMethodId = paymentMethodId;
	}

	/**
	 * 获取订单备注
	 * 
	 * @return the orderRemarks
	 */
	@ApiModelProperty(value = "订单备注")
	public String getOrderRemarks() {
		return orderRemarks;
	}

	/**
	 * 设置订单备注
	 * 
	 * @param orderRemarks
	 *            the orderRemarks to set
	 */
	public void setOrderRemarks(String orderRemarks) {
		this.orderRemarks = orderRemarks;
	}

	/**
	 * 获取是否分期订单
	 * 
	 * @return the isInstalmentOrder
	 */
	@ApiModelProperty(value = "是否分期订单")
	public boolean isInstalmentOrder() {
		return isInstalmentOrder;
	}

	/**
	 * 设置是否分期订单
	 * 
	 * @param isInstalmentOrder
	 *            the isInstalmentOrder to set
	 */
	public void setInstalmentOrder(boolean isInstalmentOrder) {
		this.isInstalmentOrder = isInstalmentOrder;
	}

	/**
	 * 获取是否使用优惠券
	 * 
	 * @return the isUseCoupons
	 */
	@ApiModelProperty(value = "是否使用优惠券")
	public Boolean getIsUseCoupons() {
		return isUseCoupons;
	}

	/**
	 * 设置是否使用优惠券
	 * 
	 * @param isUseCoupons
	 *            the isUseCoupons to set
	 */
	public void setIsUseCoupons(Boolean isUseCoupons) {
		this.isUseCoupons = isUseCoupons;
	}

	/**
	 * 获取订单应付价格
	 * 
	 * @return the amountPayable
	 */
	@ApiModelProperty(value = "订单应付价格")
	public BigDecimal getAmountPayable() {
		return amountPayable;
	}

	/**
	 * 设置订单应付价格
	 * 
	 * @param amountPayable
	 *            the amountPayable to set
	 */
	public void setAmountPayable(BigDecimal amountPayable) {
		this.amountPayable = amountPayable;
	}

	/**
	 * 获取总价格
	 * 
	 * @return the totalAmount
	 */
	@ApiModelProperty(value = "总价格")
	public BigDecimal getTotalAmount() {
		return totalAmount;
	}

	/**
	 * 设置总价格
	 * 
	 * @param totalAmount
	 *            the totalAmount to set
	 */
	public void setTotalAmount(BigDecimal totalAmount) {
		this.totalAmount = totalAmount;
	}

	/**
	 * 获取订单项list
	 * 
	 * @return the orderItemRQs
	 */
	@ApiModelProperty(value = "订单项list")
	public List<OrderItemRQ> getOrderItemRQs() {
		return orderItemRQs;
	}

	/**
	 * 设置订单项list
	 * 
	 * @param orderItemRQs
	 *            the orderItemRQs to set
	 */
	public void setOrderItemRQs(List<OrderItemRQ> orderItemRQs) {
		this.orderItemRQs = orderItemRQs;
	}

	/**
	 * 获取优惠券list
	 * 
	 * @return the orderCouponRQs
	 */
	@ApiModelProperty(value = "优惠券list")
	public List<OrderCouponRQ> getOrderCouponRQs() {
		return orderCouponRQs;
	}

	/**
	 * 设置优惠券list
	 * 
	 * @param orderCouponRQs
	 *            the orderCouponRQs to set
	 */
	public void setOrderCouponRQs(List<OrderCouponRQ> orderCouponRQs) {
		this.orderCouponRQs = orderCouponRQs;
	}

	/**
	 * @return the paymentFrom
	 */
	@ApiModelProperty(value = "支付发起地址")
	public PaymentFrom getPaymentFrom() {
		return paymentFrom;
	}

	/**
	 * @param paymentFrom
	 *            the paymentFrom to set
	 */
	public void setPaymentFrom(PaymentFrom paymentFrom) {
		this.paymentFrom = paymentFrom;
	}

}