/**
 * 
 */
package com.weimhc.modules.order.entity;

import java.math.BigDecimal;
import java.util.Date;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.thinkgem.javamg.common.persistence.DataEntity;
import com.weimhc.modules.product.entity.Product;

/**
 * 分期订单Entity
 * 
 * @author lc
 * @version 2017-06-14
 */
public class OrderInstalment extends DataEntity<OrderInstalment> {

	private static final long serialVersionUID = 1L;
	/**
	 * 订单
	 * 
	 */
	private Order order;
	/**
	 * 商品
	 * 
	 */
	private Product product;
	/**
	 * 商品名称
	 * 
	 */
	private String productName;
	/**
	 * 名称
	 * 
	 */
	private String name;
	/**
	 * 描述
	 * 
	 */
	private String description;
	/**
	 * 分期金额
	 * 
	 */
	private BigDecimal price;
	/**
	 * 当前期数
	 * 
	 */
	private Integer periods;
	/**
	 * 是否已经还请
	 * 
	 */
	private boolean isOver;
	/**
	 * 到期时间
	 * 
	 */
	private Date expire;

	public OrderInstalment() {
		super();
	}

	public OrderInstalment(String id) {
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
	 * 获取商品
	 * 
	 * @return 商品
	 */
	public Product getProduct() {
		return product;
	}

	/**
	 * 设置商品
	 * 
	 * @param product
	 *            商品
	 */
	public void setProduct(Product product) {
		this.product = product;
	}

	/**
	 * 获取商品名称
	 * 
	 * @return 商品名称
	 */
	@Length(min = 0, max = 64)
	public String getProductName() {
		return productName;
	}

	/**
	 * 设置商品名称
	 * 
	 * @param productName
	 *            商品名称
	 */
	public void setProductName(String productName) {
		this.productName = productName;
	}

	/**
	 * 获取名称
	 * 
	 * @return 名称
	 */
	@Length(min = 0, max = 100)
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
	 * 获取描述
	 * 
	 * @return 描述
	 */
	@Length(min = 0, max = 255)
	public String getDescription() {
		return description;
	}

	/**
	 * 设置描述
	 * 
	 * @param description
	 *            描述
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * 获取分期金额
	 * 
	 * @return 分期金额
	 */
	public BigDecimal getPrice() {
		return price;
	}

	/**
	 * 设置分期金额
	 * 
	 * @param price
	 *            分期金额
	 */
	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	/**
	 * 获取是否已经还请
	 * 
	 * @return 是否已经还请
	 */
	@Length(min = 0, max = 1)
	public boolean getIsOver() {
		return isOver;
	}

	/**
	 * 设置是否已经还请
	 * 
	 * @param isOver
	 *            是否已经还请
	 */
	public void setIsOver(boolean isOver) {
		this.isOver = isOver;
	}

	/**
	 * 获取到期时间
	 * 
	 * @return 到期时间
	 */
	@JsonFormat(pattern = "yyyy-MM-dd")
	public Date getExpire() {
		return expire;
	}

	/**
	 * 设置到期时间
	 * 
	 * @param expire
	 *            到期时间
	 */
	public void setExpire(Date expire) {
		this.expire = expire;
	}

	/**
	 * 获取当前期数
	 * 
	 * @return the periods
	 */
	public Integer getPeriods() {
		return periods;
	}

	/**
	 * 设置当前期数
	 * 
	 * @param periods
	 *            the periods to set
	 */
	public void setPeriods(Integer periods) {
		this.periods = periods;
	}

}