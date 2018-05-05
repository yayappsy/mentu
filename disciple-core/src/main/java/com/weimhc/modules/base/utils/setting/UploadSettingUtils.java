package com.weimhc.modules.base.utils.setting;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class UploadSettingUtils {

	private static Logger logger = LoggerFactory
			.getLogger(UploadSettingUtils.class);

	/** 商品图片设置 */
	public static final String UPLAOD_SETTING = "upload";

	/** 上传设置 */
	public enum UploadSetting {
		/** 上传文件最大限制 */
		uploadMaxSize,

		/** 允许上传图片扩展名 */
		uploadImageExtension,

		/** 允许上传Flash扩展名 */
		uploadFlashExtension,

		/** 允许上传媒体扩展名 */
		uploadMediaExtension,

		/** 允许上传文件扩展名 */
		uploadFileExtension,

		/** 图片上传路径 */
		imageUploadPath,

		/** Flash上传路径 */
		flashUploadPath,

		/** 媒体上传路径 */
		mediaUploadPath,

		/** 文件上传路径 */
		fileUploadPath,
	}
	// ============== 图片设置 ==============

	/**
	 * 获取上传文件最大限制
	 * 
	 * @return 上传文件最大限制
	 */
	@NotNull
	@Min(0)
	public Integer getUploadMaxSize() {
		String uploadMaxSize = SettingUtils.getSettingValue(
				UploadSetting.uploadMaxSize.name(), UPLAOD_SETTING);
		return NumberUtils.toInt(uploadMaxSize);
	}

	/**
	 * 获取允许上传图片扩展名
	 * 
	 * @return 允许上传图片扩展名
	 */

	public String getUploadImageExtension() {
		String uploadImageExtension = SettingUtils.getSettingValue(
				UploadSetting.uploadImageExtension.name(), UPLAOD_SETTING);
		return uploadImageExtension;
	}

	/**
	 * 获取允许上传Flash扩展名
	 * 
	 * @return 允许上传Flash扩展名
	 */

	public String getUploadFlashExtension() {
		String uploadFlashExtension = SettingUtils.getSettingValue(
				UploadSetting.uploadFlashExtension.name(), UPLAOD_SETTING);
		return uploadFlashExtension;
	}

	/**
	 * 获取允许上传媒体扩展名
	 * 
	 * @return 允许上传媒体扩展名
	 */

	public String getUploadMediaExtension() {
		String uploadMediaExtension = SettingUtils.getSettingValue(
				UploadSetting.uploadMediaExtension.name(), UPLAOD_SETTING);
		return uploadMediaExtension;
	}

	/**
	 * 获取允许上传文件扩展名
	 * 
	 * @return 允许上传文件扩展名
	 */

	public String getUploadFileExtension() {
		String uploadFileExtension = SettingUtils.getSettingValue(
				UploadSetting.uploadFileExtension.name(), UPLAOD_SETTING);
		return uploadFileExtension;
	}

	/**
	 * 获取图片上传路径
	 * 
	 * @return 图片上传路径
	 */

	public String getImageUploadPath() {
		String imageUploadPath = SettingUtils.getSettingValue(
				UploadSetting.imageUploadPath.name(), UPLAOD_SETTING);
		return imageUploadPath;
	}

	/**
	 * 获取Flash上传路径
	 * 
	 * @return Flash上传路径
	 */

	public String getFlashUploadPath() {
		String flashUploadPath = SettingUtils.getSettingValue(
				UploadSetting.flashUploadPath.name(), UPLAOD_SETTING);
		return flashUploadPath;
	}

	/**
	 * 获取媒体上传路径
	 * 
	 * @return 媒体上传路径
	 */

	public String getMediaUploadPath() {
		String mediaUploadPath = SettingUtils.getSettingValue(
				UploadSetting.mediaUploadPath.name(), UPLAOD_SETTING);
		return mediaUploadPath;
	}

	/**
	 * 获取文件上传路径
	 * 
	 * @return 文件上传路径
	 */

	public String getFileUploadPath() {
		String fileUploadPath = SettingUtils.getSettingValue(
				UploadSetting.fileUploadPath.name(), UPLAOD_SETTING);
		return fileUploadPath;
	}

	/**
	 * 获取允许上传图片扩展名
	 * 
	 * @return 允许上传图片扩展名
	 */
	public String[] getUploadImageExtensions() {
		String uploadImageExtension = getUploadImageExtension();
		return StringUtils.split(uploadImageExtension, SettingUtils.SEPARATOR);
	}

	/**
	 * 获取允许上传Flash扩展名
	 * 
	 * @return 允许上传Flash扩展名
	 */
	public String[] getUploadFlashExtensions() {
		String uploadFlashExtension = getUploadFlashExtension();
		return StringUtils.split(uploadFlashExtension, SettingUtils.SEPARATOR);
	}

	/**
	 * 获取允许上传媒体扩展名
	 * 
	 * @return 允许上传媒体扩展名
	 */
	public String[] getUploadMediaExtensions() {
		String uploadMediaExtension = getMediaUploadPath();
		return StringUtils.split(uploadMediaExtension, SettingUtils.SEPARATOR);
	}

	/**
	 * 获取允许上传文件扩展名
	 * 
	 * @return 允许上传文件扩展名
	 */
	public String[] getUploadFileExtensions() {
		String uploadFileExtension = getUploadFileExtension();
		return StringUtils.split(uploadFileExtension, SettingUtils.SEPARATOR);
	}

}
