/**
 * 
 */
package com.weimhc.modules.article.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.javamg.common.persistence.Page;
import com.weimhc.framework.service.impl.CrudServiceImpl;
import com.weimhc.modules.article.dao.ArticleDao;
import com.weimhc.modules.article.entity.Article;

/**
 * 文章Service
 * 
 * @author lc
 * @version 2016-11-22
 */
@Service
@Transactional(readOnly = true)
public class ArticleService extends CrudServiceImpl<ArticleDao, Article> {

	@Override
	public Article get(String id) {
		return super.get(id);
	}

	@Override
	public long count(Article article) {
		return super.count(article);
	}

	@Override
	public boolean exists(String id) {
		return super.exists(id);
	}

	@Override
	public List<Article> findList(Article article) {
		return super.findList(article);
	}

	@Override
	public Page<Article> findPage(Page<Article> page, Article article) {
		return super.findPage(page, article);
	}

	@Override
	@Transactional(readOnly = false)
	public void save(Article article) {
		super.save(article);
	}

	@Override
	@Transactional(readOnly = false)
	public void deleteEntity(Article article) {
		super.deleteEntity(article);
	}

	@Override
	@Transactional(readOnly = false)
	public void deleteEntity(String... ids) {
		if (ids != null) {
			Article article = null;
			for (String id : ids) {
				article = new Article(id);
				deleteEntity(article);
			}
		}
	}

	/***
	 * 搜索文章列表，供搜索功能使用
	 */
	public List<Article> searchArticles(Article article) {
		return dao.searchArticles(article);
	}

	/***
	 * 搜索文章列表，供搜索功能使用,分页显示
	 */
	public Page<Article> searchArticles(Page<Article> page, Article article) {
		article.setPage(page);
		page.setList(dao.searchArticles(article));
		return page;
	}

	public Article getRelatedArticle(Article article) {
		return dao.getRelatedArticle(article);
	}

	/**
	 * 根据栏目，搜索下级栏目的文章
	 * 
	 * @param article
	 * @return
	 */
	public List<Article> findCategoryChildrendArticle(Article article) {
		return dao.findCategoryChildrendArticle(article);
	}

	/**
	 * 根据栏目，搜索下级栏目的文章
	 * 
	 * @param page
	 * @param article
	 * @return
	 */
	public Page<Article> findCategoryChildrendArticle(Page<Article> page, Article article) {
		article.setPage(page);
		page.setList(dao.findCategoryChildrendArticle(article));
		return page;
	}

	/**
	 * 获取随机数
	 * 
	 * @return
	 */
	public Integer getRandom() {
		return dao.getRandom();
	}

	/**
	 * 获取随机健康专题
	 * 
	 * @param offset
	 * @return
	 */
	public List<Article> getRandomArticle(Integer offset) {
		return dao.getRandomArticle(offset);
	}
}