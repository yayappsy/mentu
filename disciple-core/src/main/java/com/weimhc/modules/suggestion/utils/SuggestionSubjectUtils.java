/*
 * 
 * 
 * 
 */
package com.weimhc.modules.suggestion.utils;

import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.thinkgem.javamg.common.utils.CacheUtils;
import com.thinkgem.javamg.common.utils.SpringContextHolder;
import com.weimhc.modules.suggestion.dao.SuggestionSubjectDao;
import com.weimhc.modules.suggestion.entity.SuggestionSubject;

/**
 * Utils - 投诉主题
 * 
 * 
 */
public abstract class SuggestionSubjectUtils {

	private static SuggestionSubjectDao suggestionSubjectDao = SpringContextHolder
			.getBean(SuggestionSubjectDao.class);

	/**
	 * 投诉主题相关缓存 暂时未使用
	 */
	public final static String SUGGESTION_SUBJECT_CACHE = "suggestionSubjectCache";

	/** 投诉主题相关缓存列表 */
	public final static String CACHE_SUGGESTION_SUBJECT_LIST = "suggestionSubject_list";
	/** 投诉主题相关缓存列表 */
	public final static String CACHE_SUGGESTION_SUBJECT_BY_ID = "suggestionSubject_id";

	/**
	 * 获取投诉主题相关缓存
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
	 * 设置投诉主题相关缓存
	 * 
	 * @param key
	 * @return
	 */
	public static void putCache(String key, Object value) {
		CacheUtils.put(key, value);
	}

	/**
	 * 获取所有的投诉主题
	 * 
	 * @return
	 */
	public static List<SuggestionSubject> findAll() {
		CacheUtils.remove(CACHE_SUGGESTION_SUBJECT_LIST);
		@SuppressWarnings("unchecked")
		List<SuggestionSubject> list = (List<SuggestionSubject>) getCache(
				CACHE_SUGGESTION_SUBJECT_LIST);
		if (list == null) {
			list = suggestionSubjectDao.findAllList(new SuggestionSubject());
			putCache(CACHE_SUGGESTION_SUBJECT_LIST, list);
		}
		return list;

	}

	/**
	 * id查询解决方案
	 * 
	 * @param id
	 * @return
	 */
	public static SuggestionSubject findById(String id) {
		List<SuggestionSubject> suggestionSubjectList = findAll();
		for (SuggestionSubject suggestionSubject : suggestionSubjectList) {
			if (StringUtils.equals(id, suggestionSubject.getId())) {
				return suggestionSubject;
			}
		}
		return new SuggestionSubject();
	}

}