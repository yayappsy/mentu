/**
 * 
 */
package com.weimhc.modules.remind.entity;

/**
 * 业务代码，用来获取提醒模板中的业务数据
 *
 * @author laozh
 * @version 2017年4月17日
 */
public enum BusinessAction {

	/**
	 * 会员注册
	 */
	memberRegister,

	/** 找回密码 */
	findPassword,

	/** 重置密码 */
	resetPassword,

	/**
	 * 咨询
	 */
	consultation,
	/**
	 * 咨询回复
	 */
	consultationReply,
	/**
	 * 预约课程
	 */
	makeAnAppointment,

	/**
	 * 询价提醒
	 */
	inquiry;

	private BusinessAction() {

	}
}
