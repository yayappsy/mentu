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
import com.weimhc.modules.base.dao.InterestCategoryDao;
import com.weimhc.modules.base.dao.InterestDao;
import com.weimhc.modules.base.entity.Interest;
import com.weimhc.modules.base.entity.InterestCategory;

/**
 * Utils - 兴趣
 * 
 * 
 */
public abstract class InterestUtils {

	private static InterestCategoryDao interestCategoryDao = SpringContextHolder
			.getBean(InterestCategoryDao.class);

	private static InterestDao interestDao = SpringContextHolder.getBean(InterestDao.class);

	/**
	 * 兴趣相关缓存 暂时未使用
	 */
	public final static String INTEREST_CACHE = "interestCategoryCache";
	/** 兴趣相关缓存列表 */
	public final static String CACHE_INTEREST_CATEGROY_LIST = "interestCategory_list";
	/** 兴趣分类相关缓存列表 */
	public final static String CACHE_INTEREST_CATEGROY_BY_ID = "interestCategory_id";

	/** 兴趣相关缓存列表 */
	public final static String CACHE_INTEREST_LIST_BY_CATEGROY_ID = "interest_category_id";

	/** 兴趣相关缓存列表 */
	public final static String CACHE_INTEREST_LIST = "interest_list";

	/**
	 * 获取兴趣相关缓存
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
	 * 设置兴趣相关缓存
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
		CacheUtils.remove(CACHE_INTEREST_CATEGROY_LIST);
		CacheUtils.remove(CACHE_INTEREST_CATEGROY_BY_ID);
	}

	/**
	 * 获取所有的兴趣分类
	 * 
	 * @return
	 */
	public static List<InterestCategory> findInterestCategoryAll() {
		@SuppressWarnings("unchecked")
		List<InterestCategory> list = (List<InterestCategory>) getCache(
				CACHE_INTEREST_CATEGROY_LIST);
		if (list == null || list.size() == 0) {
			list = interestCategoryDao.findAllList(new InterestCategory());
			putCache(CACHE_INTEREST_CATEGROY_LIST, list);
		}
		return list;

	}

	/**
	 * id查询兴趣分类
	 * 
	 * @param id
	 * @return
	 */
	public static InterestCategory findInterestCategoryById(String id) {
		List<InterestCategory> interestCategoryList = findInterestCategoryAll();
		for (InterestCategory entity : interestCategoryList) {
			if (StringUtils.equals(id, entity.getId())) {
				return entity;
			}
		}
		return new InterestCategory();
	}

	/**
	 * 根据分类id获取所有的兴趣
	 * 
	 * @return
	 */
	public static List<Interest> findInterestAll() {
		@SuppressWarnings("unchecked")
		List<Interest> list = (List<Interest>) getCache(CACHE_INTEREST_LIST);
		if (list == null || list.size() == 0) {
			list = interestDao.findAllList(new Interest());
			putCache(CACHE_INTEREST_LIST, list);
		}
		return list;

	}

	/**
	 * 根据分类id获取所有的兴趣
	 * 
	 * @return
	 */
	public static List<Interest> findInterestAll(String categoryId) {
		@SuppressWarnings("unchecked")
		List<Interest> list = (List<Interest>) getCache(
				CACHE_INTEREST_LIST_BY_CATEGROY_ID + categoryId);
		if (list == null || list.size() == 0) {
			list = interestDao.findAllList(new Interest(new InterestCategory(categoryId)));
			putCache(CACHE_INTEREST_LIST_BY_CATEGROY_ID + categoryId, list);
		}
		return list;

	}

	/**
	 * id 查询兴趣
	 * 
	 * @param id
	 * @return
	 */
	public static Interest findInterestById(String id) {
		List<Interest> interestList = findInterestAll();
		for (Interest entity : interestList) {
			if (StringUtils.equals(id, entity.getId())) {
				return entity;
			}
		}
		return new Interest();
	}

}