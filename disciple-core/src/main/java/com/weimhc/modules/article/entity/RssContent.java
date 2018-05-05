/**
 * 
 */
package com.weimhc.modules.article.entity;

import java.util.Date;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import com.weimhc.framework.persistence.SortableEntity;

/**
 * 信息源内容Entity
 * 
 * @author zsm
 * @version 2017-05-08
 */
public class RssContent extends SortableEntity<RssContent> {

	private static final long serialVersionUID = 1L;
	/**
	 * RSS名称
	 * 
	 */
	private String rssName;
	/**
	 * RSS源地址
	 * 
	 */
	private String rssUrl;
	/**
	 * 抓取数量
	 * 
	 */
	private Integer number;
	/**
	 * 引用文章分类
	 * 
	 */
	private ArticleCategory articleCategory;
	/**
	 * 引用文章分类名
	 * 
	 */
	private String articleCategoryName;
	/**
	 * 是否启用
	 * 
	 */
	private Boolean isEnabled;

	/**
	 * 关键词
	 * 
	 */
	private String keywords;
	/**
	 * 上一次抓取时间
	 */
	private Date lastFetchDate;

	public RssContent() {
		super();
	}

	public RssContent(String id) {
		super(id);
	}

	/**
	 * 获取RSS名称
	 * 
	 * @return RSS名称
	 */
	@Length(min = 1, max = 100)
	public String getRssName() {
		return rssName;
	}

	/**
	 * 设置RSS名称
	 * 
	 * @param rssName
	 *            RSS名称
	 */
	public void setRssName(String rssName) {
		this.rssName = rssName;
	}

	/**
	 * 获取RSS源地址
	 * 
	 * @return RSS源地址
	 */
	@Length(min = 1, max = 255)
	public String getRssUrl() {
		return rssUrl;
	}

	/**
	 * 设置RSS源地址
	 * 
	 * @param rssUrl
	 *            RSS源地址
	 */
	public void setRssUrl(String rssUrl) {
		this.rssUrl = rssUrl;
	}

	/**
	 * 获取抓取数量
	 * 
	 * @return 抓取数量
	 */
	public Integer getNumber() {
		return number;
	}

	/**
	 * 设置抓取数量
	 * 
	 * @param number
	 *            抓取数量
	 */
	public void setNumber(Integer number) {
		this.number = number;
	}

	/**
	 * 获取引用文章分类
	 * 
	 * @return 引用文章分类
	 */
	@NotNull
	public ArticleCategory getArticleCategory() {
		return articleCategory;
	}

	/**
	 * 设置引用文章分类
	 * 
	 * @param articleCategory
	 *            引用文章分类
	 */
	public void setArticleCategory(ArticleCategory articleCategory) {
		this.articleCategory = articleCategory;
	}

	/**
	 * 获取引用文章分类名
	 * 
	 * @return 引用文章分类名
	 */
	@Length(min = 0, max = 100)
	public String getArticleCategoryName() {
		return articleCategoryName;
	}

	/**
	 * 设置引用文章分类名
	 * 
	 * @param articleCategoryName
	 *            引用文章分类名
	 */
	public void setArticleCategoryName(String articleCategoryName) {
		this.articleCategoryName = articleCategoryName;
	}

	/**
	 * 获取是否启用
	 * 
	 * @return 是否启用
	 */
	@NotNull()
	public Boolean getIsEnabled() {
		return isEnabled;
	}

	/**
	 * 设置是否启用
	 * 
	 * @param isEnabled
	 *            是否启用
	 */
	public void setIsEnabled(Boolean isEnabled) {
		this.isEnabled = isEnabled;
	}

	/**
	 * 返回 关键词
	 * 
	 * @return the keywords
	 */
	public String getKeywords() {
		return keywords;
	}

	/**
	 * 设置 关键词
	 * 
	 * @param keywords
	 *            the keywords to set
	 */
	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}

	/**
	 * 返回 上一次抓取时间
	 * 
	 * @return the lastFetchDate
	 */
	public Date getLastFetchDate() {
		return lastFetchDate;
	}

	/**
	 * 设置 上一次抓取时间
	 * 
	 * @param lastFetchDate
	 *            the lastFetchDate to set
	 */
	public void setLastFetchDate(Date lastFetchDate) {
		this.lastFetchDate = lastFetchDate;
	}

}