/*
 * 
 * 
 * 
 */
package com.weimhc.modules.article.utils;

import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.google.common.collect.Lists;
import com.thinkgem.javamg.common.utils.CacheUtils;
import com.thinkgem.javamg.common.utils.SpringContextHolder;
import com.weimhc.modules.article.dao.ArticleCategoryDao;
import com.weimhc.modules.article.dao.ArticleDao;
import com.weimhc.modules.article.entity.Article;
import com.weimhc.modules.article.entity.ArticleCategory;

/**
 * Utils - 文章分类
 * 
 * 
 */
public abstract class ArticleCategoryUtils {

	private static ArticleCategoryDao articleCategoryDao = SpringContextHolder
			.getBean(ArticleCategoryDao.class);

	private static ArticleDao articleDao = SpringContextHolder
			.getBean(ArticleDao.class);

	/**
	 * 文章分类相关缓存 暂时未使用
	 */
	public final static String ARTICLE_CATEGORY_CACHE = "articleCategoryCache";

	/** 文章分类相关缓存列表 */
	public final static String CACHE_ARTICLE_CATEGORY_LIST = "articleCategory_list";
	/** 文章分类相关缓存列表 */
	public final static String CACHE_ARTICLE_CATEGORY_BY_ID = "articleCategory_id";
	/** 文章分类相关缓存列表 */
	public final static String CACHE_ARTICLE_CATEGORY_BY_PARENTID = "articleCategory_parentId";
	/** 文章相关缓存列表 */
	public final static String CACHE_ARTICLE__LIST_BY_CATEGORYID = "article_list_category";

	/**
	 * 获取文章分类相关缓存
	 * 
	 * @param key
	 * @return
	 */
	public static Object getCache(String key) {
		return getCache(key, null);
	}

	/**
	 * 获取相关缓存 从默认缓存中
	 * 
	 * @param key
	 * @return
	 */
	public static Object getCache(String key, Object defaultValue) {
		Object obj = CacheUtils.get(ARTICLE_CATEGORY_CACHE, key);
		return obj == null ? defaultValue : obj;
	}

	/**
	 * 设置文章分类相关缓存
	 * 
	 * @param key
	 * @return
	 */
	public static void putCache(String key, Object value) {
		CacheUtils.put(ARTICLE_CATEGORY_CACHE, key, value);
	}

	/**
	 * 获取所有的文章分类
	 * 
	 * @return
	 */
	public static List<ArticleCategory> findAll() {
		@SuppressWarnings("unchecked")
		List<ArticleCategory> list = (List<ArticleCategory>) getCache(
				CACHE_ARTICLE_CATEGORY_LIST);
		if (list == null) {
			list = articleCategoryDao.findAllList(new ArticleCategory());
			putCache(CACHE_ARTICLE_CATEGORY_LIST, list);
		}
		return list;

	}

	/**
	 * id查询文章分类
	 * 
	 * @param id
	 * @return
	 */
	public static ArticleCategory findById(String id) {
		List<ArticleCategory> articleCategoryList = findAll();
		for (ArticleCategory articleCategory : articleCategoryList) {
			if (StringUtils.equals(id, articleCategory.getId())) {
				return articleCategory;
			}
		}
		return new ArticleCategory();
	}

	/**
	 * 根据父id查询文章分类
	 * 
	 * @param id
	 * @return
	 */
	public static List<ArticleCategory> findByParentId(String parentId) {
		@SuppressWarnings("unchecked")
		List<ArticleCategory> list = (List<ArticleCategory>) getCache(
				parentId + CACHE_ARTICLE_CATEGORY_BY_PARENTID);
		if (list == null) {
			list = Lists.newArrayList();
			List<ArticleCategory> articleCategoryList = articleCategoryDao
					.findAllList(new ArticleCategory());
			for (ArticleCategory articleCategory : articleCategoryList) {
				if (StringUtils.equals(parentId,
						articleCategory.getParentId())) {
					list.add(articleCategory);
				}
			}
			putCache(parentId + CACHE_ARTICLE_CATEGORY_BY_PARENTID, list);
		}
		return list;
	}

	/**
	 * 根据文章分类获取文章
	 * 
	 * @param id
	 * @return
	 */
	public static List<Article> findByCategoryId(String categoryId) {
		@SuppressWarnings("unchecked")
		List<Article> list = (List<Article>) getCache(
				categoryId + CACHE_ARTICLE__LIST_BY_CATEGORYID);
		if (list == null) {
			list = Lists.newArrayList();
			Article searchArticle = new Article();
			searchArticle.getSqlMap().put("searchType", "index");
			searchArticle.setCategory(new ArticleCategory(categoryId));
			list = articleDao.findList(searchArticle);
			putCache(categoryId + CACHE_ARTICLE__LIST_BY_CATEGORYID, list);
		}
		return list;
	}

	/**
	 * 文章咨询，根据文章分类获取文章
	 * 
	 * @param id
	 * @return
	 */
	public static List<Article> findNewsByCategoryId(String categoryId) {
		@SuppressWarnings("unchecked")
		List<Article> list = (List<Article>) getCache(
				categoryId + CACHE_ARTICLE__LIST_BY_CATEGORYID);
		if (list == null) {
			list = Lists.newArrayList();
			Article searchArticle = new Article();
			searchArticle.getSqlMap().put("searchType", "news");
			searchArticle.setCategory(new ArticleCategory(categoryId));
			list = articleDao.findList(searchArticle);
			putCache(categoryId + CACHE_ARTICLE__LIST_BY_CATEGORYID, list);
		}
		return list;
	}

}