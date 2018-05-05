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
import com.weimhc.modules.base.dao.DiseaseDao;
import com.weimhc.modules.base.entity.Disease;

/**
 * Utils - 疾病
 * 
 * 
 */
public abstract class DiseaseUtils {

	private static DiseaseDao diseaseDao = SpringContextHolder
			.getBean(DiseaseDao.class);

	/**
	 * 疾病相关缓存 暂时未使用
	 */
	public final static String DISEASE_CACHE = "DiseaseCache";
	/** 疾病相关缓存列表 */
	public final static String CACHE_DISEASE_LIST = "Disease_list";
	/** 疾病相关缓存列表 */
	public final static String CACHE_DISEASE_BY_ID = "Disease_id";

	/**
	 * 获取疾病相关缓存
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
	 * 设置疾病相关缓存
	 * 
	 * @param key
	 * @return
	 */
	public static void putCache(String key, Object value) {
		CacheUtils.put(key, value);
	}

	/**
	 * 获取所有的疾病
	 * 
	 * @return
	 */
	public static List<Disease> findAll() {
		@SuppressWarnings("unchecked")
		List<Disease> list = (List<Disease>) getCache(CACHE_DISEASE_LIST);
		if (list == null) {
			list = diseaseDao.findAllList(new Disease());
			putCache(CACHE_DISEASE_LIST, list);
		}
		return list;

	}

	/**
	 * id查询疾病
	 * 
	 * @param id
	 * @return
	 */
	public static Disease findById(String id) {
		List<Disease> DiseaseList = findAll();
		for (Disease Disease : DiseaseList) {
			if (StringUtils.equals(id, Disease.getId())) {
				return Disease;
			}
		}
		return new Disease();
	}

}