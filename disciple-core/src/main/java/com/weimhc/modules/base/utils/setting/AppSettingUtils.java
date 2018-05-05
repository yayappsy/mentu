package com.weimhc.modules.base.utils.setting;

import org.apache.commons.lang3.BooleanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/***
 * app 设置
 * 
 * @author laozh
 *
 */
public abstract class AppSettingUtils {

	private static Logger logger = LoggerFactory
			.getLogger(AppSettingUtils.class);

	/** app设置 */
	public static final String APP_SETTING = "app";

	/** app设置 code */
	public enum AppSetting {
		/** 是否显示灰色图标 */
		isDisplayUnusableIcon,
	}

	// ============== app相关设置 ==============

	/**
	 * 获取是否允许注册
	 * 
	 * @return 禁用是否允许注册
	 */
	public static Boolean getIsDisplayUnusableIcon() {
		String isDisplayUnusableIcon = SettingUtils.getSettingValue(
				AppSetting.isDisplayUnusableIcon.name(), APP_SETTING);
		return BooleanUtils.toBooleanObject(isDisplayUnusableIcon);
	}

}
