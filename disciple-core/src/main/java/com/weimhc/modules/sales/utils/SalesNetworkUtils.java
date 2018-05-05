/*
 * 
 * 
 * 
 */
package com.weimhc.modules.sales.utils;

import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.thinkgem.javamg.common.utils.CacheUtils;
import com.thinkgem.javamg.common.utils.SpringContextHolder;
import com.weimhc.modules.sales.dao.SalesNetworkTypeDao;
import com.weimhc.modules.sales.entity.SalesNetworkType;

/**
 * Utils - 营业网点
 * 
 * 
 */
public abstract class SalesNetworkUtils {

	private static SalesNetworkTypeDao salesNetworkTypeDao = SpringContextHolder
			.getBean(SalesNetworkTypeDao.class);

	/**
	 * 营业网点相关缓存 暂时未使用
	 */
	public final static String SALES_NETWORK_CACHE = "salesNetworkCache";
	/** 营业网点类型相关缓存列表 */
	public final static String CACHE_SALES_NETWORK_TYPE_LIST = "salesNetworkType_list";
	/** 营业网点类型相关缓存列表 */
	public final static String CACHE_SALES_NETWORK_TYPE_BY_ID = "salesNetworkType_id";

	/**
	 * 获取营业网点相关缓存
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
	 * 设置营业网点相关缓存
	 * 
	 * @param key
	 * @return
	 */
	public static void putCache(String key, Object value) {
		CacheUtils.put(key, value);
	}

	/**
	 * 获取所有的营业网点类型
	 * 
	 * @return
	 */
	public static List<SalesNetworkType> findSalesNetworkTypeAll() {
		@SuppressWarnings("unchecked")
		List<SalesNetworkType> list = (List<SalesNetworkType>) getCache(
				CACHE_SALES_NETWORK_TYPE_LIST);
		if (list == null || list.size() == 0) {
			list = salesNetworkTypeDao.findAllList(new SalesNetworkType());
			putCache(CACHE_SALES_NETWORK_TYPE_LIST, list);
		}
		return list;

	}

	/**
	 * id查询营业网点类型
	 * 
	 * @param id
	 * @return
	 */
	public static SalesNetworkType findSalesNetworkTypeById(String id) {
		List<SalesNetworkType> salesNetworkTypeList = findSalesNetworkTypeAll();
		for (SalesNetworkType salesNetworkType : salesNetworkTypeList) {
			if (StringUtils.equals(id, salesNetworkType.getId())) {
				return salesNetworkType;
			}
		}
		return new SalesNetworkType();
	}

}