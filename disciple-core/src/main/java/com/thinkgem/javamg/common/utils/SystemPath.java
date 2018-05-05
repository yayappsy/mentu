/**
 * 
 */
package com.thinkgem.javamg.common.utils;

import java.net.URL;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @date Dec 14, 2008
 * @version v 1.0
 * @description 得到当前应用的系统路径
 */
public class SystemPath {

	private static Logger logger = LoggerFactory.getLogger(SystemPath.class);

	/***
	 * 获取项目的系统路径，绝对路径
	 * 
	 * @return
	 */
	public static String getSysPath() {
		URL url = Thread.currentThread().getContextClassLoader()
				.getResource("");
		String path = url.getPath();
		logger.debug("system path是： " + path);
		path = path.replaceFirst("WEB-INF/classes/", "");
		String resultPath = FileUtils.path(path);
		return resultPath;
	}

	/***
	 * 获取class的绝对路径
	 * 
	 * @return
	 */
	public static String getClassPath() {
		URL url = Thread.currentThread().getContextClassLoader()
				.getResource("");
		String path = url.getPath();
		logger.debug("ClassPath是： " + path);
		String resultPath = FileUtils.path(path);
		return resultPath;
	}

	public static String getSystempPath() {
		return System.getProperty("java.io.tmpdir");
	}

	public static String getSeparator() {
		return System.getProperty("file.separator");
	}

	public static void main(String[] args) {
		System.out.println(getSysPath());
		System.out.println(System.getProperty("java.io.tmpdir"));
		System.out.println(getSeparator());
		System.out.println(getClassPath());

	}
}
