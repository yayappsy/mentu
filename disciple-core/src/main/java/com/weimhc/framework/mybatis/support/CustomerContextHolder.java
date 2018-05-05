package com.weimhc.framework.mybatis.support;

/**
 * 多数据源
 * 
 * @author zsm
 * @version 1.0
 */
public abstract class CustomerContextHolder {

	public final static String SESSION_FACTORY_MYSQL = "mysql";
	public final static String SESSION_FACTORY_ORACLE = "oracle";

	private static final ThreadLocal<String> contextHolder = new ThreadLocal<String>();

	public static void setContextType(String contextType) {
		contextHolder.set(contextType);
	}

	public static String getContextType() {
		return contextHolder.get();
	}

	public static void clearContextType() {
		contextHolder.remove();
	}
}
