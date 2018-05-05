/**
 * 
 */
package com.weimhc.framework.sms;

/**
 * 短信发送
 *
 * @author laozh
 * @version 2017年4月27日
 */
public interface SMSSendService {
	/**
	 * 短信发送接口<br/>
	 *
	 * 需实现功能：<br/>
	 * 从需要发送短信列表获取短信<br/>
	 * 将已经发送成功的短信进行移出短信发送列表<br/>
	 * 
	 * @return 是否发送成功
	 */
	boolean send();
}
