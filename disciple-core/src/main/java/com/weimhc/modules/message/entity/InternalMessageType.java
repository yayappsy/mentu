/**
 * 
 */
package com.weimhc.modules.message.entity;

public enum InternalMessageType {
	/**
	 * 会员注册
	 */
	memberRegister,

	/** 找回密码 */
	findPassword,

	/** 重置密码 */
	resetPassword,
	/**
	 * 付款提醒
	 */
	paymentReminder,
	/**
	 * 导师评价
	 */
	evaluation,
	/**
	 * 消费提醒
	 */
	ConsumerReminder,
	/**
	 * 课程预约
	 */
	courseAppointment;

}