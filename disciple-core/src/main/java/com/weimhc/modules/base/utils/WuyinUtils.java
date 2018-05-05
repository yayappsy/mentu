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
import com.weimhc.modules.base.dao.WuyinDao;
import com.weimhc.modules.base.entity.Wuyin;

/**
 * Utils - 五音
 * 
 * 
 */
public abstract class WuyinUtils {

	private static WuyinDao wuyinDao = SpringContextHolder
			.getBean(WuyinDao.class);

	/**
	 * 五音相关缓存 暂时未使用
	 */
	public final static String WUYIN_CACHE = "wuyinCache";
	/** 五音相关缓存列表 */
	public final static String CACHE_WUYIN_LIST = "wuyin_list";
	/** 五音相关缓存列表 */
	public final static String CACHE_WUYIN_BY_ID = "wuyin_id";

	/**
	 * 获取五音相关缓存
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
	 * 设置五音相关缓存
	 * 
	 * @param key
	 * @return
	 */
	public static void putCache(String key, Object value) {
		CacheUtils.put(key, value);
	}

	/**
	 * 获取所有的五音
	 * 
	 * @return
	 */
	public static List<Wuyin> findAll() {
		@SuppressWarnings("unchecked")
		List<Wuyin> list = (List<Wuyin>) getCache(CACHE_WUYIN_LIST);
		if (list == null) {
			list = wuyinDao.findAllList(new Wuyin());
			putCache(CACHE_WUYIN_LIST, list);
		}
		return list;

	}

	/**
	 * id查询五音
	 * 
	 * @param id
	 * @return
	 */
	public static Wuyin findById(String id) {
		List<Wuyin> wuyinList = findAll();
		for (Wuyin wuyin : wuyinList) {
			if (StringUtils.equals(id, wuyin.getId())) {
				return wuyin;
			}
		}
		return new Wuyin();
	}

}