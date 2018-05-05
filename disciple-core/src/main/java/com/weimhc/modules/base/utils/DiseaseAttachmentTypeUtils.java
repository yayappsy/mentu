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
import com.weimhc.modules.base.dao.DiseaseAttachmentTypeDao;
import com.weimhc.modules.base.entity.DiseaseAttachmentType;

/**
 * Utils - 疾病附件类型
 * 
 * 
 */
public abstract class DiseaseAttachmentTypeUtils {

	private static DiseaseAttachmentTypeDao diseaseAttachmentTypeDao = SpringContextHolder
			.getBean(DiseaseAttachmentTypeDao.class);

	/**
	 * 疾病附件类型相关缓存 暂时未使用
	 */
	public final static String DISEASE_ATTACHMENT_TYPE_CACHE = "DiseaseAttachmentTypeCache";
	/** 疾病附件类型相关缓存列表 */
	public final static String CACHE_DISEASE_ATTACHMENT_TYPE_LIST = "DiseaseAttachmentType_list";
	/** 疾病附件类型相关缓存列表 */
	public final static String CACHE_DISEASE_ATTACHMENT_TYPE_BY_ID = "DiseaseAttachmentType_id";

	/**
	 * 获取疾病附件类型相关缓存
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
	 * 设置疾病附件类型相关缓存
	 * 
	 * @param key
	 * @return
	 */
	public static void putCache(String key, Object value) {
		CacheUtils.put(key, value);
	}

	/**
	 * 获取所有的疾病附件类型
	 * 
	 * @return
	 */
	public static List<DiseaseAttachmentType> findAll() {
		@SuppressWarnings("unchecked")
		List<DiseaseAttachmentType> list = (List<DiseaseAttachmentType>) getCache(
				CACHE_DISEASE_ATTACHMENT_TYPE_LIST);
		if (list == null || list.size() == 0) {
			list = diseaseAttachmentTypeDao
					.findAllList(new DiseaseAttachmentType());
			putCache(CACHE_DISEASE_ATTACHMENT_TYPE_LIST, list);
		}
		return list;

	}

	/**
	 * id查询疾病附件类型
	 * 
	 * @param id
	 * @return
	 */
	public static DiseaseAttachmentType findById(String id) {
		List<DiseaseAttachmentType> list = findAll();
		for (DiseaseAttachmentType e : list) {
			if (StringUtils.equals(id, e.getId())) {
				return e;
			}
		}
		return new DiseaseAttachmentType();
	}

}