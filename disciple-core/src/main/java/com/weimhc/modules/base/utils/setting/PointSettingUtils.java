package com.weimhc.modules.base.utils.setting;

import org.apache.commons.lang3.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.thinkgem.javamg.common.config.Global;
import com.thinkgem.javamg.common.utils.StringUtils;

public abstract class PointSettingUtils {

	private static Logger logger = LoggerFactory
			.getLogger(PointSettingUtils.class);

	/** 积分设置 */
	public static final String POINT_SETTING = "point";

	/***
	 * 是否启用积分
	 */
	public static final String IS_ENABLE_POINT = Global
			.getConfig("is.enable.point");

	/** 积分设置 code */
	public enum PointSetting {
		/** 注册初始积分 */
		registerPoint,
		/** 参加活动积分 */
		joinPoint,
		/** 发布活动积分 */
		publishPoint,
		/** 关注大佬积分 */
		followPoint,
	}

	public static Boolean getIsEnablePoint() {
		return StringUtils.equals(Global.TRUE, IS_ENABLE_POINT);
	}

	// ============== 积分设置 ==============
	/**
	 * 获取注册初始积分
	 * 
	 * @return 注册初始积分
	 */
	public static Long getRegisterPoint() {
		String registerPoint = SettingUtils.getSettingValue(
				PointSetting.registerPoint.name(), POINT_SETTING);
		return NumberUtils.toLong(registerPoint);
	}

	/**
	 * 获取 参加活动积分
	 * 
	 * @return 参加活动积分
	 */
	public static Long getJoinPoint() {
		String joinPoint = SettingUtils
				.getSettingValue(PointSetting.joinPoint.name(), POINT_SETTING);
		return NumberUtils.toLong(joinPoint);
	}

	/**
	 * 获取 发布活动积分
	 * 
	 * @return 发布活动积分
	 */
	public static Long getPublishPoint() {
		String publishPoint = SettingUtils.getSettingValue(
				PointSetting.publishPoint.name(), POINT_SETTING);
		return NumberUtils.toLong(publishPoint);
	}

	/**
	 * 获取 关注大佬积分
	 * 
	 * @return 获取 关注大佬积分
	 */
	public static Long getFollowPoint() {
		String followPoint = SettingUtils.getSettingValue(
				PointSetting.followPoint.name(), POINT_SETTING);
		return NumberUtils.toLong(followPoint);
	}
}
