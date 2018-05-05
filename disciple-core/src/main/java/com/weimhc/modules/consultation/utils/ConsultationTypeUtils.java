/*
 * 
 * 
 * 
 */
package com.weimhc.modules.consultation.utils;

import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.thinkgem.javamg.common.utils.CacheUtils;
import com.thinkgem.javamg.common.utils.SpringContextHolder;
import com.weimhc.modules.consultation.dao.ConsultationTypeDao;
import com.weimhc.modules.consultation.entity.ConsultationType;
/**
 * Utils - 咨询问题类型
 * 
 * 
 */
public abstract class ConsultationTypeUtils {

	private static ConsultationTypeDao ConsultationTypeDao = SpringContextHolder
			.getBean(ConsultationTypeDao.class);

	/**
	 * 咨询问题类型相关缓存 暂时未使用
	 */
	public final static String CONSULTATION_TYPE_CACHE = "ConsultationTypeCache";

	/** 咨询问题类型相关缓存列表 */
	public final static String CACHE_CONSULTATION_TYPE_LIST = "ConsultationType_list";
	/** 咨询问题类型相关缓存列表 */
	public final static String CACHE_CONSULTATION_TYPE_BY_ID = "ConsultationType_id";

	/**
	 * 获取咨询问题类型相关缓存
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
	 * 设置咨询问题类型相关缓存
	 * 
	 * @param key
	 * @return
	 */
	public static void putCache(String key, Object value) {
		CacheUtils.put(key, value);
	}

	/**
	 * 获取所有的咨询问题类型
	 * 
	 * @return
	 */
	public static List<ConsultationType> findAll() {
		@SuppressWarnings("unchecked")
		List<ConsultationType> list = (List<ConsultationType>) getCache(
				CACHE_CONSULTATION_TYPE_LIST);
		if (list == null) {
			list = ConsultationTypeDao.findAllList(new ConsultationType());
			putCache(CACHE_CONSULTATION_TYPE_LIST, list);
		}
		return list;

	}

	/**
	 * id查询咨询问题类型
	 * 
	 * @param id
	 * @return
	 */
	public static ConsultationType findById(String id) {
		List<ConsultationType> ConsultationTypeList = findAll();
		for (ConsultationType ConsultationType : ConsultationTypeList) {
			if (StringUtils.equals(id, ConsultationType.getId())) {
				return ConsultationType;
			}
		}
		return new ConsultationType();
	}


}