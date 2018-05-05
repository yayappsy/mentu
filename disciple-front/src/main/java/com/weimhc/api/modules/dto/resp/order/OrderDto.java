/**
 * 
 */
package com.weimhc.api.modules.dto.resp.order;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.common.collect.Lists;
import com.thinkgem.javamg.common.utils.StringUtils;
import com.weimhc.framework.dto.AbstractIdDto;
import com.weimhc.framework.web.utils.MessageSourceUtils;
import com.weimhc.modules.order.entity.OrderStatus;
import com.weimhc.modules.order.entity.PaymentStatus;
import com.weimhc.modules.payment.entity.PaymentMethod;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.format.annotation.DateTimeFormat;
import springfox.documentation.annotations.ApiIgnore;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 预约DTO
 * 
 * @author lc
 * @version 2016-12-15
 */
public class OrderDto extends AbstractIdDto {

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
	 * 到期时间
	 * 
	 */
	private Date expire;
	/**
	 * 订单状态
	 * 
	 */
	private OrderStatus status;
	/**
	 * 支付状态
	 * 
	 */
	private PaymentStatus paymentStatus;
	/**
	 * 支付方式id
	 * 
	 */
	private PaymentMethod paymentMethod;
	/**
	 * 支付方式名称
	 * 
	 */
	private String paymentMethodName;
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
	 * 是否使用优惠劵
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
	 * 支付时间
	 */
	private Date paymentDate;
	/**
	 * 创建时间
	 */
	private Date createDate;
	/**
	 * 订单项list
	 */
	private List<OrderItemDto> orderItemList = Lists.newArrayList();
	/**
	 * 订单支付信息
	 */
	private Map<String, String> payInfos;

	private Boolean isfinishPay;

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
	 * 获取到期时间
	 * 
	 * @return the expire
	 */
	@ApiModelProperty(value = "到期时间")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getExpire() {
		return expire;
	}

	/**
	 * 设置到期时间
	 * 
	 * @param expire
	 *            the expire to set
	 */
	public void setExpire(Date expire) {
		this.expire = expire;
	}

	/**
	 * 获取订单状态
	 * 
	 * @return the status
	 */
	public OrderStatus getStatus() {
		return status;
	}

	/**
	 * 设置订单状态
	 * 
	 * @param status
	 *            the status to set
	 */
	public void setStatus(OrderStatus status) {
		this.status = status;
	}

	@ApiModelProperty(value = "订单状态描述")
	public String getStatusDescription() {
		if (getStatus() != null) {
			return MessageSourceUtils
					.getMessage("Order.OrderStatus." + getStatus().name());
		}
		return "";
	}

	/**
	 * 获取支付状态
	 * 
	 * @return the paymentStatus
	 */
	public PaymentStatus getPaymentStatus() {
		return paymentStatus;
	}

	/**
	 * 设置支付状态
	 * 
	 * @param paymentStatus
	 *            the paymentStatus to set
	 */
	public void setPaymentStatus(PaymentStatus paymentStatus) {
		this.paymentStatus = paymentStatus;
	}

	@ApiModelProperty(value = "支付状态描述")
	public String getPaymentStatusDescription() {
		if (getPaymentStatus() != null) {
			return MessageSourceUtils.getMessage(
					"Order.PaymentStatus." + getPaymentStatus().name());
		}
		return "";
	}

	/**
	 * 获取支付方式
	 * 
	 * @return the paymentMethod
	 */
	@ApiIgnore
	@JsonIgnore
	public PaymentMethod getPaymentMethod() {
		return paymentMethod;
	}

	/**
	 * 设置支付方式
	 * 
	 * @param paymentMethod
	 *            the paymentMethod to set
	 */
	public void setPaymentMethod(PaymentMethod paymentMethod) {
		this.paymentMethod = paymentMethod;
	}

	/**
	 * 获取支付方式
	 * 
	 * @return the paymentMethodId
	 */
	@ApiModelProperty(value = "支付方式id")
	public String getPaymentMethodId() {
		if (paymentMethod != null
				&& StringUtils.isNotBlank(paymentMethod.getId())) {
			return paymentMethod.getId();
		}
		return "";
	}

	/**
	 * 获取支付方式名称
	 * 
	 * @return the paymentMethodName
	 */
	@ApiModelProperty(value = "支付方式名称")
	public String getPaymentMethodName() {
		return paymentMethodName;
	}

	/**
	 * 设置支付方式名称
	 * 
	 * @param paymentMethodName
	 *            the paymentMethodName to set
	 */
	public void setPaymentMethodName(String paymentMethodName) {
		this.paymentMethodName = paymentMethodName;
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
	 * 获取支付时间
	 * 
	 * @return the paymentDate
	 */
	@ApiModelProperty(value = "支付时间")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
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

	@ApiModelProperty(value = "订单购买内容")
	public String getOrderContent() {
		String str = "";
		if (orderItemList.size() > 0) {
			for (OrderItemDto orderItemDto : orderItemList) {
				str += orderItemDto.getProductName() + ",";
			}

		}
		return str;
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
	 * 获取订单支付信息
	 * 
	 * @return the payInfos
	 */
	@ApiModelProperty(value = "订单支付信息")
	public Map<String, String> getPayInfos() {
		return payInfos;
	}

	/**
	 * 设置订单支付信息
	 * 
	 * @param payInfos
	 *            the payInfos to set
	 */
	public void setPayInfos(Map<String, String> payInfos) {
		this.payInfos = payInfos;
	}

	/**
	 * 获取订单创建时间
	 * 
	 * @return the createDate
	 */
	@ApiModelProperty(value = "订单创建时间")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getCreateDate() {
		return createDate;
	}

	/**
	 * @param createDate
	 *            the createDate to set
	 */
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	/**
	 * 获取是否完成支付
	 * 
	 * @return the isfinishPay
	 */
	@ApiModelProperty(value = "是否完成支付")
	public Boolean getIsfinishPay() {
		if (paymentStatus == PaymentStatus.paid) {
			return true;
		}
		return false;
	}

	/**
	 * 设置是否完成支付
	 * 
	 * @param isfinishPay
	 *            the isfinishPay to set
	 */
	public void setIsfinishPay(Boolean isfinishPay) {
		this.isfinishPay = isfinishPay;
	}

}