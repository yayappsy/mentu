package com.weimhc.modules.base.utils.setting;

import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.weimhc.modules.base.entity.ShowType;

public abstract class MemberSettingUtils {

	private static Logger logger = LoggerFactory.getLogger(MemberSettingUtils.class);

	/** 会员设置 */
	public static final String MEMBER_SETTING = "member";

	/** 会员设置 code */
	public enum MemberSetting {
		/** 是否开放注册 */
		isRegisterEnabled(ShowType.image),
		/** 是否允许E-mail重复注册 */
		isDuplicateEmail(ShowType.image),
		/** 禁用用户名 */
		disabledUsername(ShowType.text),
		/** 用户名最小长度 */
		usernameMinLength(ShowType.text),
		/** 用户名最大长度 */
		usernameMaxLength(ShowType.text),
		/** 密码最小长度 */
		passwordMinLength(ShowType.text),
		/** 密码最大长度 */
		passwordMaxLength(ShowType.text),
		/** 连续登录失败最大次数 */
		accountLockCount(ShowType.text),
		/** 自动解锁时间 */
		accountLockTime(ShowType.text),
		/**
		 * 默认头像
		 */
		defaultAvatar(ShowType.image);

		private ShowType showType;

		private MemberSetting(ShowType showType) {
			this.showType = showType;
		}

		public ShowType getShowType() {
			return showType;
		}

		public void getShowType(ShowType showType) {
			this.showType = showType;
		}
	}

	// ============== 会员相关设置 ==============
	/**
	 * 获取禁用用户名
	 * 
	 * @return 禁用用户名
	 */
	public static String[] getDisabledUsernames() {
		String disabledUsernames = SettingUtils
				.getSettingValue(MemberSetting.disabledUsername.name(), MEMBER_SETTING);
		return StringUtils.split(disabledUsernames, SettingUtils.SEPARATOR);
	}

	/**
	 * 获取是否允许注册
	 * 
	 * @return 禁用是否允许注册
	 */
	public static Boolean getIsRegisterEnabled() {
		String isRegisterEnabled = SettingUtils
				.getSettingValue(MemberSetting.isRegisterEnabled.name(), MEMBER_SETTING);
		return BooleanUtils.toBooleanObject(isRegisterEnabled);
	}

	/**
	 * 获取是否允许E-mail重复注册
	 * 
	 * @return 是否允许E-mail重复注册
	 */
	public static Boolean getIsDuplicateEmail() {
		String isDuplicateEmail = SettingUtils
				.getSettingValue(MemberSetting.isDuplicateEmail.name(), MEMBER_SETTING);
		return BooleanUtils.toBooleanObject(isDuplicateEmail);
	}

	/**
	 * 获取用户名最小长度
	 * 
	 * @return 用户名最小长度
	 */

	public static Integer getUsernameMinLength() {
		String usernameMinLength = SettingUtils
				.getSettingValue(MemberSetting.usernameMinLength.name(), MEMBER_SETTING);
		return NumberUtils.toInt(usernameMinLength);
	}

	/**
	 * 获取用户名最大长度
	 * 
	 * @return 用户名最大长度
	 */
	public static Integer getUsernameMaxLength() {
		String usernameMaxLength = SettingUtils
				.getSettingValue(MemberSetting.usernameMaxLength.name(), MEMBER_SETTING);
		return NumberUtils.toInt(usernameMaxLength);
	}

	/**
	 * 获取密码最小长度
	 * 
	 * @return 获取最小长度
	 */
	public static Integer getPasswordMinLength() {

		String passwordMinLength = SettingUtils
				.getSettingValue(MemberSetting.passwordMinLength.name(), MEMBER_SETTING);
		return NumberUtils.toInt(passwordMinLength);
	}

	/**
	 * 获取密码最大长度
	 * 
	 * @return 密码最大长度
	 */
	public static Integer getPasswordMaxLength() {
		String passwordMaxLength = SettingUtils
				.getSettingValue(MemberSetting.passwordMaxLength.name(), MEMBER_SETTING);
		return NumberUtils.toInt(passwordMaxLength);
	}

	/**
	 * 获取连续登录失败最大次数
	 * 
	 * @return 连续登录失败最大次数
	 */
	public static Integer getAccountLockCount() {
		String accountLockCount = SettingUtils
				.getSettingValue(MemberSetting.accountLockCount.name(), MEMBER_SETTING);
		return NumberUtils.toInt(accountLockCount);
	}

	/**
	 * 获取自动解锁时间
	 * 
	 * @return 自动解锁时间
	 */
	public static Integer getAccountLockTime() {
		String accountLockTime = SettingUtils.getSettingValue(MemberSetting.accountLockTime.name(),
				MEMBER_SETTING);
		return NumberUtils.toInt(accountLockTime);
	}

	/**
	 * 获取 会员默认头像
	 * 
	 * @return 会员默认头像
	 */
	public static String getDefaultAvatar() {
		String defaultAvatar = SettingUtils.getSettingValue(MemberSetting.defaultAvatar.name(),
				MEMBER_SETTING);
		return defaultAvatar;
	}
}
