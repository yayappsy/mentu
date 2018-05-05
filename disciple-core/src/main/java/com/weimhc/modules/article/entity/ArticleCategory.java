/**
 * 
 */
package com.weimhc.modules.article.entity;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.thinkgem.javamg.common.persistence.TreeEntity;

/**
 * 文章分类Entity
 * 
 * @author lc
 * @version 2016-11-22
 */
public class ArticleCategory extends TreeEntity<ArticleCategory> {

	private static final long serialVersionUID = 1L;

	private String module; // 栏目模块

	private String image; // 栏目图片
	private String url; // 链接
	private String target; // 目标
	private String description; // 描述
	private String keywords; // 关键字
	private String inMenu; // 是否在导航中显示
	private String inList; // 是否在分类页中显示列表
	private String showModes; // 展现方式
	private String allowComment; // 是否允许评论
	private String isAudit; // 是否需要审核
	private String customListView; // 自定义列表视图
	private String customContentView; // 自定义内容视图
	private String viewConfig; // 视图配置

	public ArticleCategory() {
		super();
	}

	public ArticleCategory(String id) {
		super(id);
	}

	/**
	 * 获取父级编号
	 * 
	 * @return 父级编号
	 */
	@Override
	@JsonBackReference
	@NotNull()
	public ArticleCategory getParent() {
		return parent;
	}

	/**
	 * 设置父级编号
	 * 
	 * @param parent
	 *            父级编号
	 */
	@Override
	public void setParent(ArticleCategory parent) {
		this.parent = parent;
	}
	
	/**
	 * 获取栏目模块
	 * 
	 * @return 栏目模块
	 */
	@Length(min = 0, max = 20)
	public String getModule() {
		return module;
	}

	/**
	 * 设置栏目模块
	 * 
	 * @param module
	 *            栏目模块
	 */
	public void setModule(String module) {
		this.module = module;
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
	 * 获取目标
	 * 
	 * @return 目标
	 */
	@Length(min = 0, max = 20)
	public String getTarget() {
		return target;
	}

	/**
	 * 设置目标
	 * 
	 * @param target
	 *            目标
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
	 * 获取是否在导航中显示
	 * 
	 * @return 是否在导航中显示
	 */
	@Length(min = 0, max = 1)
	public String getInMenu() {
		return inMenu;
	}

	/**
	 * 设置是否在导航中显示
	 * 
	 * @param inMenu
	 *            是否在导航中显示
	 */
	public void setInMenu(String inMenu) {
		this.inMenu = inMenu;
	}

	/**
	 * 获取是否在分类页中显示列表
	 * 
	 * @return 是否在分类页中显示列表
	 */
	@Length(min = 0, max = 1)
	public String getInList() {
		return inList;
	}

	/**
	 * 设置是否在分类页中显示列表
	 * 
	 * @param inList
	 *            是否在分类页中显示列表
	 */
	public void setInList(String inList) {
		this.inList = inList;
	}

	/**
	 * 获取展现方式
	 * 
	 * @return 展现方式
	 */
	@Length(min = 0, max = 1)
	public String getShowModes() {
		return showModes;
	}

	/**
	 * 设置展现方式
	 * 
	 * @param showModes
	 *            展现方式
	 */
	public void setShowModes(String showModes) {
		this.showModes = showModes;
	}

	/**
	 * 获取是否允许评论
	 * 
	 * @return 是否允许评论
	 */
	@Length(min = 0, max = 1)
	public String getAllowComment() {
		return allowComment;
	}

	/**
	 * 设置是否允许评论
	 * 
	 * @param allowComment
	 *            是否允许评论
	 */
	public void setAllowComment(String allowComment) {
		this.allowComment = allowComment;
	}

	/**
	 * 获取是否需要审核
	 * 
	 * @return 是否需要审核
	 */
	@Length(min = 0, max = 1)
	public String getIsAudit() {
		return isAudit;
	}

	/**
	 * 设置是否需要审核
	 * 
	 * @param isAudit
	 *            是否需要审核
	 */
	public void setIsAudit(String isAudit) {
		this.isAudit = isAudit;
	}

	/**
	 * 获取自定义列表视图
	 * 
	 * @return 自定义列表视图
	 */
	@Length(min = 0, max = 255)
	public String getCustomListView() {
		return customListView;
	}

	/**
	 * 设置自定义列表视图
	 * 
	 * @param customListView
	 *            自定义列表视图
	 */
	public void setCustomListView(String customListView) {
		this.customListView = customListView;
	}

	/**
	 * 获取自定义内容视图
	 * 
	 * @return 自定义内容视图
	 */
	@Length(min = 0, max = 255)
	public String getCustomContentView() {
		return customContentView;
	}

	/**
	 * 设置自定义内容视图
	 * 
	 * @param customContentView
	 *            自定义内容视图
	 */
	public void setCustomContentView(String customContentView) {
		this.customContentView = customContentView;
	}

	/**
	 * 获取视图配置
	 * 
	 * @return 视图配置
	 */
	public String getViewConfig() {
		return viewConfig;
	}

	/**
	 * 设置视图配置
	 * 
	 * @param viewConfig
	 *            视图配置
	 */
	public void setViewConfig(String viewConfig) {
		this.viewConfig = viewConfig;
	}

	@Override
	public String getParentId() {
		return parent != null && parent.getId() != null ? parent.getId() : "0";
	}
}
