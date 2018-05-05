package org.apache.ibatis.thread;

import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PropertiesUtil {

	private static Logger logger = LoggerFactory
			.getLogger(PropertiesUtil.class);

	private static String filename = "/mybatis-refresh.properties";
	private static Properties pro = new Properties();
	static {
		try {
			pro.load(PropertiesUtil.class.getResourceAsStream(filename));
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			logger.error("Load mybatis-refresh “" + filename + "” file error.");
		}
	}

	public static int getInt(String key) {
		int i = 0;
		try {
			i = Integer.parseInt(getString(key));
		} catch (Exception e) {
		}
		return i;
	}

	public static String getString(String key) {
		return pro == null ? null : pro.getProperty(key);
	}

}
