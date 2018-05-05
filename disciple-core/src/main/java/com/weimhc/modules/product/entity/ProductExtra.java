/**
 * 
 */
package com.weimhc.modules.product.entity;

import com.thinkgem.javamg.common.persistence.DataEntity;

/**
 * 商品附加信息Entity
 * 
 * @author lc
 * @version 2017-06-07
 */
public class ProductExtra extends DataEntity<ProductExtra> {

	private static final long serialVersionUID = 1L;
	/**
	 * 商品
	 * 
	 */
	private Product product;

	/**
	 * 会员是否长期有效
	 */
	private Boolean isForever;

	public ProductExtra() {
		super();
	}

	public ProductExtra(String id) {
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

}