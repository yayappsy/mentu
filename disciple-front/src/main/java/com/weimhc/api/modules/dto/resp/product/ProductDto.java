/**
 * 
 */
package com.weimhc.api.modules.dto.resp.product;

import com.weimhc.framework.dto.AbstractIdDto;
import com.weimhc.framework.utils.UploadUtils;
import com.weimhc.modules.product.entity.ProductCategory;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;

/**
 * 商品信息 DTO
 * 
 * @author zsm
 * @version 2016-10-05
 */
public class ProductDto extends AbstractIdDto {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6707586013228500755L;
	/**
	 * 商品名称
	 * 
	 */
	private String name;
	/**
	 * 平台编号
	 * 
	 */
	private String sn;
	/**
	 * 商品副标题
	 * 
	 */
	private String subtitle;
	/**
	 * 商品分类
	 * 
	 */
	private ProductCategory productCategory;
	/**
	 * 商品分类名称
	 * 
	 */
	private String productCategoryName;
	/**
	 * 市场价
	 * 
	 */
	private BigDecimal marketPrice;
	/**
	 * 销售价
	 * 
	 */
	private BigDecimal salesPrice;
	/**
	 * 主图
	 * 
	 */
	private String defaultImage;
	/**
	 * 多图
	 * 
	 */
	private String imageMore;
	/**
	 * 商品介绍
	 * 
	 */
	private String introduction;
	/**
	 * 商品详细信息
	 * 
	 */
	private String body;
	/**
	 * 是否支持分期
	 */
	private Boolean isInstalment;

	/**
	 * 赠品
	 * 
	 */
	private String gift;

	/**
	 * 获取商品名称
	 * 
	 * @return the name
	 */
	@ApiModelProperty(value = "商品名称")
	public String getName() {
		return name;
	}

	/**
	 * 设置商品名称
	 * 
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 获取平台编号
	 * 
	 * @return the sn
	 */
	@ApiModelProperty(value = "sn")
	public String getSn() {
		return sn;
	}

	/**
	 * 设置平台编号
	 * 
	 * @param sn
	 *            the sn to set
	 */
	public void setSn(String sn) {
		this.sn = sn;
	}

	/**
	 * 获取商品副标题
	 * 
	 * @return the subtitle
	 */
	@ApiModelProperty(value = "商品副标题")
	public String getSubtitle() {
		return subtitle;
	}

	/**
	 * 设置商品副标题
	 * 
	 * @param subtitle
	 *            the subtitle to set
	 */
	public void setSubtitle(String subtitle) {
		this.subtitle = subtitle;
	}

	/**
	 * 获取商品分类
	 * 
	 * @return the productCategory
	 */
	public ProductCategory getProductCategory() {
		return productCategory;
	}

	/**
	 * 设置商品分类
	 * 
	 * @param productCategory
	 *            the productCategory to set
	 */
	public void setProductCategory(ProductCategory productCategory) {
		this.productCategory = productCategory;
	}

	/**
	 * 获取商品分类名称
	 * 
	 * @return the productCategoryName
	 */
	@ApiModelProperty(value = "商品分类名称")
	public String getProductCategoryName() {
		return productCategoryName;
	}

	/**
	 * 设置商品分类名称
	 * 
	 * @param productCategoryName
	 *            the productCategoryName to set
	 */
	public void setProductCategoryName(String productCategoryName) {
		this.productCategoryName = productCategoryName;
	}

	/**
	 * 获取市场价
	 * 
	 * @return the marketPrice
	 */
	@ApiModelProperty(value = "市场价")
	public BigDecimal getMarketPrice() {
		return marketPrice;
	}

	/**
	 * 设置市场价
	 * 
	 * @param marketPrice
	 *            the marketPrice to set
	 */
	public void setMarketPrice(BigDecimal marketPrice) {
		this.marketPrice = marketPrice;
	}

	/**
	 * 获取销售价
	 * 
	 * @return the salesPrice
	 */
	@ApiModelProperty(value = "销售价")
	public BigDecimal getSalesPrice() {
		return salesPrice;
	}

	/**
	 * 设置销售价
	 * 
	 * @param salesPrice
	 *            the salesPrice to set
	 */
	public void setSalesPrice(BigDecimal salesPrice) {
		this.salesPrice = salesPrice;
	}

	/**
	 * 获取主图
	 * 
	 * @return the defaultImage
	 */
	@ApiModelProperty(value = "主图")
	public String getDefaultImage() {
		if (StringUtils.isNotBlank(defaultImage)) {
			return UploadUtils.getAccessUrl(defaultImage);
		}
		return "";
	}

	/**
	 * 设置主图
	 * 
	 * @param defaultImage
	 *            the defaultImage to set
	 */
	public void setDefaultImage(String defaultImage) {
		this.defaultImage = defaultImage;
	}

	/**
	 * 获取多图
	 * 
	 * @return the imageMore
	 */
	@ApiModelProperty(value = "多图")
	public String getImageMore() {
		return imageMore;
	}

	/**
	 * 设置多图
	 * 
	 * @param imageMore
	 *            the imageMore to set
	 */
	public void setImageMore(String imageMore) {
		this.imageMore = imageMore;
	}

	/**
	 * 获取商品介绍
	 * 
	 * @return the introduction
	 */
	@ApiModelProperty(value = "商品介绍")
	public String getIntroduction() {
		return introduction;
	}

	/**
	 * 设置商品介绍
	 * 
	 * @param introduction
	 *            the introduction to set
	 */
	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}

	/**
	 * 获取商品详细信息
	 * 
	 * @return the body
	 */
	@ApiModelProperty(value = "商品详细信息")
	public String getBody() {
		return body;
	}

	/**
	 * 设置商品详细信息
	 * 
	 * @param body
	 *            the body to set
	 */
	public void setBody(String body) {
		this.body = body;
	}

	/**
	 * 获取是否支持分期
	 * 
	 * @return the isInstalment
	 */
	@ApiModelProperty(value = "是否支持分期")
	public Boolean getIsInstalment() {
		return isInstalment;
	}

	/**
	 * 设置是否支持分期
	 * 
	 * @param isInstalment
	 *            the isInstalment to set
	 */
	public void setIsInstalment(Boolean isInstalment) {
		this.isInstalment = isInstalment;
	}

	/**
	 * 获取赠品
	 * 
	 * @return the gift
	 */
	@ApiModelProperty(value = "赠品")
	public String getGift() {
		return gift;
	}

	/**
	 * 设置赠品
	 * 
	 * @param gift
	 *            the gift to set
	 */
	public void setGift(String gift) {
		this.gift = gift;
	}

}