/**
 * 
 */
package com.weimhc.modules.article.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.javamg.common.persistence.Page;
import com.weimhc.framework.service.impl.CrudServiceImpl;
import com.weimhc.modules.article.dao.ArticleTypeDao;
import com.weimhc.modules.article.entity.ArticleType;

/**
 * 文章类型Service
 * 
 * @author lc
 * @version 2016-11-22
 */
@Service
@Transactional(readOnly = true)
public class ArticleTypeService
		extends CrudServiceImpl<ArticleTypeDao, ArticleType> {

	@Override
	public ArticleType get(String id) {
		return super.get(id);
	}

	public long count(ArticleType articleType) {
		return super.count(articleType);
	}

	@Override
	public boolean exists(String id) {
		return super.exists(id);
	}

	public List<ArticleType> findList(ArticleType articleType) {
		return super.findList(articleType);
	}

	public Page<ArticleType> findPage(Page<ArticleType> page,
			ArticleType articleType) {
		return super.findPage(page, articleType);
	}

	@Transactional(readOnly = false)
	public void save(ArticleType articleType) {
		super.save(articleType);
	}

	@Transactional(readOnly = false)
	public void deleteEntity(ArticleType articleType) {
		super.deleteEntity(articleType);
	}

	@Override
	@Transactional(readOnly = false)
	public void deleteEntity(String... ids) {
		if (ids != null) {
			ArticleType articleType = null;
			for (String id : ids) {
				articleType = new ArticleType(id);
				deleteEntity(articleType);
			}
		}
	}

}