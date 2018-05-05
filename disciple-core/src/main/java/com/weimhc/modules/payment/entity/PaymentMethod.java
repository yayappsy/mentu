/**
 * 
 */
package com.weimhc.modules.payment.entity;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.javamg.common.persistence.DataEntity;

/**
 * 支付方式Entity 在线支付，货到付款等
 * 
 * @author zsm
 * @version 2016-02-17
 */
public class PaymentMethod extends DataEntity<PaymentMethod> {

	private static final long serialVersionUID = 1L;
	private String name; // 支付方式名称
	private String description; // 支付方式描述
	private String icon; // 支付方式图标
	private Method mentod; // 支付方式
	private Integer timeout; // 超时时间

	private String content; // 支付方式内容
	private String sort; // sort

	/**
	 * 方式
	 */
	public enum Method {

		/** 在线支付 */
		online,

		/** 线下支付 */
		offline
	};

	public PaymentMethod() {
		super();
	}

	public PaymentMethod(String id) {
		super(id);
	}

	/**
	 * 获取支付方式名称
	 * 
	 * @return 支付方式名称
	 */
	@Length(min = 1, max = 255)
	public String getName() {
		return name;
	}

	/**
	 * 设置支付方式名称
	 * 
	 * @param name
	 *            支付方式名称
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 获取支付方式描述
	 * 
	 * @return 支付方式描述
	 */
	@Length(min = 0, max = 255)
	public String getDescription() {
		return description;
	}

	/**
	 * 设置支付方式描述
	 * 
	 * @param description
	 *            支付方式描述
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * 获取支付方式图标
	 * 
	 * @return 支付方式图标
	 */
	@Length(min = 0, max = 255)
	public String getIcon() {
		return icon;
	}

	/**
	 * 设置支付方式图标
	 * 
	 * @param icon
	 *            支付方式图标
	 */
	public void setIcon(String icon) {
		this.icon = icon;
	}

	/**
	 * 获取支付方式
	 * 
	 * @return 支付方式
	 */
	public Method getMethod() {
		return mentod;
	}

	/**
	 * 设置支付方式
	 * 
	 * @param mentod
	 *            支付方式
	 */
	public void setMethod(Method mentod) {
		this.mentod = mentod;
	}

	/**
	 * 获取超时时间
	 * 
	 * @return 超时时间
	 */
	public Integer getTimeout() {
		return timeout;
	}

	/**
	 * 设置超时时间
	 * 
	 * @param timeout
	 *            超时时间
	 */
	public void setTimeout(Integer timeout) {
		this.timeout = timeout;
	}

	/**
	 * 获取支付方式内容
	 * 
	 * @return 支付方式内容
	 */
	public String getContent() {
		return content;
	}

	/**
	 * 设置支付方式内容
	 * 
	 * @param content
	 *            支付方式内容
	 */
	public void setContent(String content) {
		this.content = content;
	}

	/**
	 * 获取sort
	 * 
	 * @return sort
	 */
	@Length(min = 0, max = 11)
	public String getSort() {
		return sort;
	}

	/**
	 * 设置sort
	 * 
	 * @param sort
	 *            sort
	 */
	public void setSort(String sort) {
		this.sort = sort;
	}

}