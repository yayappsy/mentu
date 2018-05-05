/**
 * 
 */
package com.weimhc.api.modules.dto.req.order;

import com.weimhc.framework.dto.req.AbstractRQ;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;

/**
 * 订单项 请求
 * 
 * @author zsm
 * @version 2016-10-05
 */
public class OrderItemRQ extends AbstractRQ {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 商品id
	 * 
	 */
	private String productId;
	/**
	 * 单价
	 * 
	 */
	private BigDecimal price;
	/**
	 * 实际应付单价
	 * 
	 */
	private BigDecimal pricePayable;
	/**
	 * 数量
	 * 
	 */
	private Integer quantity;

	/**
	 * 获取商品id
	 * 
	 * @return the productId
	 */
	@ApiModelProperty(value = "商品id")
	public String getProductId() {
		return productId;
	}

	/**
	 * 设置商品id
	 * 
	 * @param productId
	 *            the productId to set
	 */
	public void setProductId(String productId) {
		this.productId = productId;
	}

	/**
	 * 获取单价
	 * 
	 * @return the price
	 */
	@ApiModelProperty(value = "单价")
	public BigDecimal getPrice() {
		return price;
	}

	/**
	 * 设置单价
	 * 
	 * @param price
	 *            the price to set
	 */
	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	/**
	 * 获取实际应付单价
	 * 
	 * @return the pricePayable
	 */
	@ApiModelProperty(value = "实际应付单价")
	public BigDecimal getPricePayable() {
		return pricePayable;
	}

	/**
	 * 设置实际应付单价
	 * 
	 * @param pricePayable
	 *            the pricePayable to set
	 */
	public void setPricePayable(BigDecimal pricePayable) {
		this.pricePayable = pricePayable;
	}

	/**
	 * 获取数量
	 * 
	 * @return the quantity
	 */
	@ApiModelProperty(value = "数量")
	public Integer getQuantity() {
		return quantity;
	}

	/**
	 * 设置数量
	 * 
	 * @param quantity
	 *            the quantity to set
	 */
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

}