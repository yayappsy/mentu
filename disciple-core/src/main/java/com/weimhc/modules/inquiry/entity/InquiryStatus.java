package com.weimhc.modules.inquiry.entity;

/**
 * 
 * 询价单状态
 *
 * @author laozh
 * @version 2017年4月10日
 */
public enum InquiryStatus {
	/**
	 * 等待审核
	 */
	waiting,
	/**
	 * 有效
	 */
	valid,
	/**
	 * 无效
	 */
	invalid;
}