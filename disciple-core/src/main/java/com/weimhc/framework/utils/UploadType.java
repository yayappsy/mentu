package com.weimhc.framework.utils;

import java.util.Arrays;
import java.util.List;

/**
 * 可接受的上传类型
 * 
 * @author szuo
 *
 */
public enum UploadType {

	/** 图片 */
	image("gif,jpg,jpeg,png,bmp"),
	/** 文件 */
	file("doc,docx,xls,xlsx,ppt,htm,html,txt,zip,rar,gz,bz2"),

	/** 多媒体文件 */
	media("swf,flv,mp3,wav,wma,wmv,mid,avi,mpg,asf,rm,rmvb");

	/***
	 * 可接受的类型
	 */
	private List<String> allowedExtensions;

	private UploadType(String allowedExtensions) {
		this.allowedExtensions = Arrays
				.<String> asList(allowedExtensions.split(","));
	}

	/**
	 * 是否允许上传
	 * 
	 * @param extension
	 * @return
	 */
	public boolean getIfAllowed(String extension) {
		return this.allowedExtensions.contains(extension);
	}
}
