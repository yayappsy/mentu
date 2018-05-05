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
import com.weimhc.modules.base.dao.EducationDao;
import com.weimhc.modules.base.entity.Education;

/**
 * Utils - 学历
 * 
 * 
 */
public abstract class EducationUtils {

	private static EducationDao educationDao = SpringContextHolder
			.getBean(EducationDao.class);

	/**
	 * 学历相关缓存 暂时未使用
	 */
	public final static String EDUCATION_CACHE = "educationCache";

	/** 学历相关缓存列表 */
	public final static String CACHE_EDUCATION_LIST = "education_list";
	/** 学历相关缓存列表 */
	public final static String CACHE_EDUCATION_BY_ID = "education_id";

	/**
	 * 获取学历相关缓存
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
	 * 设置学历相关缓存
	 * 
	 * @param key
	 * @return
	 */
	public static void putCache(String key, Object value) {
		CacheUtils.put(key, value);
	}

	/**
	 * 获取所有的学历
	 * 
	 * @return
	 */
	public static List<Education> findAll() {
		@SuppressWarnings("unchecked")
		List<Education> list = (List<Education>) getCache(CACHE_EDUCATION_LIST);
		if (list == null) {
			list = educationDao.findAllList(new Education());
			putCache(CACHE_EDUCATION_LIST, list);
		}
		return list;

	}

	/**
	 * id查询学历
	 * 
	 * @param id
	 * @return
	 */
	public static Education findById(String id) {
		List<Education> educationList = findAll();
		for (Education education : educationList) {
			if (StringUtils.equals(id, education.getId())) {
				return education;
			}
		}
		return new Education();
	}

}