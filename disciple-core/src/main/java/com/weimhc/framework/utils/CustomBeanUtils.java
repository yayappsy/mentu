package com.weimhc.framework.utils;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

/**
 * 
 * 工具类
 *
 * 
 * @author szuo
 */
public abstract class CustomBeanUtils {

	private static Logger logger = LoggerFactory
			.getLogger(CustomBeanUtils.class);

	/**
	 * 复制List中的数据到另一个List
	 * 
	 * 属于不同的实体，同名复制
	 * 
	 * @param <S>
	 * 
	 * @param poList
	 * @param voClass
	 * @return
	 */
	public static <S, T> List<T> copyList(List<S> sourceList,
			Class<T> targetClass) {

		List<T> targetList = Lists.newArrayList();

		for (S sourceObj : sourceList) {
			T targetObj = null;
			try {
				targetObj = targetClass.newInstance();
			} catch (InstantiationException | IllegalAccessException e) {
				logger.error(e.getMessage(), e);
				;
			}
			BeanUtils.copyProperties(sourceObj, targetObj);
			targetList.add(targetObj);
		}
		return targetList;

	}

	/**
	 * 复制特定Map中的数据到另一个特定Map
	 * 
	 * 属于不同的实体，同名复制
	 * 
	 * @param sourceList
	 * @param targetClass
	 * @return
	 */
	public static <S, T> Map<String, List<T>> copyMap(
			Map<String, List<S>> sourceMap, Class<T> targetClass) {

		Map<String, List<T>> targetMap = Maps.newHashMap();

		for (String key : sourceMap.keySet()) {
			List<S> sourceList = sourceMap.get(key);

			List<T> targetList = copyList(sourceList, targetClass);

			targetMap.put(key, targetList);
		}

		return targetMap;

	}

}
