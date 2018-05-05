package com.weimhc.modules.base.utils.setting;

import org.apache.commons.lang3.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class ProductImageSettingUtils {

	private static Logger logger = LoggerFactory
			.getLogger(ProductImageSettingUtils.class);

	/** 商品图片设置 */
	public static final String PRODUCT_IMAGE_SETTING = "productImage";

	/** 商品图片设置 code */
	public enum ProductImageSetting {
		/** 商品图片(大)宽度 */
		largeProductImageWidth,

		/** 商品图片(大)高度 */
		largeProductImageHeight,

		/** 商品图片(中)宽度 */
		mediumProductImageWidth,

		/** 商品图片(中)高度 */
		mediumProductImageHeight,

		/** 商品缩略图宽度 */
		thumbnailProductImageWidth,

		/** 商品缩略图高度 */
		thumbnailProductImageHeight,

		/** 默认商品图片(大) */
		defaultLargeProductImage,

		/** 默认商品图片(小) */
		defaultMediumProductImage,

		/** 默认缩略图 */
		defaultThumbnailProductImage,

		/** 水印透明度 */
		watermarkAlpha,

		/** 水印图片 */
		watermarkImage,

		/** 水印位置 */
		watermarkPosition,
	}
	// ============== 图片设置 ==============

	/**
	 * 获取商品图片(大)宽度
	 * 
	 * @return 商品图片(大)宽度
	 */

	public static Integer getLargeProductImageWidth() {
		String largeProductImageHeight = SettingUtils.getSettingValue(
				ProductImageSetting.largeProductImageHeight.name(),
				PRODUCT_IMAGE_SETTING);
		return NumberUtils.toInt(largeProductImageHeight);
	}

	/**
	 * 获取商品图片(大)高度
	 * 
	 * @return 商品图片(大)高度
	 */

	public static Integer getLargeProductImageHeight() {
		String largeProductImageHeight = SettingUtils.getSettingValue(
				ProductImageSetting.largeProductImageHeight.name(),
				PRODUCT_IMAGE_SETTING);
		return NumberUtils.toInt(largeProductImageHeight);
	}

	/**
	 * 获取商品图片(中)宽度
	 * 
	 * @return 商品图片(中)宽度
	 */

	public static Integer getMediumProductImageWidth() {
		String mediumProductImageWidth = SettingUtils.getSettingValue(
				ProductImageSetting.mediumProductImageWidth.name(),
				PRODUCT_IMAGE_SETTING);
		return NumberUtils.toInt(mediumProductImageWidth);
	}

	/**
	 * 获取商品图片(中)高度
	 * 
	 * @return 商品图片(中)高度
	 */
	public static Integer getMediumProductImageHeight() {
		String mediumProductImageHeight = SettingUtils.getSettingValue(
				ProductImageSetting.mediumProductImageHeight.name(),
				PRODUCT_IMAGE_SETTING);
		return NumberUtils.toInt(mediumProductImageHeight);
	}

	/**
	 * 获取商品缩略图宽度
	 * 
	 * @return 商品缩略图宽度
	 */
	public static Integer getThumbnailProductImageWidth() {
		String thumbnailProductImageWidth = SettingUtils.getSettingValue(
				ProductImageSetting.thumbnailProductImageWidth.name(),
				PRODUCT_IMAGE_SETTING);
		return NumberUtils.toInt(thumbnailProductImageWidth);
	}

	/**
	 * 获取商品缩略图高度
	 * 
	 * @return 商品缩略图高度
	 */
	public static Integer getThumbnailProductImageHeight() {
		String thumbnailProductImageHeight = SettingUtils.getSettingValue(
				ProductImageSetting.thumbnailProductImageHeight.name(),
				PRODUCT_IMAGE_SETTING);
		return NumberUtils.toInt(thumbnailProductImageHeight);
	}

	/**
	 * 获取默认商品图片(大)
	 * 
	 * @return 默认商品图片(大)
	 */

	public static String getDefaultLargeProductImage() {
		String defaultLargeProductImage = SettingUtils.getSettingValue(
				ProductImageSetting.defaultLargeProductImage.name(),
				PRODUCT_IMAGE_SETTING);
		return defaultLargeProductImage;
	}

	/**
	 * 获取默认商品图片(小)
	 * 
	 * @return 默认商品图片(小)
	 */
	public static String getDefaultMediumProductImage() {
		String defaultMediumProductImage = SettingUtils.getSettingValue(
				ProductImageSetting.defaultMediumProductImage.name(),
				PRODUCT_IMAGE_SETTING);
		return defaultMediumProductImage;
	}

	/**
	 * 获取默认缩略图
	 * 
	 * @return 默认缩略图
	 */
	public static String getDefaultThumbnailProductImage() {
		String defaultThumbnailProductImage = SettingUtils.getSettingValue(
				ProductImageSetting.defaultThumbnailProductImage.name(),
				PRODUCT_IMAGE_SETTING);
		return defaultThumbnailProductImage;
	}

	/**
	 * 获取水印透明度
	 * 
	 * @return 水印透明度
	 */
	public static Integer getWatermarkAlpha() {
		String watermarkAlpha = SettingUtils.getSettingValue(
				ProductImageSetting.watermarkAlpha.name(),
				PRODUCT_IMAGE_SETTING);
		return NumberUtils.toInt(watermarkAlpha);
	}

	/**
	 * 获取水印图片
	 * 
	 * @return 水印图片
	 */
	public static String getWatermarkImage() {
		String watermarkImage = SettingUtils.getSettingValue(
				ProductImageSetting.watermarkImage.name(),
				PRODUCT_IMAGE_SETTING);
		return watermarkImage;
	}

	/**
	 * 获取水印位置
	 * 
	 * @return 水印位置
	 */
	public static WatermarkPosition getWatermarkPosition() {
		String watermarkPosition = SettingUtils.getSettingValue(
				ProductImageSetting.watermarkPosition.name(),
				PRODUCT_IMAGE_SETTING);
		return WatermarkPosition.valueOf(watermarkPosition);
	}

}
