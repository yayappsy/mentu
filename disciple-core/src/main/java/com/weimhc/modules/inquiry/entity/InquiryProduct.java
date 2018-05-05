/**
 * 
 */
package com.weimhc.modules.inquiry.entity;

import java.math.BigDecimal;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.javamg.common.persistence.DataEntity;
import com.weimhc.modules.product.entity.Product;

/**
 * 询价产品Entity
 * 
 * @author zsm
 * @version 2017-04-10
 */
public class InquiryProduct extends DataEntity<InquiryProduct> {

	private static final long serialVersionUID = 1L;
	/**
	 * 关联询价单
	 * 
	 */
	private InquirySheet inquirySheet;
	/**
	 * 询价产品
	 * 
	 */
	private Product product;
	/**
	 * 产品编号
	 * 
	 */
	private String productSn;
	/**
	 * 产品名称
	 * 
	 */
	private String productName;
	/**
	 * 销售价
	 * 
	 */
	private BigDecimal salesPrice;
	/**
	 * 期望单价
	 * 
	 */
	private BigDecimal expectedPrice;
	/**
	 * 预购数量
	 * 
	 */
	private Integer preOrderQuantity;

	public InquiryProduct() {
		super();
	}

	public InquiryProduct(String id) {
		super(id);
	}

	public InquiryProduct(InquirySheet inquirySheet) {
		super();
		this.inquirySheet = inquirySheet;
	}

	/**
	 * 获取关联询价单
	 * 
	 * @return 关联询价单
	 */
	@NotNull
	public InquirySheet getInquirySheet() {
		return inquirySheet;
	}

	/**
	 * 设置关联询价单
	 * 
	 * @param inquirySheet
	 *            关联询价单
	 */
	public void setInquirySheet(InquirySheet inquirySheet) {
		this.inquirySheet = inquirySheet;
	}

	/**
	 * 获取询价产品
	 * 
	 * @return 询价产品
	 */
	@NotNull
	public Product getProduct() {
		return product;
	}

	/**
	 * 设置询价产品
	 * 
	 * @param product
	 *            询价产品
	 */
	public void setProduct(Product product) {
		this.product = product;
	}

	/**
	 * 获取产品名称
	 * 
	 * @return 产品名称
	 */
	@Length(min = 1, max = 100)
	public String getProductName() {
		return productName;
	}

	/**
	 * 设置产品名称
	 * 
	 * @param productName
	 *            产品名称
	 */
	public void setProductName(String productName) {
		this.productName = productName;
	}

	/**
	 * 获取销售价
	 * 
	 * @return 销售价
	 */
	@NotNull()
	public BigDecimal getSalesPrice() {
		return salesPrice;
	}

	/**
	 * 设置销售价
	 * 
	 * @param salesPrice
	 *            销售价
	 */
	public void setSalesPrice(BigDecimal salesPrice) {
		this.salesPrice = salesPrice;
	}

	/**
	 * 获取期望单价
	 * 
	 * @return 期望单价
	 */
	public BigDecimal getExpectedPrice() {
		return expectedPrice;
	}

	/**
	 * 设置期望单价
	 * 
	 * @param expectedPrice
	 *            期望单价
	 */
	public void setExpectedPrice(BigDecimal expectedPrice) {
		this.expectedPrice = expectedPrice;
	}

	/**
	 * 获取预购数量
	 * 
	 * @return 预购数量
	 */
	@NotNull()
	public Integer getPreOrderQuantity() {
		return preOrderQuantity;
	}

	/**
	 * 设置预购数量
	 * 
	 * @param preOrderQuantity
	 *            预购数量
	 */
	public void setPreOrderQuantity(Integer preOrderQuantity) {
		this.preOrderQuantity = preOrderQuantity;
	}

	/**
	 * 返回 产品编号
	 * 
	 * @return the productSn
	 */
	public String getProductSn() {
		return productSn;
	}

	/**
	 * 设置 产品编号
	 * 
	 * @param productSn
	 *            the productSn to set
	 */
	public void setProductSn(String productSn) {
		this.productSn = productSn;
	}

}