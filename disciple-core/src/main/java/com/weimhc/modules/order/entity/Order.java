/**
 * 
 */
package com.weimhc.modules.order.entity;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.thinkgem.javamg.common.persistence.DataEntity;
import com.weimhc.modules.base.entity.Region;
import com.weimhc.modules.member.entity.Member;
import com.weimhc.modules.payment.entity.PaymentMethod;

/**
 * 订单Entity
 * 
 * @author lc
 * @version 2017-01-03
 */
public class Order extends DataEntity<Order> {

	private static final long serialVersionUID = 1L;

	public static final Integer EXPIRE_MINUTES = 30;
	/**
	 * 订单编号
	 * 
	 */
	private String sn;
	/**
	 * 会员id
	 * 
	 */
	private Member member;
	/**
	 * 会员昵称
	 * 
	 */
	private String memberNickname;
	/**
	 * 详细地址
	 * 
	 */
	private String detailedAddress;
	/**
	 * 联系人名称
	 * 
	 */
	private String contactName;
	/**
	 * 手机
	 * 
	 */
	private String mobile;
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
	 * 运费
	 * 
	 */
	private BigDecimal freight;
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
	 * 支付状态
	 * 
	 */
	private PaymentStatus paymentStatus;

	/**
	 * 配送状态
	 * 
	 */
	private ShippingStatus shippingStatus;
	/**
	 * 支付时间
	 * 
	 */
	private Date paymentDate;
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
	 * 订单主题
	 */
	private String subject;
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
	 * 收货地区
	 */
	private Region area;
	/**
	 * 是否使用优惠券
	 */
	private Boolean isUseCoupons;

	private List<OrderItem> orderItems;

	public Order() {
		super();
	}

	public Order(String id) {
		super(id);
	}

	/**
	 * 获取订单编号
	 * 
	 * @return 订单编号
	 */
	@Length(min = 1, max = 100)
	public String getSn() {
		return sn;
	}

	/**
	 * 设置订单编号
	 * 
	 * @param sn
	 *            订单编号
	 */
	public void setSn(String sn) {
		this.sn = sn;
	}

	/**
	 * 获取会员
	 * 
	 * @return 会员
	 */
	public Member getMember() {
		return member;
	}

	/**
	 * 设置会员
	 * 
	 * @param member
	 *            会员
	 */
	public void setMember(Member member) {
		this.member = member;
	}

	/**
	 * 获取会员昵称
	 * 
	 * @return 会员昵称
	 */
	@Length(min = 1, max = 64)
	public String getMemberNickname() {
		return memberNickname;
	}

	/**
	 * 设置会员昵称
	 * 
	 * @param memberNickname
	 *            会员昵称
	 */
	public void setMemberNickname(String memberNickname) {
		this.memberNickname = memberNickname;
	}

	/**
	 * 获取详细地址
	 * 
	 * @return 详细地址
	 */
	@Length(min = 0, max = 64)
	public String getDetailedAddress() {
		return detailedAddress;
	}

	/**
	 * 设置详细地址
	 * 
	 * @param detailedAddress
	 *            详细地址
	 */
	public void setDetailedAddress(String detailedAddress) {
		this.detailedAddress = detailedAddress;
	}

	/**
	 * 获取联系人名称
	 * 
	 * @return 联系人名称
	 */
	@Length(min = 1, max = 64)
	public String getContactName() {
		return contactName;
	}

	/**
	 * 设置联系人名称
	 * 
	 * @param contactName
	 *            联系人名称
	 */
	public void setContactName(String contactName) {
		this.contactName = contactName;
	}

	/**
	 * 获取手机
	 * 
	 * @return 手机
	 */
	@Length(min = 1, max = 64)
	public String getMobile() {
		return mobile;
	}

	/**
	 * 设置手机
	 * 
	 * @param mobile
	 *            手机
	 */
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	/**
	 * 获取到期事假
	 * 
	 * @return 到期事假
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

	/**
	 * 获取订单状态
	 * 
	 * @return 订单状态
	 */
	public OrderStatus getStatus() {
		return status;
	}

	/**
	 * 设置订单状态
	 * 
	 * @param status
	 *            订单状态
	 */
	public void setStatus(OrderStatus status) {
		this.status = status;
	}

	/**
	 * 获取运费
	 * 
	 * @return 运费
	 */
	@NotNull()
	public BigDecimal getFreight() {
		return freight;
	}

	/**
	 * 设置运费
	 * 
	 * @param freight
	 *            运费
	 */
	public void setFreight(BigDecimal freight) {
		this.freight = freight;
	}

	/**
	 * 获取订单应付价格
	 * 
	 * @return 订单应付价格
	 */
	@NotNull()
	public BigDecimal getAmountPayable() {
		return amountPayable;
	}

	/**
	 * 设置订单应付价格
	 * 
	 * @param amountPayable
	 *            订单应付价格
	 */
	public void setAmountPayable(BigDecimal amountPayable) {
		this.amountPayable = amountPayable;
	}

	/**
	 * 获取总金额
	 * 
	 * @return 总金额
	 */
	@NotNull()
	public BigDecimal getTotalAmount() {
		return totalAmount;
	}

	/**
	 * 设置总金额
	 * 
	 * @param totalAmount
	 *            总金额
	 */
	public void setTotalAmount(BigDecimal totalAmount) {
		this.totalAmount = totalAmount;
	}

	/**
	 * 获取支付状态
	 * 
	 * @return 支付状态
	 */
	public PaymentStatus getPaymentStatus() {
		return paymentStatus;
	}

	/**
	 * 设置支付状态
	 * 
	 * @param paymentStatus
	 *            支付状态
	 */
	public void setPaymentStatus(PaymentStatus paymentStatus) {
		this.paymentStatus = paymentStatus;
	}

	/**
	 * 获取支付时间
	 * 
	 * @return 支付时间
	 */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@NotNull()
	public Date getPaymentDate() {
		return paymentDate;
	}

	/**
	 * 设置支付时间
	 * 
	 * @param paymentDate
	 *            支付时间
	 */
	public void setPaymentDate(Date paymentDate) {
		this.paymentDate = paymentDate;
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
	 * 获取订单备注
	 * 
	 * @return 订单备注
	 */
	@Length(min = 0, max = 255)
	public String getOrderRemarks() {
		return orderRemarks;
	}

	/**
	 * 设置订单备注
	 * 
	 * @param orderRemarks
	 *            订单备注
	 */
	public void setOrderRemarks(String orderRemarks) {
		this.orderRemarks = orderRemarks;
	}

	/**
	 * 获取配送状态
	 * 
	 * @return 配送状态
	 */
	public ShippingStatus getShippingStatus() {
		return shippingStatus;
	}

	/**
	 * 设置配送状态
	 * 
	 * @param shippingStatus
	 *            配送状态
	 */
	public void setShippingStatus(ShippingStatus shippingStatus) {
		this.shippingStatus = shippingStatus;
	}

	/**
	 * 返回 收货地区
	 * 
	 * @return the area
	 */
	public Region getArea() {
		return area;
	}

	/**
	 * 设置 收货地区
	 * 
	 * @param area
	 *            the area to set
	 */
	public void setArea(Region area) {
		this.area = area;
	}

	/**
	 * 获取是否分期订单
	 * 
	 * @return the isInstalmentOrder
	 */
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
	 * @return the orderItems
	 */
	public List<OrderItem> getOrderItems() {
		return orderItems;
	}

	/**
	 * @param orderItems
	 *            the orderItems to set
	 */
	public void setOrderItems(List<OrderItem> orderItems) {
		this.orderItems = orderItems;
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

	/**
	 * 获取订单主题
	 * 
	 * @return the subject
	 */
	public String getSubject() {
		return subject;
	}

	/**
	 * 设置订单主题
	 * 
	 * @param subject
	 *            the subject to set
	 */
	public void setSubject(String subject) {
		this.subject = subject;
	}

}