/*
 * 
 * 
 * 
 */
package com.weimhc.modules.base.utils;

import java.util.List;

import com.google.common.collect.Lists;
import com.thinkgem.javamg.common.utils.CacheUtils;
import com.thinkgem.javamg.common.utils.SpringContextHolder;
import com.thinkgem.javamg.common.utils.StringUtils;
import com.weimhc.framework.utils.ConstantUtils;
import com.weimhc.modules.base.dao.BodyCategoryDao;
import com.weimhc.modules.base.dao.BodyPartDao;
import com.weimhc.modules.base.dao.BodyPartItemDao;
import com.weimhc.modules.base.entity.BodyCategory;
import com.weimhc.modules.base.entity.BodyPart;
import com.weimhc.modules.base.entity.BodyPartItem;

/**
 * Utils - 身体部位
 * 
 * 
 */
public abstract class BodyPartUtils {

	private static BodyCategoryDao bodyCategoryDao = SpringContextHolder
			.getBean(BodyCategoryDao.class);

	private static BodyPartDao bodyPartDao = SpringContextHolder
			.getBean(BodyPartDao.class);

	private static BodyPartItemDao bodyPartItemDao = SpringContextHolder
			.getBean(BodyPartItemDao.class);

	/**
	 * 身体部位相关缓存 暂时未使用
	 */
	public final static String BODY_PART_CACHE = "bodyPartCache";

	/** 身体部位相关缓存列表 */
	public final static String CACHE_BODY_CATEGORY_LIST = "bodyCategory_list";

	/** 身体部位相关缓存列表 */
	public final static String CACHE_BODY_PART_LIST = "bodyPart_list";
	/** 身体部位相关缓存列表 */
	public final static String CACHE_BODY_PART_BY_ID = "bodyPart_id";

	/** 身体部位相关缓存列表 */
	public final static String CACHE_BODY_PART_ITEM_LIST = "bodyPartItem_list";

	/**
	 * 获取身体部位相关缓存
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
	 * 设置身体部位相关缓存
	 * 
	 * @param key
	 * @return
	 */
	public static void putCache(String key, Object value) {
		CacheUtils.put(key, value);
	}

	/**
	 * 获取 所有的分类
	 * 
	 * @return
	 */
	public static List<BodyCategory> findBodyCategoryAll() {
		@SuppressWarnings("unchecked")
		List<BodyCategory> list = (List<BodyCategory>) getCache(
				CACHE_BODY_CATEGORY_LIST);
		if (list == null || list.size() == 0) {
			list = bodyCategoryDao.findAllList(new BodyCategory());
			putCache(CACHE_BODY_CATEGORY_LIST, list);
		}
		return list;
	}

	/**
	 * id查询身体部位分类
	 * 
	 * @param id
	 * @return
	 */
	public static BodyCategory findBodyCategoryById(String id) {
		for (BodyCategory e : findBodyCategoryAll()) {
			if (StringUtils.equals(id, e.getId())) {
				return e;
			}
		}
		return new BodyCategory();
	}

	/**
	 * 获取所有的身体部位
	 * 
	 * @return
	 */
	public static List<BodyPart> findBodyPartAll() {
		@SuppressWarnings("unchecked")
		List<BodyPart> list = (List<BodyPart>) getCache(CACHE_BODY_PART_LIST);
		if (list == null) {
			list = bodyPartDao.findAllList(new BodyPart());
			putCache(CACHE_BODY_PART_LIST, list);
		}
		return list;
	}

	/**
	 * id查询身体部位
	 * 
	 * @param id
	 * @return
	 */
	public static BodyPart findBodyPartById(String id) {
		List<BodyPart> list = findBodyPartAll();
		for (BodyPart e : list) {
			if (StringUtils.equals(id, e.getId())) {
				return e;
			}
		}
		return new BodyPart();
	}

	/**
	 * 获取所有的身体部位
	 * 
	 * @return
	 */
	public static List<BodyPartItem> findItemAll() {
		@SuppressWarnings("unchecked")
		List<BodyPartItem> list = (List<BodyPartItem>) getCache(
				CACHE_BODY_PART_ITEM_LIST);
		if (list == null || list.size() == 0) {
			list = bodyPartItemDao.findAllList(new BodyPartItem());
			putCache(CACHE_BODY_PART_ITEM_LIST, list);
		}
		return list;
	}

	/**
	 * id查询身体部位
	 * 
	 * @param id
	 * @return
	 */
	public static BodyPartItem findItemById(String id) {
		List<BodyPartItem> list = findItemAll();
		for (BodyPartItem e : list) {
			if (StringUtils.equals(id, e.getId())) {
				return e;
			}
		}
		return new BodyPartItem();
	}

	/**
	 * 根据身体部位分类id 查询身体部位
	 * 
	 * @param categoryId
	 * @return
	 */
	public static List<BodyPart> findByBodyCategoryId(String bodyCategoryId) {
		List<BodyPart> list = Lists.newArrayList();
		for (BodyPart e : findBodyPartAll()) {
			if (e.getBodyCategory() != null && StringUtils
					.equals(bodyCategoryId, e.getBodyCategory().getId())) {
				list.add(e);
			}
		}
		return list;
	}

	/**
	 * 根据身体部位id 查询身体部位所有细节并组合成字符串
	 * 
	 * @param categoryId
	 * @return
	 */
	public static String findPartsStrByBodyCategoryId(String bodyCategoryId) {
		List<String> list = Lists.newArrayList();
		for (BodyPart e : findByBodyCategoryId(bodyCategoryId)) {
			list.add(e.getId());
		}
		return StringUtils.join(list, ConstantUtils.SEPARATOR_COMMA);
	}

	/**
	 * 根据身体部位id 查询身体部位所有细节
	 * 
	 * @param id
	 * @return
	 */
	public static List<BodyPartItem> findByBodyPartId(String bodyPartId) {
		List<BodyPartItem> list = Lists.newArrayList();
		for (BodyPartItem e : findItemAll()) {
			if (StringUtils.equals(bodyPartId, e.getBodyPart().getId())) {
				list.add(e);
			}
		}
		return list;
	}

	/**
	 * 根据身体部位id 查询身体部位所有细节并组合成字符串
	 * 
	 * @param id
	 * @return
	 */
	public static String findItemsStrByBodyPartId(String bodyPartId) {
		List<String> list = Lists.newArrayList();
		for (BodyPartItem e : findByBodyPartId(bodyPartId)) {
			list.add(e.getId());
		}
		return StringUtils.join(list, ConstantUtils.SEPARATOR_COMMA);
	}

	/**
	 * 根据身体部位id 查询身体部位所有细节并组合成字符串
	 * 
	 * @param id
	 * @return
	 */
	public static String findItemsStrByBodyCategoyId(String bodyCategoryId) {

		List<String> list = Lists.newArrayList();
		for (BodyPart bodyPart : findByBodyCategoryId(bodyCategoryId)) {
			for (BodyPartItem e : findByBodyPartId(bodyPart.getId())) {
				list.add(e.getId());
			}
		}

		return StringUtils.join(list, ConstantUtils.SEPARATOR_COMMA);
	}

}