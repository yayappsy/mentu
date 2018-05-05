/**
 * 
 */
package com.weimhc.api.modules.dto.resp;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.google.common.collect.Lists;
import com.weimhc.framework.dto.AbstractDto;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 预约DTO
 * 
 * @author lc
 * @version 2016-12-15
 */
public class OrderLogDto extends AbstractDto {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1095501047336970672L;

	/**
	 * 订单编号
	 * 
	 */
	private String sn;
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
	 * 支付时间
	 */
	private Date paymentDate;
	/**
	 * 是否使用优惠券
	 */
	private Boolean isUseCoupons;
	/**
	 * 订单项list
	 */
	private List<OrderItemDto> orderItemList = Lists.newArrayList();

	/**
	 * 获取订单编号
	 * 
	 * @return the sn
	 */
	@ApiModelProperty(value = "订单编号")
	public String getSn() {
		return sn;
	}

	/**
	 * 设置订单编号
	 * 
	 * @param sn
	 *            the sn to set
	 */
	public void setSn(String sn) {
		this.sn = sn;
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
	 * 获取订单总金额
	 * 
	 * @return the totalAmount
	 */
	@ApiModelProperty(value = "订单总金额")
	public BigDecimal getTotalAmount() {
		return totalAmount;
	}

	/**
	 * 设置订单总金额
	 * 
	 * @param totalAmount
	 *            the totalAmount to set
	 */
	public void setTotalAmount(BigDecimal totalAmount) {
		this.totalAmount = totalAmount;
	}

	/**
	 * 获取支付时间
	 * 
	 * @return the paymentDate
	 */
	@ApiModelProperty(value = "支付时间")
	@JsonFormat(pattern = "yyyy-MM-dd")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	public Date getPaymentDate() {
		return paymentDate;
	}

	/**
	 * 设置支付时间
	 * 
	 * @param paymentDate
	 *            the paymentDate to set
	 */
	public void setPaymentDate(Date paymentDate) {
		this.paymentDate = paymentDate;
	}

	/**
	 * 获取订单项list
	 * 
	 * @return the orderItemList
	 */
	@ApiModelProperty(value = "订单项list")
	public List<OrderItemDto> getOrderItemList() {
		return orderItemList;
	}

	/**
	 * 设置订单项list
	 * 
	 * @param orderItemList
	 *            the orderItemList to set
	 */
	public void setOrderItemList(List<OrderItemDto> orderItemList) {
		this.orderItemList = orderItemList;
	}

	/**
	 * 获取是否使用优惠券
	 * 
	 * @return the isUseCoupons
	 */
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

}