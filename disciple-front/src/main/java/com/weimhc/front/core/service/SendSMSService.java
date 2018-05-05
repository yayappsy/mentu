/**
 * 
 */
package com.weimhc.front.core.service;

import java.util.List;
import java.util.Map;

/**
 * 发送短信服务类
 * 
 * @version 2016-02-23
 */
public interface SendSMSService {
	/**
	 * 单个手机 发送短信
	 * 
	 * @param moblie
	 *            目标手机
	 * @param content
	 *            短信内容
	 */
	public void sendSMS(String mobile, String content);

	/**
	 * 单个手机发送模板短信
	 * 
	 * 模板中包含了短信目标
	 * 
	 * @param content
	 *            短信模板
	 */
	public void sendSMSTemplate(Map<String, String> content);

	/**
	 * 单个手机发送模板短信
	 * 
	 * 模板中包含了短信目标
	 * 
	 * @param content
	 *            短信模板
	 */
	public void sendSMSTemplate(List<Map<String, String>> content);
}
