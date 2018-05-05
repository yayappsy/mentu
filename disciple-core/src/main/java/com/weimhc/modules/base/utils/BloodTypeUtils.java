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
import com.weimhc.modules.base.dao.BloodTypeDao;
import com.weimhc.modules.base.entity.BloodType;

/**
 * Utils - 血型
 * 
 * 
 */
public abstract class BloodTypeUtils {

	private static BloodTypeDao bloodTypeDao = SpringContextHolder
			.getBean(BloodTypeDao.class);

	/**
	 * 血型相关缓存 暂时未使用
	 */
	public final static String BLOOD_TYPE_CACHE = "bloodTypeCache";
	/** 血型相关缓存列表 */
	public final static String CACHE_BLOOD_TYPE_LIST = "bloodType_list";
	/** 血型相关缓存列表 */
	public final static String CACHE_BLOOD_TYPE_BY_ID = "bloodType_id";

	/**
	 * 获取血型相关缓存
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
	 * 设置血型相关缓存
	 * 
	 * @param key
	 * @return
	 */
	public static void putCache(String key, Object value) {
		CacheUtils.put(key, value);
	}

	/**
	 * 清除缓存
	 * 
	 * @param key
	 * @return
	 */
	public static void removeCache(String key) {
		CacheUtils.remove(key);
	}

	/**
	 * 清除所有缓存缓存
	 * 
	 * @param key
	 * @return
	 */
	public static void clearCache() {
		CacheUtils.remove(CACHE_BLOOD_TYPE_LIST);
		CacheUtils.remove(CACHE_BLOOD_TYPE_BY_ID);
	}

	/**
	 * 获取所有的血型
	 * 
	 * @return
	 */
	public static List<BloodType> findAll() {
		@SuppressWarnings("unchecked")
		List<BloodType> list = (List<BloodType>) getCache(
				CACHE_BLOOD_TYPE_LIST);
		if (list == null || list.size() == 0) {
			list = bloodTypeDao.findAllList(new BloodType());
			putCache(CACHE_BLOOD_TYPE_LIST, list);
		}
		return list;

	}

	/**
	 * id查询血型
	 * 
	 * @param id
	 * @return
	 */
	public static BloodType findById(String id) {
		List<BloodType> bloodTypeList = findAll();
		for (BloodType entity : bloodTypeList) {
			if (StringUtils.equals(id, entity.getId())) {
				return entity;
			}
		}
		return new BloodType();
	}

}