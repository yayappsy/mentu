package com.weimhc.framework.spring.support;

/**
 * 多数据源实现-不区分主从
 * 
 * @author zsm
 * @version 1.0
 */
public abstract class DataSourceKeyHolder {

	private static final ThreadLocal<String> contextHolder = new ThreadLocal<String>();

	public static void setDataSourceKey(String dataSourceKey) {
		contextHolder.set(dataSourceKey);
	}

	public static String getDataSourceKey() {
		return contextHolder.get();
	}

	public static void clearDataSourceKey() {
		contextHolder.remove();
	}
}
