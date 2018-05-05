/**
 * 
 */
package com.weimhc.modules.order.entity;

import java.math.BigDecimal;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.javamg.common.persistence.DataEntity;
import com.weimhc.modules.product.entity.Product;

/**
 * 订单项Entity
 * 
 * @author lc
 * @version 2017-01-03
 */
public class OrderItem extends DataEntity<OrderItem> {

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
	 * 评价图片
	 * 
	 */
	private String images;

	public OrderItem() {
		super();
	}

	public OrderItem(String id) {
		super(id);
	}

	/**
	 * @param order
	 */
	public OrderItem(Order order) {
		this.order = order;
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
	 * @param orderId
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
	 * @param productId
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
	@Length(min = 1, max = 100)
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
	 * 获取单价
	 * 
	 * @return 单价
	 */
	@NotNull()
	public BigDecimal getPrice() {
		return price;
	}

	/**
	 * 设置单价
	 * 
	 * @param price
	 *            单价
	 */
	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	/**
	 * 获取实际应付价格
	 * 
	 * @return 实际应付单价
	 */
	@NotNull()
	public BigDecimal getPricePayable() {
		return pricePayable;
	}

	/**
	 * 设置实际应付单价
	 * 
	 * @param pricePayable
	 *            实际应付单价
	 */
	public void setPricePayable(BigDecimal pricePayable) {
		this.pricePayable = pricePayable;
	}

	/**
	 * 获取数量
	 * 
	 * @return 数量
	 */
	@NotNull()
	public Integer getQuantity() {
		return quantity;
	}

	/**
	 * 设置数量
	 * 
	 * @param quantity
	 *            数量
	 */
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	/**
	 * 获取评价图片
	 * 
	 * @return 评价图片
	 */
	@Length(min = 1, max = 255)
	public String getImages() {
		return images;
	}

	/**
	 * 设置评价图片
	 * 
	 * @param images
	 *            评价图片
	 */
	public void setImages(String images) {
		this.images = images;
	}

}