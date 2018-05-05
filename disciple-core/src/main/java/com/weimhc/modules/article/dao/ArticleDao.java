/**
 * 
 */
package com.weimhc.modules.article.dao;

import java.util.List;

import com.thinkgem.javamg.common.persistence.annotation.MyBatisDao;
import com.weimhc.framework.persistence.CrudDao;
import com.weimhc.modules.article.entity.Article;

/**
 * 文章DAO接口
 * 
 * @author lc
 * @version 2016-11-22
 */
@MyBatisDao
public interface ArticleDao extends CrudDao<Article> {

	/**
	 * 热门文章
	 * 
	 * @param article
	 * @return
	 */
	public List<Article> findArticle(Article article);

	/**
	 * 最新文章
	 * 
	 * @param article
	 * @return
	 */
	public List<Article> findNewArticle(Article article);

	/***
	 * 搜索文章列表，供搜索功能使用,分页显示
	 */
	public List<Article> searchArticles(Article article);

	public Article getRelatedArticle(Article article);

	/**
	 * 根据栏目，搜索下级栏目的文章
	 */
	public List<Article> findCategoryChildrendArticle(Article article);

	public Integer getRandom();

	public List<Article> getRandomArticle(Integer offset);

}