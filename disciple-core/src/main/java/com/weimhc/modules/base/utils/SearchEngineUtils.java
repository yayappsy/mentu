/*
 * 
 * 
 * 
 */
package com.weimhc.modules.base.utils;

import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.thinkgem.javamg.common.utils.CacheUtils;
import com.thinkgem.javamg.common.utils.SpringContextHolder;
import com.weimhc.modules.base.dao.SearchEngineDao;
import com.weimhc.modules.base.entity.SearchEngine;

/**
 * Utils - 搜索引擎
 * 
 * 
 */
public abstract class SearchEngineUtils {

	private static SearchEngineDao searchEngineDao = SpringContextHolder
			.getBean(SearchEngineDao.class);

	/**
	 * 搜索引擎相关缓存 暂时未使用
	 */
	public final static String SEARCH_ENGINE_CACHE = "searchEngineCache";
	/** 搜索引擎相关缓存列表 */
	public final static String CACHE_SEARCH_ENGINE_LIST = "searchEngine_list";
	/** 搜索引擎相关缓存列表 */
	public final static String CACHE_SEARCH_ENGINE_BY_ID = "searchEngine_id";

	/**
	 * 获取搜索引擎相关缓存
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
	 * 设置搜索引擎相关缓存
	 * 
	 * @param key
	 * @return
	 */
	public static void putCache(String key, Object value) {
		CacheUtils.put(key, value);
	}

	/**
	 * 获取所有的搜索引擎
	 * 
	 * @return
	 */
	public static List<SearchEngine> findAll() {
		@SuppressWarnings("unchecked")
		List<SearchEngine> list = (List<SearchEngine>) getCache(
				CACHE_SEARCH_ENGINE_LIST);
		if (list == null) {
			list = searchEngineDao.findAllList(new SearchEngine());
			putCache(CACHE_SEARCH_ENGINE_LIST, list);
		}
		return list;

	}

	/**
	 * id查询搜索引擎
	 * 
	 * @param id
	 * @return
	 */
	public static SearchEngine findById(String id) {
		List<SearchEngine> searchEngineList = findAll();
		for (SearchEngine searchEngine : searchEngineList) {
			if (StringUtils.equals(id, searchEngine.getId())) {
				return searchEngine;
			}
		}
		return new SearchEngine();
	}

}