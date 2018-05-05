/**
 * 
 */
package com.weimhc.modules.product.entity;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.common.base.Function;
import com.google.common.collect.Maps;
import com.thinkgem.javamg.common.persistence.DataEntity;
import com.thinkgem.javamg.common.utils.StringUtils;
import com.weimhc.modules.base.entity.Region;
import com.weimhc.modules.property.entity.PropertyTemplate;

/**
 * 商品Entity
 * 
 * @author lc
 * @version 2017-01-03
 */
public class Product extends DataEntity<Product> {

	private static final long serialVersionUID = 1L;
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
	 * 属性模板
	 * 
	 */
	private PropertyTemplate propertyTemplate;
	/**
	 * 属性模板名称
	 * 
	 */
	private String propertyTemplateName;

	/**
	 * 商品品牌
	 */
	private ProductBrand productBrand;
	/**
	 * 商品品牌名称
	 */
	private String productBrandName;

	/**
	 * 商品货号
	 * 
	 */
	private String articleNumber;
	/**
	 * 成本价
	 * 
	 */
	private BigDecimal costPrice;
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
	 * 商品扩展属性值
	 * 
	 */
	private String propertyValues;
	/**
	 * 商品规格属性值
	 * 
	 */
	private String specificationPropertiesNames;
	/**
	 * 商品库存
	 * 
	 */
	private Integer stock;
	/**
	 * 是否为赠品
	 * 
	 */
	private Boolean isGift;
	/**
	 * 是否列出
	 * 
	 */
	private Boolean isList;
	/**
	 * 是否上架
	 * 
	 */
	private Boolean isMarketable;
	/**
	 * 是否置顶
	 * 
	 */
	private Boolean isTop;
	/**
	 * 是否推荐
	 */
	private Boolean isRecommend;
	/**
	 * 是否支持分期
	 */
	private Boolean isInstalment;
	/**
	 * 商品关键字
	 * 
	 */
	private String keyword;
	/**
	 * 商品发布状态
	 * 
	 */
	private String productReleaseStatus;
	/**
	 * 商品定时发布开始时间
	 * 
	 */
	private Date productReleaseBeginDate;
	/**
	 * 商品定时发布结束时间
	 * 
	 */
	private Date productReleaseEndDate;
	/**
	 * 商品状态
	 * 
	 */
	private String productStatus;
	/**
	 * 商品状态备注
	 * 
	 */
	private String productStatusRemark;
	/**
	 * 是否卖家承担运费
	 * 
	 */
	private Boolean isSellerBearFreight;
	/**
	 * 销量
	 * 
	 */
	private String sales;
	/**
	 * 评分
	 * 
	 */
	private String score;
	/**
	 * 总评分
	 * 
	 */
	private String totalScore;
	/**
	 * 评分数
	 * 
	 */
	private String scoreCount;
	/**
	 * 商品所在地
	 * 
	 */
	private Region area;
	/**
	 * 商品关联属性列表
	 */
	private List<ProductProperty> productPropertyList;

	private Map<String, ProductProperty> propValues = Maps.newHashMap();
	/**
	 * 商品关联属性 ，用于数据库插入
	 */
	private List<ProductProperty> productProperties;

	/**
	 * 商品关联分期 ，用于数据库插入
	 */
	private List<ProductInstalment> productInstalments;

	/**
	 * 商品附件信息
	 */
	private ProductExtra productExtra;

	private BigDecimal amount;

	private Integer count;

	private Date beginDate;

	private Date endDate;

	public Product() {
		super();
	}

	public Product(String id) {
		super(id);
	}

	/**
	 * 获取商品名称
	 * 
	 * @return 商品名称
	 */
	@Length(min = 1, max = 255)
	public String getName() {
		return name;
	}

	/**
	 * 设置商品名称
	 * 
	 * @param name
	 *            商品名称
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 获取平台编号
	 * 
	 * @return 平台编号
	 */
	@Length(min = 1, max = 255)
	public String getSn() {
		return sn;
	}

	/**
	 * 设置平台编号
	 * 
	 * @param sn
	 *            平台编号
	 */
	public void setSn(String sn) {
		this.sn = sn;
	}

	/**
	 * 获取商品副标题
	 * 
	 * @return 商品副标题
	 */
	@Length(min = 0, max = 255)
	public String getSubtitle() {
		return subtitle;
	}

	/**
	 * 设置商品副标题
	 * 
	 * @param subtitle
	 *            商品副标题
	 */
	public void setSubtitle(String subtitle) {
		this.subtitle = subtitle;
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
	 * 获取商品分类名称
	 * 
	 * @return 商品分类名称
	 */
	@Length(min = 1, max = 255)
	public String getProductCategoryName() {
		return productCategoryName;
	}

	/**
	 * 设置商品分类名称
	 * 
	 * @param productCategoryName
	 *            商品分类名称
	 */
	public void setProductCategoryName(String productCategoryName) {
		this.productCategoryName = productCategoryName;
	}

	/**
	 * 获取属性模板
	 * 
	 * @return 属性模板
	 */
	public PropertyTemplate getPropertyTemplate() {
		return propertyTemplate;
	}

	/**
	 * 设置属性模板
	 * 
	 * @param propertyTemplate
	 *            属性模板
	 */
	public void setPropertyTemplate(PropertyTemplate propertyTemplate) {
		this.propertyTemplate = propertyTemplate;
	}

	/**
	 * 获取属性模板名称
	 * 
	 * @return 属性模板名称
	 */
	@Length(min = 0, max = 255)
	public String getPropertyTemplateName() {
		return propertyTemplateName;
	}

	/**
	 * 设置属性模板名称
	 * 
	 * @param propertyTemplateName
	 *            属性模板名称
	 */
	public void setPropertyTemplateName(String propertyTemplateName) {
		this.propertyTemplateName = propertyTemplateName;
	}

	/**
	 * 获取商品货号
	 * 
	 * @return 商品货号
	 */
	@Length(min = 0, max = 64)
	public String getArticleNumber() {
		return articleNumber;
	}

	/**
	 * 设置商品货号
	 * 
	 * @param articleNumber
	 *            商品货号
	 */
	public void setArticleNumber(String articleNumber) {
		this.articleNumber = articleNumber;
	}

	/**
	 * 获取成本价
	 * 
	 * @return 成本价
	 */
	public BigDecimal getCostPrice() {
		return costPrice;
	}

	/**
	 * 设置成本价
	 * 
	 * @param costPrice
	 *            成本价
	 */
	public void setCostPrice(BigDecimal costPrice) {
		this.costPrice = costPrice;
	}

	/**
	 * 获取市场价
	 * 
	 * @return 市场价
	 */
	@NotNull()
	public BigDecimal getMarketPrice() {
		return marketPrice;
	}

	/**
	 * 设置市场价
	 * 
	 * @param marketPrice
	 *            市场价
	 */
	public void setMarketPrice(BigDecimal marketPrice) {
		this.marketPrice = marketPrice;
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
	 * 获取主图
	 * 
	 * @return 主图
	 */
	@Length(min = 0, max = 255)
	public String getDefaultImage() {
		return defaultImage;
	}

	/**
	 * 设置主图
	 * 
	 * @param defaultImage
	 *            主图
	 */
	public void setDefaultImage(String defaultImage) {
		this.defaultImage = defaultImage;
	}

	/**
	 * 获取多图
	 * 
	 * @return 多图
	 */
	@Length(min = 0, max = 255)
	public String getImageMore() {
		return imageMore;
	}

	/**
	 * 设置多图
	 * 
	 * @param imageMore
	 *            多图
	 */
	public void setImageMore(String imageMore) {
		this.imageMore = imageMore;
	}

	/**
	 * 获取商品介绍
	 * 
	 * @return 商品介绍
	 */
	public String getIntroduction() {
		return introduction;
	}

	/**
	 * 设置商品介绍
	 * 
	 * @param introduction
	 *            商品介绍
	 */
	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}

	/**
	 * 获取商品详细信息
	 * 
	 * @return 商品详细信息
	 */
	public String getBody() {
		return body;
	}

	/**
	 * 设置商品详细信息
	 * 
	 * @param body
	 *            商品详细信息
	 */
	public void setBody(String body) {
		this.body = body;
	}

	/**
	 * 获取商品扩展属性值
	 * 
	 * @return 商品扩展属性值
	 */
	public String getPropertyValues() {
		return propertyValues;
	}

	/**
	 * 设置商品扩展属性值
	 * 
	 * @param propertyValues
	 *            商品扩展属性值
	 */
	public void setPropertyValues(String propertyValues) {
		this.propertyValues = propertyValues;
	}

	/**
	 * 获取商品规格属性值
	 * 
	 * @return 商品规格属性值
	 */
	public String getSpecificationPropertiesNames() {
		return specificationPropertiesNames;
	}

	/**
	 * 设置商品规格属性值
	 * 
	 * @param specificationPropertiesNames
	 *            商品规格属性值
	 */
	public void setSpecificationPropertiesNames(
			String specificationPropertiesNames) {
		this.specificationPropertiesNames = specificationPropertiesNames;
	}

	/**
	 * 获取商品库存
	 * 
	 * @return 商品库存
	 */
	public Integer getStock() {
		return stock;
	}

	/**
	 * 设置商品库存
	 * 
	 * @param stock
	 *            商品库存
	 */
	public void setStock(Integer stock) {
		this.stock = stock;
	}

	/**
	 * 获取是否为赠品
	 * 
	 * @return 是否为赠品
	 */
	@NotNull()
	public Boolean getIsGift() {
		return isGift;
	}

	/**
	 * 设置是否为赠品
	 * 
	 * @param isGift
	 *            是否为赠品
	 */
	public void setIsGift(Boolean isGift) {
		this.isGift = isGift;
	}

	/**
	 * 获取是否列出
	 * 
	 * @return 是否列出
	 */
	@NotNull()
	public Boolean getIsList() {
		return isList;
	}

	/**
	 * 设置是否列出
	 * 
	 * @param isList
	 *            是否列出
	 */
	public void setIsList(Boolean isList) {
		this.isList = isList;
	}

	/**
	 * 获取是否上架
	 * 
	 * @return 是否上架
	 */
	@NotNull()
	public Boolean getIsMarketable() {
		return isMarketable;
	}

	/**
	 * 设置是否上架
	 * 
	 * @param isMarketable
	 *            是否上架
	 */
	public void setIsMarketable(Boolean isMarketable) {
		this.isMarketable = isMarketable;
	}

	/**
	 * 获取是否置顶
	 * 
	 * @return 是否置顶
	 */
	@NotNull()
	public Boolean getIsTop() {
		return isTop;
	}

	/**
	 * 设置是否置顶
	 * 
	 * @param isTop
	 *            是否置顶
	 */
	public void setIsTop(Boolean isTop) {
		this.isTop = isTop;
	}

	/**
	 * 获取商品关键字
	 * 
	 * @return 商品关键字
	 */
	@Length(min = 0, max = 255)
	public String getKeyword() {
		return keyword;
	}

	/**
	 * 设置商品关键字
	 * 
	 * @param keyword
	 *            商品关键字
	 */
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	/**
	 * 获取商品发布状态
	 * 
	 * @return 商品发布状态
	 */
	@Length(min = 0, max = 25)
	public String getProductReleaseStatus() {
		return productReleaseStatus;
	}

	/**
	 * 设置商品发布状态
	 * 
	 * @param productReleaseStatus
	 *            商品发布状态
	 */
	public void setProductReleaseStatus(String productReleaseStatus) {
		this.productReleaseStatus = productReleaseStatus;
	}

	/**
	 * 获取商品定时发布开始时间
	 * 
	 * @return 商品定时发布开始时间
	 */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getProductReleaseBeginDate() {
		return productReleaseBeginDate;
	}

	/**
	 * 设置商品定时发布开始时间
	 * 
	 * @param productReleaseBeginDate
	 *            商品定时发布开始时间
	 */
	public void setProductReleaseBeginDate(Date productReleaseBeginDate) {
		this.productReleaseBeginDate = productReleaseBeginDate;
	}

	/**
	 * 获取商品定时发布结束时间
	 * 
	 * @return 商品定时发布结束时间
	 */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getProductReleaseEndDate() {
		return productReleaseEndDate;
	}

	/**
	 * 设置商品定时发布结束时间
	 * 
	 * @param productReleaseEndDate
	 *            商品定时发布结束时间
	 */
	public void setProductReleaseEndDate(Date productReleaseEndDate) {
		this.productReleaseEndDate = productReleaseEndDate;
	}

	/**
	 * 获取商品状态
	 * 
	 * @return 商品状态
	 */
	@Length(min = 0, max = 25)
	public String getProductStatus() {
		return productStatus;
	}

	/**
	 * 设置商品状态
	 * 
	 * @param productStatus
	 *            商品状态
	 */
	public void setProductStatus(String productStatus) {
		this.productStatus = productStatus;
	}

	/**
	 * 获取商品状态备注
	 * 
	 * @return 商品状态备注
	 */
	@Length(min = 0, max = 25)
	public String getProductStatusRemark() {
		return productStatusRemark;
	}

	/**
	 * 设置商品状态备注
	 * 
	 * @param productStatusRemark
	 *            商品状态备注
	 */
	public void setProductStatusRemark(String productStatusRemark) {
		this.productStatusRemark = productStatusRemark;
	}

	/**
	 * 获取是否卖家承担运费
	 * 
	 * @return 是否卖家承担运费
	 */
	@NotNull()
	public Boolean getIsSellerBearFreight() {
		return isSellerBearFreight;
	}

	/**
	 * 设置是否卖家承担运费
	 * 
	 * @param isSellerBearFreight
	 *            是否卖家承担运费
	 */
	public void setIsSellerBearFreight(Boolean isSellerBearFreight) {
		this.isSellerBearFreight = isSellerBearFreight;
	}

	/**
	 * 获取销量
	 * 
	 * @return 销量
	 */
	@Length(min = 1, max = 64)
	public String getSales() {
		return sales;
	}

	/**
	 * 设置销量
	 * 
	 * @param sales
	 *            销量
	 */
	public void setSales(String sales) {
		this.sales = sales;
	}

	/**
	 * 获取评分
	 * 
	 * @return 评分
	 */
	public String getScore() {
		return score;
	}

	/**
	 * 设置评分
	 * 
	 * @param score
	 *            评分
	 */
	public void setScore(String score) {
		this.score = score;
	}

	/**
	 * 获取总评分
	 * 
	 * @return 总评分
	 */
	public String getTotalScore() {
		return totalScore;
	}

	/**
	 * 设置总评分
	 * 
	 * @param totalScore
	 *            总评分
	 */
	public void setTotalScore(String totalScore) {
		this.totalScore = totalScore;
	}

	/**
	 * 获取评分数
	 * 
	 * @return 评分数
	 */
	@Length(min = 0, max = 64)
	public String getScoreCount() {
		return scoreCount;
	}

	/**
	 * 设置评分数
	 * 
	 * @param scoreCount
	 *            评分数
	 */
	public void setScoreCount(String scoreCount) {
		this.scoreCount = scoreCount;
	}

	/**
	 * 获取商品所在地
	 * 
	 * @return 商品所在地
	 */
	@NotNull()
	public Region getArea() {
		return area;
	}

	/**
	 * 设置商品所在地
	 * 
	 * @param area
	 *            商品所在地
	 */
	public void setArea(Region area) {
		this.area = area;
	}

	/**
	 * 返回是否推荐
	 * 
	 * @return the isRecommend
	 */
	public Boolean getIsRecommend() {
		return isRecommend;
	}

	/**
	 * 设置是否推荐
	 * 
	 * @param isRecommend
	 *            the isRecommend to set
	 */
	public void setIsRecommend(Boolean isRecommend) {
		this.isRecommend = isRecommend;
	}

	/**
	 * 获取商品品牌
	 * 
	 * @return 商品品牌
	 */
	public ProductBrand getProductBrand() {
		return productBrand;
	}

	/**
	 * 设置商品品牌
	 * 
	 * @param productBrand
	 *            商品品牌
	 */
	public void setProductBrand(ProductBrand productBrand) {
		this.productBrand = productBrand;
	}

	/**
	 * 获取商品品牌名称
	 * 
	 * @return 商品品牌名称
	 */
	@Length(min = 0, max = 255)
	public String getProductBrandName() {
		return productBrandName;
	}

	/**
	 * 设置商品品牌名称
	 * 
	 * @param productBrandName
	 *            商品品牌名称
	 */
	public void setProductBrandName(String productBrandName) {
		this.productBrandName = productBrandName;
	}

	/**
	 * 返回 商品关联属性列表
	 * 
	 * @return the productPropertyList
	 */
	public List<ProductProperty> getProductPropertyList() {
		return productPropertyList;
	}

	/**
	 * 设置 商品关联属性列表
	 * 
	 * @param productPropertyList
	 *            the productPropertyList to set
	 */
	public void setProductPropertyList(
			List<ProductProperty> productPropertyList) {
		this.productPropertyList = productPropertyList;

		// 将List转换为map
		propValues = Maps.uniqueIndex(productPropertyList,
				new Function<ProductProperty, String>() {

					@Override
					public String apply(ProductProperty input) {
						return input.getProperty().getId();
					}
				});

	}

	/**
	 * 判断是否选中
	 * <p>
	 * showType=select,radiobox,checkbox是使用
	 * </p>
	 */
	public Boolean isPropertyValueChecked(String propId, String propValueId) {

		if (propValues != null) {
			ProductProperty prodProp = propValues.get(propId);
			if (prodProp != null) {
				return StringUtils.contains(prodProp.getPropertyValueIds(),
						propValueId);
			}

		}
		return false;
	}

	/**
	 * 获取页面的值
	 * <p>
	 * showType=input,dataselect,textareas时使用
	 * </p>
	 * 
	 */
	public String getPropertyValue(String propertyId) {
		if (propValues != null) {
			ProductProperty prodProp = propValues.get(propertyId);
			if (prodProp != null) {
				return prodProp.getPropertyValueNames();
			}
		}
		return "";
	}

	/**
	 * 返回 商品关联属性 ，用于数据库插入
	 * 
	 * @return the productProperties
	 */
	@JsonIgnore
	public List<ProductProperty> getProductProperties() {
		return productProperties;
	}

	/**
	 * 设置 商品关联属性 ，用于数据库插入
	 * 
	 * @param productProperties
	 *            the productProperties to set
	 */
	public void setProductProperties(List<ProductProperty> productProperties) {
		this.productProperties = productProperties;
	}

	/**
	 * 获取是否支持分期
	 * 
	 * @return the isInstalment
	 */
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
	 * @return the productInstalments
	 */
	public List<ProductInstalment> getProductInstalments() {
		return productInstalments;
	}

	/**
	 * @param productInstalments
	 *            the productInstalments to set
	 */
	public void setProductInstalments(
			List<ProductInstalment> productInstalments) {
		this.productInstalments = productInstalments;
	}

	/**
	 * @return the amount
	 */
	public BigDecimal getAmount() {
		return amount;
	}

	/**
	 * @param amount
	 *            the amount to set
	 */
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	/**
	 * @return the count
	 */
	public Integer getCount() {
		return count;
	}

	/**
	 * @param count
	 *            the count to set
	 */
	public void setCount(Integer count) {
		this.count = count;
	}

	/**
	 * @return the beginDate
	 */
	@JsonFormat(pattern = "yyyy-MM-dd")
	public Date getBeginDate() {
		return beginDate;
	}

	/**
	 * @param beginDate
	 *            the beginDate to set
	 */
	public void setBeginDate(Date beginDate) {
		this.beginDate = beginDate;
	}

	/**
	 * @return the endDate
	 */
	@JsonFormat(pattern = "yyyy-MM-dd")
	public Date getEndDate() {
		return endDate;
	}

	/**
	 * @param endDate
	 *            the endDate to set
	 */
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	/**
	 * 返回 商品附加信息
	 * 
	 * @return the productExtra
	 */
	public ProductExtra getProductExtra() {
		return productExtra;
	}

	/**
	 * 设置 商品附加信息
	 * 
	 * @param productExtra
	 *            the productExtra to set
	 */
	public void setProductExtra(ProductExtra productExtra) {
		this.productExtra = productExtra;
	}

}