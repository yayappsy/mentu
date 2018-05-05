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
import com.weimhc.modules.base.dao.ReligiousBeliefDao;
import com.weimhc.modules.base.entity.ReligiousBelief;

/**
 * Utils - 宗教信仰
 * 
 * 
 */
public abstract class ReligiousBeliefUtils {

	private static ReligiousBeliefDao religiousBeliefDao = SpringContextHolder
			.getBean(ReligiousBeliefDao.class);

	/**
	 * 宗教信仰相关缓存 暂时未使用
	 */
	public final static String RELIGIOUSBELIEF_CACHE = "ReligiousBeliefCache";
	/** 宗教信仰相关缓存列表 */
	public final static String CACHE_RELIGIOUSBELIEF_LIST = "ReligiousBelief_list";
	/** 宗教信仰相关缓存列表 */
	public final static String CACHE_RELIGIOUSBELIEF_BY_ID = "ReligiousBelief_id";

	/**
	 * 获取宗教信仰相关缓存
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
	 * 设置宗教信仰相关缓存
	 * 
	 * @param key
	 * @return
	 */
	public static void putCache(String key, Object value) {
		CacheUtils.put(key, value);
	}

	/**
	 * 获取所有的宗教信仰
	 * 
	 * @return
	 */
	public static List<ReligiousBelief> findAll() {
		@SuppressWarnings("unchecked")
		List<ReligiousBelief> list = (List<ReligiousBelief>) getCache(
				CACHE_RELIGIOUSBELIEF_LIST);
		if (list == null) {
			list = religiousBeliefDao.findAllList(new ReligiousBelief());
			putCache(CACHE_RELIGIOUSBELIEF_LIST, list);
		}
		return list;

	}

	/**
	 * id查询宗教信仰
	 * 
	 * @param id
	 * @return
	 */
	public static ReligiousBelief findById(String id) {
		List<ReligiousBelief> list = findAll();
		for (ReligiousBelief e : list) {
			if (StringUtils.equals(id, e.getId())) {
				return e;
			}
		}
		return new ReligiousBelief();
	}

}