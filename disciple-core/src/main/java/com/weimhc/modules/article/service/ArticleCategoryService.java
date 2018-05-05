/**
 * 
 */
package com.weimhc.modules.article.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.javamg.common.utils.StringUtils;
import com.weimhc.framework.service.impl.TreeServiceImpl;
import com.weimhc.modules.article.dao.ArticleCategoryDao;
import com.weimhc.modules.article.entity.ArticleCategory;

/**
 * 文章分类Service
 * 
 * @author lc
 * @version 2016-11-22
 */
@Service
@Transactional(readOnly = true)
public class ArticleCategoryService
		extends TreeServiceImpl<ArticleCategoryDao, ArticleCategory> {

	@Override
	public ArticleCategory get(String id) {
		return super.get(id);
	}

	@Override
	public List<ArticleCategory> findList(ArticleCategory articleCategory) {
		if (StringUtils.isNotBlank(articleCategory.getParentIds())) {
			articleCategory
					.setParentIds("," + articleCategory.getParentIds() + ",");
		}
		return super.findList(articleCategory);
	}

	@Override
	@Transactional(readOnly = false)
	public void save(ArticleCategory articleCategory) {
		super.save(articleCategory);
	}

	@Override
	@Transactional(readOnly = false)
	public void deleteEntity(ArticleCategory articleCategory) {
		super.deleteEntity(articleCategory);
	}

	@Override
	@Transactional(readOnly = false)
	public void deleteEntity(String... ids) {
		if (ids != null) {
			ArticleCategory articleCategory = null;
			for (String id : ids) {
				articleCategory = new ArticleCategory(id);
				super.deleteEntity(articleCategory);
			}
		}
	}

}