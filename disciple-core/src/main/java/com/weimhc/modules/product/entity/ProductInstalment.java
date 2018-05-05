/**
 * 
 */
package com.weimhc.modules.product.entity;

import java.math.BigDecimal;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.javamg.common.persistence.DataEntity;

/**
 * 商品分期信息Entity
 * 
 * @author lc
 * @version 2017-06-07
 */
public class ProductInstalment extends DataEntity<ProductInstalment> {

	private static final long serialVersionUID = 1L;
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
	 */
	private Integer periods;

	public ProductInstalment() {
		super();
	}

	public ProductInstalment(String id) {
		super(id);
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
	 * @return the periods
	 */
	public Integer getPeriods() {
		return periods;
	}

	/**
	 * @param periods
	 *            the periods to set
	 */
	public void setPeriods(Integer periods) {
		this.periods = periods;
	}

}