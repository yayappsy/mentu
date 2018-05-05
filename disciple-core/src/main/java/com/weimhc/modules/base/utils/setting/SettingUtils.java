package com.weimhc.modules.base.utils.setting;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.thinkgem.javamg.common.utils.CacheUtils;
import com.thinkgem.javamg.common.utils.SpringContextHolder;
import com.weimhc.modules.base.dao.SettingDao;
import com.weimhc.modules.base.entity.Setting;

public abstract class SettingUtils {

	private static Logger logger = LoggerFactory.getLogger(SettingUtils.class);

	private static SettingDao settingDao = SpringContextHolder
			.getBean(SettingDao.class);

	/** 分隔符 */
	public static final String SEPARATOR = ",";

	public static final String CACHE_SETTING_MAP = "settingMap";

	/***
	 * 通过名称和类型获取标签
	 * 
	 * @param name
	 * @param type
	 * @return
	 */
	public static String getSettingLabel(String name, String type) {
		logger.debug("getSettingLabel: name = " + name + " ,  type=" + type);
		if (StringUtils.isNotBlank(type) && StringUtils.isNotBlank(name)) {
			for (Setting setting : getSettingList(type)) {
				if (type.equals(setting.getType())
						&& name.equals(setting.getName())) {
					return setting.getLabel();
				}
			}
		}
		return "";
	}

	/***
	 * 通过名称列表和类型，获取标签组
	 * 
	 * @param names
	 * @param type
	 * @return
	 */
	public static String getSettingLabels(String names, String type) {
		logger.debug("getSettingLabels: names = " + names + " ,  type=" + type);
		if (StringUtils.isNotBlank(type) && StringUtils.isNotBlank(names)) {
			List<String> valueList = Lists.newArrayList();
			for (String value : StringUtils.split(names, ",")) {
				valueList.add(getSettingLabel(value, type));
			}
			return StringUtils.join(valueList, ",");
		}
		return "";
	}

	/**
	 * 通过名称和类型，获取值
	 * 
	 * @param name
	 * @param type
	 * @return
	 */
	public static String getSettingValue(String name, String type) {
		logger.debug("getSettingValue: name = " + name + " ,  type=" + type);
		if (StringUtils.isNotBlank(type) && StringUtils.isNotBlank(name)) {
			for (Setting setting : getSettingList(type)) {
				if (type.equals(setting.getType())
						&& name.equals(setting.getName())) {
					return setting.getValue();
				}
			}
		}
		return "";
	}

	/**
	 * 通过类型，获取所有参数设置
	 * 
	 * @param type
	 * @return
	 */
	public static List<Setting> getSettingList(String type) {
		logger.debug("getSettingList: type=" + type);
		@SuppressWarnings("unchecked")
		Map<String, List<Setting>> settingMap = (Map<String, List<Setting>>) CacheUtils
				.get(CACHE_SETTING_MAP);
		if (settingMap == null) {
			settingMap = Maps.newHashMap();
			for (Setting setting : settingDao.findAllList(new Setting())) {
				List<Setting> settingList = settingMap.get(setting.getType());
				if (settingList != null) {
					settingList.add(setting);
				} else {
					settingMap.put(setting.getType(),
							Lists.newArrayList(setting));
				}
			}
			CacheUtils.put(CACHE_SETTING_MAP, settingMap);
		}
		List<Setting> settingList = settingMap.get(type);
		if (settingList == null) {
			settingList = Lists.newArrayList();
		}
		return settingList;
	}

	/**
	 * 获取验证码类型
	 * 
	 * @return 验证码类型
	 */
	public static CaptchaType[] getCaptchaTypes() {
		return CaptchaType.values();
	}

	/**
	 * 获取账号锁定类型
	 * 
	 * @return 账号锁定类型
	 */
	public static AccountLockType[] getAccountLockTypes() {
		return AccountLockType.values();
	}
}
