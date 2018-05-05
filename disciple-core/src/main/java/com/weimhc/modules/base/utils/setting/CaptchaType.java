package com.weimhc.modules.base.utils.setting;

/**
 * 验证码类型
 */
public enum CaptchaType {

	/** 会员登录 */
	memberLogin,
	/** 消息提醒 */
	remind,
	/** 会员注册 */
	memberRegister,

	/** 后台登录 */
	adminLogin,

	/** 商品评论 */
	review,

	/** 咨询 */
	consultation,

	/** 找回密码 */
	findPassword,

	/** 重置密码 */
	resetPassword,

	/** 其它 */
	other
}