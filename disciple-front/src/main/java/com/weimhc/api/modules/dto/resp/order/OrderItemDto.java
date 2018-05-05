/**
 * 
 */
package com.weimhc.api.modules.dto.resp.order;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.weimhc.framework.dto.AbstractDto;
import com.weimhc.modules.product.entity.Product;
import io.swagger.annotations.ApiModelProperty;
import springfox.documentation.annotations.ApiIgnore;

import java.math.BigDecimal;

/**
 * 预约DTO
 * 
 * @author lc
 * @version 2016-12-15
 */
public class OrderItemDto extends AbstractDto {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1095501047336970672L;

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
	 * 获取商品
	 * 
	 * @return the product
	 */
	@ApiIgnore
	@JsonIgnore
	public Product getProduct() {
		return product;
	}

	/**
	 * 设置商品
	 * 
	 * @param product
	 *            the product to set
	 */
	public void setProduct(Product product) {
		this.product = product;
	}

	/**
	 * 获取商品名称
	 * 
	 * @return the productName
	 */
	@ApiModelProperty(value = "商品名称")
	public String getProductName() {
		return productName;
	}

	/**
	 * 设置商品名称
	 * 
	 * @param productName
	 *            the productName to set
	 */
	public void setProductName(String productName) {
		this.productName = productName;
	}

	/**
	 * 获取商品价格
	 * 
	 * @return the price
	 */
	@ApiModelProperty(value = "商品价格")
	public BigDecimal getPrice() {
		return price;
	}

	/**
	 * 设置商品价格
	 * 
	 * @param price
	 *            the price to set
	 */
	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	/**
	 * 获取实际应付价格
	 * 
	 * @return the pricePayable
	 */
	@ApiModelProperty(value = "实际应付价格")
	public BigDecimal getPricePayable() {
		return pricePayable;
	}

	/**
	 * 设置实际应付价格
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