/**
 * 
 */
package com.weimhc.framework.utils;

import java.util.Arrays;
import java.util.List;

import com.thinkgem.javamg.common.utils.StringUtils;

/**
 * @author shaozuo
 *
 */
public abstract class FileTypeUtils {

	private static final String[] IMAGE_TYPES = { ".png", ".jpg", ".jpeg", ".gif", ".bmp" };

	private static final List<String> IMAGE_TYPE_LIST = Arrays.asList(IMAGE_TYPES);

	public static boolean isImageFile(String filename) {
		if (StringUtils.isBlank(filename)) {
			return false;
		}
		if (IMAGE_TYPE_LIST.contains(getSuffixByFilename(filename))) {
			return true;
		}
		return false;
	}

	/**
	 * 根据给定的文件名,获取其后缀信息
	 * 
	 * @param filename
	 * @return
	 */
	public static String getSuffixByFilename(String filename) {
		return filename.substring(filename.lastIndexOf(".")).toLowerCase();
	}
}
