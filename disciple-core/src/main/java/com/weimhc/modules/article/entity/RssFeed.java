/**
 * 
 */
package com.weimhc.modules.article.entity;

import org.hibernate.validator.constraints.Length;

import com.weimhc.framework.persistence.BaseNameEntity;

/**
 * 信息源接口地址Entity
 * 
 * @author zsm
 * @version 2017-05-08
 */
public class RssFeed extends BaseNameEntity<RssFeed> {

	private static final long serialVersionUID = 1L;
	/**
	 * 信息源地址
	 * 
	 */
	private String url;
	/**
	 * 信息源类型
	 * 
	 */
	private String feedType;

	public RssFeed() {
		super();
	}

	public RssFeed(String id) {
		super(id);
	}

	/**
	 * 获取信息源地址
	 * 
	 * @return 信息源地址
	 */
	@Length(min = 0, max = 255)
	public String getUrl() {
		return url;
	}

	/**
	 * 设置信息源地址
	 * 
	 * @param url
	 *            信息源地址
	 */
	public void setUrl(String url) {
		this.url = url;
	}

	/**
	 * 获取信息源类型
	 * 
	 * @return 信息源类型
	 */
	@Length(min = 0, max = 25)
	public String getFeedType() {
		return feedType;
	}

	/**
	 * 设置信息源类型
	 * 
	 * @param feedType
	 *            信息源类型
	 */
	public void setFeedType(String feedType) {
		this.feedType = feedType;
	}

}