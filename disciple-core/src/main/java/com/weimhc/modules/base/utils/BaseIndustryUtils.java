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
import com.weimhc.modules.base.dao.BaseIndustryDao;
import com.weimhc.modules.base.entity.BaseIndustry;

/**
 * Utils - 行业
 * 
 * 
 */
public abstract class BaseIndustryUtils {

	private static BaseIndustryDao industryDao = SpringContextHolder
			.getBean(BaseIndustryDao.class);

	/**
	 * 行业相关缓存 暂时未使用
	 */
	public final static String INDUSTRY_CACHE = "industryCache";

	/** 行业相关缓存列表 */
	public final static String CACHE_INDUSTRY_LIST = "industry_list";
	/** 行业相关缓存列表 */
	public final static String CACHE_INDUSTRY_BY_ID = "industry_id";

	/**
	 * 获取行业相关缓存
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
	 * 设置行业相关缓存
	 * 
	 * @param key
	 * @return
	 */
	public static void putCache(String key, Object value) {
		CacheUtils.put(key, value);
	}

	/**
	 * 获取所有的行业
	 * 
	 * @return
	 */
	public static List<BaseIndustry> findAll() {
		@SuppressWarnings("unchecked")
		List<BaseIndustry> list = (List<BaseIndustry>) getCache(CACHE_INDUSTRY_LIST);
		if (list == null) {
			list = industryDao.findAllList(new BaseIndustry());
			putCache(CACHE_INDUSTRY_LIST, list);
		}
		return list;

	}

	/**
	 * id查询行业
	 * 
	 * @param id
	 * @return
	 */
	public static BaseIndustry findById(String id) {
		List<BaseIndustry> industryList = findAll();
		for (BaseIndustry industry : industryList) {
			if (StringUtils.equals(id, industry.getId())) {
				return industry;
			}
		}
		return new BaseIndustry();
	}

}