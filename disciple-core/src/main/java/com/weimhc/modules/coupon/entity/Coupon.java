/**
 * 
 */
package com.weimhc.modules.coupon.entity;

import java.math.BigDecimal;
import java.util.Date;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.thinkgem.javamg.common.persistence.DataEntity;

/**
 * 优惠券Entity
 * 
 * @author lc
 * @version 2017-06-07
 */
public class Coupon extends DataEntity<Coupon> {

	private static final long serialVersionUID = 1L;
	/**
	 * 名称
	 * 
	 */
	private String name;
	/**
	 * 介绍
	 * 
	 */
	private String description;
	/**
	 * 是否开启
	 * 
	 */
	private Boolean isEnabled;
	/**
	 * 兑换价格限制
	 * 
	 */
	private BigDecimal priceLimit;
	/**
	 * 价格表达式
	 * 
	 */
	private BigDecimal priceExpression;
	/**
	 * 兑换数量限制
	 * 
	 */
	private Integer exchangeLimit;
	/**
	 * 优惠券状态
	 * 
	 */
	private String status;
	/**
	 * 是否锁定
	 * 
	 */
	private Boolean isLocked;
	/**
	 * 优惠券数量
	 * 
	 */
	private Integer quantity;
	/**
	 * 兑换所需积分
	 * 
	 */
	private String point;
	/**
	 * 开始时间
	 * 
	 */
	private Date beginDate;
	/**
	 * 结束时间
	 * 
	 */
	private Date endDate;

	public Coupon() {
		super();
	}

	public Coupon(String id) {
		super(id);
	}

	/**
	 * 获取名称
	 * 
	 * @return 名称
	 */
	@Length(min = 1, max = 255)
	public String getName() {
		return name;
	}

	/**
	 * 设置名称
	 * 
	 * @param name
	 *            名称
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 获取介绍
	 * 
	 * @return 介绍
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * 设置介绍
	 * 
	 * @param description
	 *            介绍
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * 获取是否开启
	 * 
	 * @return 是否开启
	 */
	@NotNull()
	public Boolean getIsEnabled() {
		return isEnabled;
	}

	/**
	 * 设置是否开启
	 * 
	 * @param isEnabled
	 *            是否开启
	 */
	public void setIsEnabled(Boolean isEnabled) {
		this.isEnabled = isEnabled;
	}

	/**
	 * 获取兑换价格限制
	 * 
	 * @return 兑换价格限制
	 */
	@NotNull()
	public BigDecimal getPriceLimit() {
		return priceLimit;
	}

	/**
	 * 设置兑换价格限制
	 * 
	 * @param priceLimit
	 *            兑换价格限制
	 */
	public void setPriceLimit(BigDecimal priceLimit) {
		this.priceLimit = priceLimit;
	}

	/**
	 * 获取价格表达式
	 * 
	 * @return 价格表达式
	 */
	public BigDecimal getPriceExpression() {
		return priceExpression;
	}

	/**
	 * 设置价格表达式
	 * 
	 * @param priceExpression
	 *            价格表达式
	 */
	public void setPriceExpression(BigDecimal priceExpression) {
		this.priceExpression = priceExpression;
	}

	/**
	 * 获取兑换数量限制
	 * 
	 * @return 兑换数量限制
	 */
	public Integer getExchangeLimit() {
		return exchangeLimit;
	}

	/**
	 * 设置兑换数量限制
	 * 
	 * @param exchangeLimit
	 *            兑换数量限制
	 */
	public void setExchangeLimit(Integer exchangeLimit) {
		this.exchangeLimit = exchangeLimit;
	}

	/**
	 * 获取优惠券状态
	 * 
	 * @return 优惠券状态
	 */
	@Length(min = 0, max = 25)
	public String getStatus() {
		return status;
	}

	/**
	 * 设置优惠券状态
	 * 
	 * @param status
	 *            优惠券状态
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * 获取是否锁定
	 * 
	 * @return 是否锁定
	 */
	public Boolean getIsLocked() {
		return isLocked;
	}

	/**
	 * 设置是否锁定
	 * 
	 * @param isLocked
	 *            是否锁定
	 */
	public void setIsLocked(Boolean isLocked) {
		this.isLocked = isLocked;
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

	/**
	 * 获取兑换所需积分
	 * 
	 * @return 兑换所需积分
	 */
	@Length(min = 0, max = 64)
	public String getPoint() {
		return point;
	}

	/**
	 * 设置兑换所需积分
	 * 
	 * @param point
	 *            兑换所需积分
	 */
	public void setPoint(String point) {
		this.point = point;
	}

	/**
	 * 获取开始时间
	 * 
	 * @return 开始时间
	 */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getBeginDate() {
		return beginDate;
	}

	/**
	 * 设置开始时间
	 * 
	 * @param beginDate
	 *            开始时间
	 */
	public void setBeginDate(Date beginDate) {
		this.beginDate = beginDate;
	}

	/**
	 * 获取结束时间
	 * 
	 * @return 结束时间
	 */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getEndDate() {
		return endDate;
	}

	/**
	 * 设置结束时间
	 * 
	 * @param endDate
	 *            结束时间
	 */
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

}