/**
 * 
 */
package com.weimhc.modules.article.entity;

import com.weimhc.framework.persistence.BaseNameEntity;

/**
 * 文章类型Entity
 * 
 * 普通文章 外部链接等
 * 
 * @author lc
 * @version 2016-11-22
 */
public class ArticleType extends BaseNameEntity<ArticleType> {

	private static final long serialVersionUID = 1L;

	public ArticleType() {
		super();
	}

	public ArticleType(String id) {
		super(id);
	}

}