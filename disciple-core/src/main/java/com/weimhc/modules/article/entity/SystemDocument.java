
/**
 * 
 */
package com.weimhc.modules.article.entity;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.javamg.common.persistence.DataEntity;

/**
 * 系统文章，主要用于注册协议或其他行为提示Entity
 * 
 * @author zsm
 * @version 2016-02-18
 */
public class SystemDocument extends DataEntity<SystemDocument> {

	private static final long serialVersionUID = 1L;
	private String title; // 文章标题
	private String code; // 系统调用代码
	private String content; // 文章内容

	public SystemDocument() {
		super();
	}

	public SystemDocument(String id) {
		super(id);
	}

	/**
	 * 获取文章标题
	 * 
	 * @return 文章标题
	 */
	@Length(min = 0, max = 255)
	public String getTitle() {
		return title;
	}

	/**
	 * 设置文章标题
	 * 
	 * @param title
	 *            文章标题
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * 获取系统调用代码
	 * 
	 * @return 系统调用代码
	 */
	@Length(min = 0, max = 255)
	public String getCode() {
		return code;
	}

	/**
	 * 设置系统调用代码
	 * 
	 * @param code
	 *            系统调用代码
	 */
	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * 获取文章内容
	 * 
	 * @return 文章内容
	 */
	public String getContent() {
		return content;
	}

	/**
	 * 设置文章内容
	 * 
	 * @param content
	 *            文章内容
	 */
	public void setContent(String content) {
		this.content = content;
	}

}