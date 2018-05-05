/**
 * 
 */
package com.weimhc.modules.product.entity;

import java.math.BigDecimal;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.javamg.common.persistence.TreeEntity;
import com.weimhc.modules.property.entity.PropertyTemplate;

/**
 * 商品分类Entity
 * 
 * @author lc
 * @version 2017-01-03
 */
public class ProductCategory extends TreeEntity<ProductCategory> {

	private static final long serialVersionUID = 1L;
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
	 * 层级
	 * 
	 */
	private Integer grade; // 层级
	/**
	 * 分类名称
	 * 
	 */
	private String name; // 分类名称
	/**
	 * 分类图片路径
	 * 
	 */
	private String imageUrl; // 分类图片路径
	/**
	 * 树路径
	 * 
	 */
	private String parentIds; // 树路径
	/**
	 * 是否前台显示(0为否)
	 * 
	 */
	private Boolean isShow; // 是否前台显示(0为否)
	/**
	 * 是否关联子类
	 * 
	 */
	private Boolean isRelateSub; // 是否关联子类

	/**
	 * 佣金比例
	 * 
	 */
	private BigDecimal commissionRatio; // 佣金比例

	public ProductCategory() {
		super();
	}

	public ProductCategory(String id) {
		super(id);
	}

	/**
	 * 获取属性模板
	 * 
	 * @return属性模板
	 */
	@NotNull
	public PropertyTemplate getPropertyTemplate() {
		return propertyTemplate;
	}

	/**
	 * 设置 属性模板
	 * 
	 * @param propertyTemplate
	 *            属性模板
	 */
	public void setPropertyTemplate(PropertyTemplate propertyTemplate) {
		this.propertyTemplate = propertyTemplate;
	}

	/**
	 * 获取层级
	 * 
	 * @return 层级
	 */
	@NotNull()
	public Integer getGrade() {
		return grade;
	}

	/**
	 * 设置层级
	 * 
	 * @param grade
	 *            层级
	 */
	public void setGrade(Integer grade) {
		this.grade = grade;
	}

	/**
	 * 获取分类名称
	 * 
	 * @return 分类名称
	 */
	@Override
	@Length(min = 1, max = 255)
	public String getName() {
		return name;
	}

	/**
	 * 设置分类名称
	 * 
	 * @param name
	 *            分类名称
	 */
	@Override
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 获取分类图片路径
	 * 
	 * @return 分类图片路径
	 */
	@Length(min = 0, max = 255)
	public String getImageUrl() {
		return imageUrl;
	}

	/**
	 * 设置分类图片路径
	 * 
	 * @param imageUrl
	 *            分类图片路径
	 */
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	/**
	 * 获取树路径
	 * 
	 * @return 树路径
	 */
	@Override
	@Length(min = 1, max = 2000)
	public String getParentIds() {
		return parentIds;
	}

	/**
	 * 设置树路径
	 * 
	 * @param parentIds
	 *            树路径
	 */
	@Override
	public void setParentIds(String parentIds) {
		this.parentIds = parentIds;
	}

	/**
	 * 获取是否前台显示(0为否)
	 * 
	 * @return 是否前台显示(0为否)
	 */
	@NotNull()
	public Boolean getIsShow() {
		return isShow;
	}

	/**
	 * 设置是否前台显示(0为否)
	 * 
	 * @param isShow
	 *            是否前台显示(0为否)
	 */
	public void setIsShow(Boolean isShow) {
		this.isShow = isShow;
	}

	/**
	 * 获取是否关联子类
	 * 
	 * @return 是否关联子类
	 */
	public Boolean getIsRelateSub() {
		return isRelateSub;
	}

	/**
	 * 设置是否关联子类
	 * 
	 * @param isRelateSub
	 *            是否关联子类
	 */
	public void setIsRelateSub(Boolean isRelateSub) {
		this.isRelateSub = isRelateSub;
	}

	/**
	 * 获取排序
	 * 
	 * @return 排序
	 */
	@Override
	public Integer getSort() {
		return sort;
	}

	/**
	 * 设置排序
	 * 
	 * @param sort
	 *            排序
	 */
	@Override
	public void setSort(Integer sort) {
		this.sort = sort;
	}

	/**
	 * 获取佣金比例
	 * 
	 * @return 佣金比例
	 */
	public BigDecimal getCommissionRatio() {
		return commissionRatio;
	}

	/**
	 * 设置佣金比例
	 * 
	 * @param commissionRatio
	 *            佣金比例
	 */
	public void setCommissionRatio(BigDecimal commissionRatio) {
		this.commissionRatio = commissionRatio;
	}

	/**
	 * 返回 性模板名称
	 * 
	 * @return the propertyTemplateName
	 */
	public String getPropertyTemplateName() {
		return propertyTemplateName;
	}

	/**
	 * 设置 性模板名称
	 * 
	 * @param propertyTemplateName
	 *            the propertyTemplateName to set
	 */
	public void setPropertyTemplateName(String propertyTemplateName) {
		this.propertyTemplateName = propertyTemplateName;
	}

	@Override
	public ProductCategory getParent() {
		return parent;
	}

	@Override
	public void setParent(ProductCategory parent) {
		this.parent = parent;
	}

}