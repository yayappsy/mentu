/**
 * 
 */
package com.weimhc.modules.navigation.entity;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.thinkgem.javamg.common.persistence.TreeEntity;

/**
 * 导航栏Entity
 * 
 * @author zsm
 * @version 2017-03-27
 */
public class Navigation extends TreeEntity<Navigation> {

	private static final long serialVersionUID = 1L;

	/**
	 * 栏目图片
	 * 
	 */
	private String image; // 栏目图片
	/**
	 * 目标窗口
	 * 
	 */
	private String target; // 目标窗口
	/**
	 * 描述
	 * 
	 */
	private String description; // 描述
	/**
	 * 关键字
	 * 
	 */
	private String keywords; // 关键字
	/**
	 * 展现方式
	 * 
	 */
	private LinkType linkType; // 展现方式
	/**
	 * 链接
	 * 
	 */
	private String url; // 链接
	/**
	 * 模块id
	 * 
	 */
	private String moduleId; // 模块id
	/**
	 * 模块名称
	 * 
	 */
	private String moduleName; // 模块名称
	/**
	 * 自定义内容视图
	 * 
	 */
	private String customContent; // 自定义内容视图
	/**
	 * 是否显示
	 * 
	 */
	private Boolean isShow = Boolean.TRUE; // 是否显示

	public Navigation() {
		super();
	}

	public Navigation(String id) {
		super(id);
	}

	/**
	 * 获取编号
	 * 
	 * @return 编号
	 */
	/**
	 * 获取父级编号
	 * 
	 * @return 父级编号
	 */
	@Override
	@JsonBackReference
	@NotNull()
	public Navigation getParent() {
		return parent;
	}

	/**
	 * 设置父级编号
	 * 
	 * @param parent
	 *            父级编号
	 */
	@Override
	public void setParent(Navigation parent) {
		this.parent = parent;
	}

	/**
	 * 获取所有父级编号
	 * 
	 * @return 所有父级编号
	 */
	@Override
	@Length(min = 1, max = 2000)
	public String getParentIds() {
		return parentIds;
	}

	/**
	 * 设置所有父级编号
	 * 
	 * @param parentIds
	 *            所有父级编号
	 */
	@Override
	public void setParentIds(String parentIds) {
		this.parentIds = parentIds;
	}

	/**
	 * 获取栏目名称
	 * 
	 * @return 栏目名称
	 */
	@Override
	@Length(min = 1, max = 100)
	public String getName() {
		return name;
	}

	/**
	 * 设置栏目名称
	 * 
	 * @param name
	 *            栏目名称
	 */
	@Override
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 获取栏目图片
	 * 
	 * @return 栏目图片
	 */
	@Length(min = 0, max = 255)
	public String getImage() {
		return image;
	}

	/**
	 * 设置栏目图片
	 * 
	 * @param image
	 *            栏目图片
	 */
	public void setImage(String image) {
		this.image = image;
	}

	/**
	 * 获取目标窗口
	 * 
	 * @return 目标窗口
	 */
	@Length(min = 0, max = 20)
	public String getTarget() {
		return target;
	}

	/**
	 * 设置目标窗口
	 * 
	 * @param target
	 *            目标窗口
	 */
	public void setTarget(String target) {
		this.target = target;
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
	 * 获取关键字
	 * 
	 * @return 关键字
	 */
	@Length(min = 0, max = 255)
	public String getKeywords() {
		return keywords;
	}

	/**
	 * 设置关键字
	 * 
	 * @param keywords
	 *            关键字
	 */
	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}

	/**
	 * 获取展现方式
	 * 
	 * @return 展现方式
	 */
	@NotNull
	public LinkType getLinkType() {
		return linkType;
	}

	/**
	 * 设置展现方式
	 * 
	 * @param linkType
	 *            展现方式
	 */
	public void setLinkType(LinkType linkType) {
		this.linkType = linkType;
	}

	/**
	 * 获取链接
	 * 
	 * @return 链接
	 */
	@Length(min = 0, max = 255)
	public String getUrl() {
		return url;
	}

	/**
	 * 设置链接
	 * 
	 * @param url
	 *            链接
	 */
	public void setUrl(String url) {
		this.url = url;
	}

	/**
	 * 获取模块id
	 * 
	 * @return 模块id
	 */
	@Length(min = 0, max = 20)
	public String getModuleId() {
		return moduleId;
	}

	/**
	 * 设置模块id
	 * 
	 * @param moduleId
	 *            模块id
	 */
	public void setModuleId(String moduleId) {
		this.moduleId = moduleId;
	}

	/**
	 * 获取模块名称
	 * 
	 * @return 模块名称
	 */
	@Length(min = 0, max = 255)
	public String getModuleName() {
		return moduleName;
	}

	/**
	 * 设置模块名称
	 * 
	 * @param moduleName
	 *            模块名称
	 */
	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}

	/**
	 * 获取自定义内容视图
	 * 
	 * @return 自定义内容视图
	 */
	public String getCustomContent() {
		return customContent;
	}

	/**
	 * 设置自定义内容视图
	 * 
	 * @param customContent
	 *            自定义内容视图
	 */
	public void setCustomContent(String customContent) {
		this.customContent = customContent;
	}

	/**
	 * 获取是否显示
	 * 
	 * @return 是否显示
	 */
	@NotNull()
	public Boolean getIsShow() {
		return isShow;
	}

	/**
	 * 设置是否显示
	 * 
	 * @param isShow
	 *            是否显示
	 */
	public void setIsShow(Boolean isShow) {
		this.isShow = isShow;
	}

	/**
	 * 获取排序（升序）
	 * 
	 * @return 排序（升序）
	 */
	@Override
	public Integer getSort() {
		return sort;
	}

	/**
	 * 设置排序（升序）
	 * 
	 * @param sort
	 *            排序（升序）
	 */
	@Override
	public void setSort(Integer sort) {
		this.sort = sort;
	}

	/**
	 * 获取创建时间
	 * 
	 * @return 创建时间
	 */
	/**
	 * 获取更新时间
	 * 
	 * @return 更新时间
	 */
	/**
	 * 获取备注信息
	 * 
	 * @return 备注信息
	 */
	/**
	 * 获取删除标记
	 * 
	 * @return 删除标记
	 */
	@Override
	public String getParentId() {
		return parent != null && parent.getId() != null ? parent.getId() : "0";
	}
}