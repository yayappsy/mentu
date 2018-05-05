/*
 * 
 * 
 * 
 */
package com.weimhc.modules.activity.utils;

import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.thinkgem.javamg.common.utils.CacheUtils;
import com.thinkgem.javamg.common.utils.SpringContextHolder;
import com.weimhc.modules.activity.dao.ActivityTypeDao;
import com.weimhc.modules.activity.entity.ActivityType;

/**
 * Utils - 活动
 * 
 * 
 */
public abstract class ActivityUtils {

	private static ActivityTypeDao activityTypeDao = SpringContextHolder
			.getBean(ActivityTypeDao.class);

	/**
	 * 活动相关缓存 暂时未使用
	 */
	public final static String ACTIVITY_CACHE = "activityCache";
	/** 活动类型相关缓存列表 */
	public final static String CACHE_ACTIVITY_TYPE_LIST = "activityType_list";
	/** 活动类型相关缓存列表 */
	public final static String CACHE_ACTIVITY_TYPE_BY_ID = "activityType_id";

	/**
	 * 获取活动相关缓存
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
	 * 设置活动相关缓存
	 * 
	 * @param key
	 * @return
	 */
	public static void putCache(String key, Object value) {
		CacheUtils.put(key, value);
	}

	/**
	 * 获取所有的活动类型
	 * 
	 * @return
	 */
	public static List<ActivityType> findActivityTypeAll() {
		@SuppressWarnings("unchecked")
		List<ActivityType> list = (List<ActivityType>) getCache(
				CACHE_ACTIVITY_TYPE_LIST);
		if (list == null || list.size() == 0) {
			list = activityTypeDao.findAllList(new ActivityType());
			putCache(CACHE_ACTIVITY_TYPE_LIST, list);
		}
		return list;

	}

	/**
	 * id查询活动活动类型
	 * 
	 * @param id
	 * @return
	 */
	public static ActivityType findActivityTypeById(String id) {
		List<ActivityType> activityTypeList = findActivityTypeAll();
		for (ActivityType activityType : activityTypeList) {
			if (StringUtils.equals(id, activityType.getId())) {
				return activityType;
			}
		}
		return new ActivityType();
	}

}