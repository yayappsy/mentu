/*
 * 
 * 
 * 
 */
package com.weimhc.modules.article.utils;

import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.thinkgem.javamg.common.utils.CacheUtils;
import com.thinkgem.javamg.common.utils.SpringContextHolder;
import com.weimhc.modules.article.dao.ArticleTypeDao;
import com.weimhc.modules.article.entity.ArticleType;

/**
 * Utils - 文章类型
 * 
 * 
 */
public abstract class ArticleTypeUtils {

	private static ArticleTypeDao articleTypeDao = SpringContextHolder
			.getBean(ArticleTypeDao.class);

	/**
	 * 普通文章
	 */
	public final static String NORMAL = "1";
	/**
	 * 外部链接
	 */
	public final static String LINK = "2";

	/**
	 * 文章类型相关缓存 暂时未使用
	 */
	public final static String ARTICLE_TYPE_CACHE = "articleTypeCache";

	/** 文章类型相关缓存列表 */
	public final static String CACHE_ARTICLE_TYPE_LIST = "articleType_list";
	/** 文章类型相关缓存列表 */
	public final static String CACHE_ARTICLE_TYPE_BY_ID = "articleType_id";

	/**
	 * 获取文章类型相关缓存
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
		Object obj = CacheUtils.get(key);
		return obj == null ? defaultValue : obj;
	}

	/**
	 * 设置文章类型相关缓存
	 * 
	 * @param key
	 * @return
	 */
	public static void putCache(String key, Object value) {
		CacheUtils.put(key, value);
	}

	/**
	 * 获取所有的文章类型
	 * 
	 * @return
	 */
	public static List<ArticleType> findAll() {
		@SuppressWarnings("unchecked")
		List<ArticleType> list = (List<ArticleType>) getCache(
				CACHE_ARTICLE_TYPE_LIST);
		if (list == null) {
			list = articleTypeDao.findAllList(new ArticleType());
			putCache(CACHE_ARTICLE_TYPE_LIST, list);
		}
		return list;

	}

	/**
	 * id查询文章类型
	 * 
	 * @param id
	 * @return
	 */
	public static ArticleType findById(String id) {
		List<ArticleType> articleTypeList = findAll();
		for (ArticleType articleType : articleTypeList) {
			if (StringUtils.equals(id, articleType.getId())) {
				return articleType;
			}
		}
		return new ArticleType();
	}

}