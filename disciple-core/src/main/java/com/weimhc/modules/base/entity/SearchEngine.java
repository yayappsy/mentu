/**
 * 
 */
package com.weimhc.modules.base.entity;

import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;

import com.weimhc.framework.persistence.BaseNameEntity;

/**
 * 血型Entity
 * 
 * @author lc
 * @version 2016-06-24
 */
public class SearchEngine extends BaseNameEntity<SearchEngine> {

	private static final long serialVersionUID = 1L;
	/**
	 * 搜索网址
	 * 
	 */
	private String url;
	/**
	 * 搜索参数
	 * 
	 */
	private String searchParameter;
	/**
	 * 搜索网址
	 */
	private Pattern serchUrlPattern;
	/**
	 * 搜索关键词
	 */
	private Pattern searchParameterPattern;

	public SearchEngine() {
		super();
	}

	public SearchEngine(String id) {
		super(id);
	}

	/**
	 * 返回 链接
	 * 
	 * @return the url
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * 设置 链接
	 * 
	 * @param url
	 *            the url to set
	 */
	public void setUrl(String url) {
		this.url = url;
		if (StringUtils.isNotBlank(url)) {
			this.serchUrlPattern = Pattern.compile(url + "(.*)");
		}
	}

	/**
	 * 返回 搜索参数
	 * 
	 * @return the searchParameter
	 */
	public String getSearchParameter() {
		return searchParameter;
	}

	/**
	 * 设置 搜索参数
	 * 
	 * @param searchParameter
	 *            the searchParameter to set
	 */
	public void setSearchParameter(String searchParameter) {
		this.searchParameter = searchParameter;
		if (StringUtils.isNotBlank(searchParameter)) {
			this.searchParameterPattern = Pattern.compile(
					"(?:^|/?|&)" + searchParameter + "=([^&]*)(?:&|$)");
		}
	}

	/**
	 * 返回 获取网站正则
	 * 
	 * @return the serchUrlPattern
	 */
	public Pattern getSerchUrlPattern() {
		return serchUrlPattern;
	}

	/**
	 * 返回 获取搜索参数正则
	 * 
	 * @return the searchParameterPattern
	 */
	public Pattern getSearchParameterPattern() {
		return searchParameterPattern;
	}

}