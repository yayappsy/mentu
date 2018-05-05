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
import com.weimhc.modules.base.dao.WorkingYearsDao;
import com.weimhc.modules.base.entity.WorkingYears;

/**
 * Utils - 工作年限
 * 
 * 
 */
public abstract class WorkingYearsUtils {

	private static WorkingYearsDao workingYearsDao = SpringContextHolder
			.getBean(WorkingYearsDao.class);

	/**
	 * 工作年限相关缓存 暂时未使用
	 */
	public final static String WORKING_YEARS_CACHE = "workingYearsCache";

	/** 工作年限相关缓存列表 */
	public final static String CACHE_WORKING_YEARS_LIST = "workingYears_list";
	/** 工作年限相关缓存列表 */
	public final static String CACHE_WORKING_YEARS_BY_ID = "workingYears_id";

	/**
	 * 获取工作年限相关缓存
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
	 * 设置工作年限相关缓存
	 * 
	 * @param key
	 * @return
	 */
	public static void putCache(String key, Object value) {
		CacheUtils.put(key, value);
	}

	/**
	 * 获取所有的工作年限
	 * 
	 * @return
	 */
	public static List<WorkingYears> findAll() {
		@SuppressWarnings("unchecked")
		List<WorkingYears> list = (List<WorkingYears>) getCache(
				CACHE_WORKING_YEARS_LIST);
		if (list == null) {
			list = workingYearsDao.findAllList(new WorkingYears());
			putCache(CACHE_WORKING_YEARS_LIST, list);
		}
		return list;

	}

	/**
	 * id查询工作年限
	 * 
	 * @param id
	 * @return
	 */
	public static WorkingYears findById(String id) {
		List<WorkingYears> educationList = findAll();
		for (WorkingYears education : educationList) {
			if (StringUtils.equals(id, education.getId())) {
				return education;
			}
		}
		return new WorkingYears();
	}

}