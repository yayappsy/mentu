/**
 * 
 */
package com.weimhc.framework.sms.dto;

public enum SmsType {
	/** 验证码 **/
	captcha,
	/** 订单提醒 **/
	order,
	/** 预约提醒 **/
	appointment,
	/** 充值提醒 **/
	recharge,
	/** 申请提现提醒 **/
	withdrawals,
	/** 提现成功提醒 **/
	withdrawalsSeccess,
	/** 订单违约提醒 **/
	orderBreach,
	/** 竞拍成功提醒 **/
	auction,
	/** 关注后即将开拍提醒 **/
	followRemind,
	/** 落后提醒 **/
	backwardRemind,
	/** 还款提醒 **/
	repayment;
}