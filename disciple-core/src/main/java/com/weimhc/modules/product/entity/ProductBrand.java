/**
 * 
 */
package com.weimhc.modules.product.entity;

import org.hibernate.validator.constraints.Length;

import com.weimhc.framework.persistence.SortableEntity;
import com.weimhc.modules.property.entity.PropertyTemplate;

/**
 * 品牌系列Entity
 * 
 * @author zsm
 * @version 2016-02-01
 */
public class ProductBrand extends SortableEntity<ProductBrand> {

	private static final long serialVersionUID = 1L;
	private ProductCategory productCategory; // 商品分类
	private String name; // 中文名称
	private String englishName; // 英文名称
	private String logoUrl; // 品牌Logo地址
	private String siteUrl; // 品牌官网
	private String description; // 描述
	private String status; // 状态
	private Boolean isRecommend; // 是否推荐

	private PropertyTemplate propertyTemplate; // 根据商品类型查询相关的品牌系列

	public ProductBrand() {
		super();
	}

	public ProductBrand(String id) {
		super(id);
	}

	/**
	 * 获取商品分类
	 * 
	 * @return 商品分类
	 */
	public ProductCategory getProductCategory() {
		return productCategory;
	}

	/**
	 * 设置商品分类
	 * 
	 * @param productCategory
	 *            商品分类
	 */
	public void setProductCategory(ProductCategory productCategory) {
		this.productCategory = productCategory;
	}

	/**
	 * 获取中文名称
	 * 
	 * @return 中文名称
	 */
	@Length(min = 0, max = 255)
	public String getName() {
		return name;
	}

	/**
	 * 设置中文名称
	 * 
	 * @param name
	 *            中文名称
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 获取英文名称
	 * 
	 * @return 英文名称
	 */
	@Length(min = 0, max = 255)
	public String getEnglishName() {
		return englishName;
	}

	/**
	 * 设置英文名称
	 * 
	 * @param englishName
	 *            英文名称
	 */
	public void setEnglishName(String englishName) {
		this.englishName = englishName;
	}

	/**
	 * 获取品牌Logo地址
	 * 
	 * @return 品牌Logo地址
	 */
	@Length(min = 0, max = 255)
	public String getLogoUrl() {
		return logoUrl;
	}

	/**
	 * 设置品牌Logo地址
	 * 
	 * @param logoUrl
	 *            品牌Logo地址
	 */
	public void setLogoUrl(String logoUrl) {
		this.logoUrl = logoUrl;
	}

	/**
	 * 获取品牌官网
	 * 
	 * @return 品牌官网
	 */
	@Length(min = 0, max = 255)
	public String getSiteUrl() {
		return siteUrl;
	}

	/**
	 * 设置品牌官网
	 * 
	 * @param siteUrl
	 *            品牌官网
	 */
	public void setSiteUrl(String siteUrl) {
		this.siteUrl = siteUrl;
	}

	/**
	 * 获取描述
	 * 
	 * @return 描述
	 */
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
	 * 获取状态
	 * 
	 * @return 状态
	 */
	@Length(min = 0, max = 25)
	public String getStatus() {
		return status;
	}

	/**
	 * 设置状态
	 * 
	 * @param status
	 *            状态
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * 获取是否推荐
	 * 
	 * @return 是否推荐
	 */
	public Boolean getIsRecommend() {
		return isRecommend;
	}

	/**
	 * 设置是否推荐
	 * 
	 * @param isRecommend
	 *            是否推荐
	 */
	public void setIsRecommend(Boolean isRecommend) {
		this.isRecommend = isRecommend;
	}

	/**
	 * 获取商品类型
	 * 
	 * @return the productType
	 */
	public PropertyTemplate getProductType() {
		return propertyTemplate;
	}

	/**
	 * 设置商品类型
	 * 
	 * @param propertyTemplate
	 *            the productType to set
	 */
	public void setProductType(PropertyTemplate propertyTemplate) {
		this.propertyTemplate = propertyTemplate;
	}

}