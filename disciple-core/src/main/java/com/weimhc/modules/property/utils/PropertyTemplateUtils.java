package com.weimhc.modules.property.utils;

import java.util.List;

import com.thinkgem.javamg.common.utils.CacheUtils;
import com.thinkgem.javamg.common.utils.SpringContextHolder;
import com.weimhc.modules.property.dao.PropertyDao;
import com.weimhc.modules.property.dao.PropertyTemplateDao;
import com.weimhc.modules.property.dao.PropertyValueDao;
import com.weimhc.modules.property.entity.Property;
import com.weimhc.modules.property.entity.PropertyTemplate;
import com.weimhc.modules.property.entity.PropertyValue;

/**
 * @author szuo
 *
 */
public abstract class PropertyTemplateUtils {

	private static PropertyTemplateDao propertyTemplateDao = SpringContextHolder
			.getBean(PropertyTemplateDao.class);

	private static PropertyDao propertyDao = SpringContextHolder
			.getBean(PropertyDao.class);

	private static PropertyValueDao propertyValueDao = SpringContextHolder
			.getBean(PropertyValueDao.class);

	/** 模板相关缓存 */
	public final static String PROPERTY_TEMPLAT_CACHE = "propertyTemplateCache";

	/** 根据模板id存储相关缓存 */
	public final static String PROPERTY_TEMPALTE_ID = "tempate_id_";

	/**
	 * 获取相关缓存
	 * 
	 * @param key
	 * @return
	 */
	public static Object getCache(String key) {
		return getCache(key, null);
	}

	/**
	 * 获取相关缓存
	 * 
	 * @param key
	 * @return
	 */
	public static Object getCache(String key, Object defaultValue) {
		Object obj = CacheUtils.get(PROPERTY_TEMPLAT_CACHE, key);
		return obj == null ? defaultValue : obj;
	}

	/**
	 * 设置相关缓存
	 * 
	 * @param key
	 * @return
	 */
	public static void putCache(String key, Object value) {
		CacheUtils.put(PROPERTY_TEMPLAT_CACHE, key, value);
	}

	/**
	 * 根据key值，清除相关缓存
	 * 
	 * @param key
	 */
	public static void removeCache(String key) {
		CacheUtils.remove(PROPERTY_TEMPLAT_CACHE, key);
	}

	/**
	 * 根据id获取属性模板
	 * 
	 * @param id
	 * @return
	 */
	public static PropertyTemplate findById(String id) {
		PropertyTemplate propertyTemplate = propertyTemplateDao.get(id);
		propertyTemplate.setPropertyList(findPropertyByTemlateId(id));
		return propertyTemplate;
	}

	public static List<Property> findPropertyByTemlateId(String templateId) {
		Property property = new Property();
		property.setPropertyTemplate(new PropertyTemplate(templateId));
		return findPropertyList(property);
	}

	public static List<Property> findPropertyList(Property property) {
		List<Property> list = propertyDao.findAllList(property);
		list.stream().forEach(p -> {
			if (p.getShowType() != null && p.getShowType().getHasOptions()) {
				p.setPropertyValueList(
						propertyValueDao.findAllList(new PropertyValue(p)));
			}
		});
		return list;
	}
}
