package com.weimhc.framework.utils;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * Map 与 bean 转换
 * 
 * @author szuo
 *
 */
public abstract class BeanMapUtils {

	private static Logger logger = LoggerFactory.getLogger(BeanMapUtils.class);

	/**
	 * map 转换为bean
	 * 
	 * @see org.apache.commons.beanutils.BeanUtils
	 * 
	 * @param map
	 * @param beanClass
	 * @return
	 * @throws Exception
	 */
	public static Object mapToObject(Map<String, Object> map,
			Class<?> beanClass) throws Exception {
		if (map == null)
			return null;

		Object obj = beanClass.newInstance();

		org.apache.commons.beanutils.BeanUtils.populate(obj, map);

		return obj;
	}

	/***
	 * bean 转换为 map
	 * 
	 * @param obj
	 * @return
	 */
	public static Map<?, ?> objectToMap(Object obj) {
		if (obj == null)
			return null;

		return new org.apache.commons.beanutils.BeanMap(obj);
	}

}
