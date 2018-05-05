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
import com.weimhc.modules.base.dao.ThirdVideoCloudDao;
import com.weimhc.modules.base.entity.ThirdVideoCloud;

/**
 * Utils - 视频云
 * 
 * 
 */
public abstract class ThirdVideoCloudUtils {

	private static ThirdVideoCloudDao thirdVideoCloudDao = SpringContextHolder
			.getBean(ThirdVideoCloudDao.class);

	/**
	 * 第三方视频云相关缓存 暂时未使用
	 */
	public final static String THIRD_CLOUD_VIDEO_CACHE = "thirdVideoCloudCache";
	/** 第三方视频云相关缓存列表 */
	public final static String CACHE_THIRD_CLOUD_VIDEO_LIST = "thirdVideoCloud_list";
	/** 第三方视频云相关缓存列表 */
	public final static String CACHE_THIRD_CLOUD_VIDEO_BY_ID = "thirdVideoCloud_id";

	/**
	 * 获取第三方视频云相关缓存
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
	 * 设置第三方视频云相关缓存
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
		CacheUtils.remove(CACHE_THIRD_CLOUD_VIDEO_LIST);
		CacheUtils.remove(CACHE_THIRD_CLOUD_VIDEO_BY_ID);
	}

	/**
	 * 获取所有的第三方视频云
	 * 
	 * @return
	 */
	public static List<ThirdVideoCloud> findAll() {
		@SuppressWarnings("unchecked")
		List<ThirdVideoCloud> list = (List<ThirdVideoCloud>) getCache(
				CACHE_THIRD_CLOUD_VIDEO_LIST);
		if (list == null || list.size() == 0) {
			list = thirdVideoCloudDao.findAllList(new ThirdVideoCloud());
			putCache(CACHE_THIRD_CLOUD_VIDEO_LIST, list);
		}
		return list;

	}

	/**
	 * id查询第三方视频云
	 * 
	 * @param id
	 * @return
	 */
	public static ThirdVideoCloud findById(String id) {
		List<ThirdVideoCloud> thirdVideoCloudList = findAll();
		for (ThirdVideoCloud entity : thirdVideoCloudList) {
			if (StringUtils.equals(id, entity.getId())) {
				return entity;
			}
		}
		return new ThirdVideoCloud();

	}
}
