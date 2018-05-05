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
import com.weimhc.modules.base.dao.RelationshipDao;
import com.weimhc.modules.base.entity.Relationship;

/**
 * Utils - 民族
 * 
 * 
 */
public abstract class RelationshipUtils {

	private static RelationshipDao relationshipDao = SpringContextHolder
			.getBean(RelationshipDao.class);

	/**
	 * 民族相关缓存 暂时未使用
	 */
	public final static String NATION_CACHE = "RelationshipCache";
	/** 民族相关缓存列表 */
	public final static String CACHE_NATION_LIST = "Relationship_list";
	/** 民族相关缓存列表 */
	public final static String CACHE_NATION_BY_ID = "Relationship_id";

	/**
	 * 获取民族相关缓存
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
	 * 设置民族相关缓存
	 * 
	 * @param key
	 * @return
	 */
	public static void putCache(String key, Object value) {
		CacheUtils.put(key, value);
	}

	/**
	 * 获取所有的民族
	 * 
	 * @return
	 */
	public static List<Relationship> findAll() {
		@SuppressWarnings("unchecked")
		List<Relationship> list = (List<Relationship>) getCache(
				CACHE_NATION_LIST);
		if (list == null) {
			list = relationshipDao.findAllList(new Relationship());
			putCache(CACHE_NATION_LIST, list);
		}
		return list;

	}

	/**
	 * id查询民族
	 * 
	 * @param id
	 * @return
	 */
	public static Relationship findById(String id) {
		List<Relationship> list = findAll();
		for (Relationship e : list) {
			if (StringUtils.equals(id, e.getId())) {
				return e;
			}
		}
		return new Relationship();
	}

}