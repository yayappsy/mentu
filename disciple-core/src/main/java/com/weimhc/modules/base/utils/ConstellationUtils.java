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
import com.weimhc.modules.base.dao.ConstellationDao;
import com.weimhc.modules.base.entity.Constellation;

/**
 * Utils - 星座
 * 
 * 
 */
public abstract class ConstellationUtils {

	private static ConstellationDao constellationDao = SpringContextHolder
			.getBean(ConstellationDao.class);

	/**
	 * 星座相关缓存 暂时未使用
	 */
	public final static String CONSTELLATION_CACHE = "constellationCache";

	/** 星座相关缓存列表 */
	public final static String CACHE_CONSTELLATION_LIST = "constellation_list";
	/** 星座相关缓存列表 */
	public final static String CACHE_CONSTELLATION_BY_ID = "constellation_id";

	/**
	 * 获取星座相关缓存
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
	 * 设置星座相关缓存
	 * 
	 * @param key
	 * @return
	 */
	public static void putCache(String key, Object value) {
		CacheUtils.put(key, value);
	}

	/**
	 * 获取所有的星座
	 * 
	 * @return
	 */
	public static List<Constellation> findAll() {
		@SuppressWarnings("unchecked")
		List<Constellation> list = (List<Constellation>) getCache(
				CACHE_CONSTELLATION_LIST);
		if (list == null || list.size() == 0) {
			list = constellationDao.findAllList(new Constellation());
			putCache(CACHE_CONSTELLATION_LIST, list);
		}
		return list;

	}

	/**
	 * id查询星座
	 * 
	 * @param id
	 * @return
	 */
	public static Constellation findById(String id) {
		List<Constellation> constellationList = findAll();
		for (Constellation constellation : constellationList) {
			if (StringUtils.equals(id, constellation.getId())) {
				return constellation;
			}
		}
		return new Constellation();
	}

}