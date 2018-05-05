/**
 * 
 */
package com.weimhc.modules.payment.entity;

/**
 * @author laozh
 *
 */
public enum PaymentFrom {

	/**
	 * 从pc网站发起的支付
	 */
	PC,
	/**
	 * 从app调用发起的支付
	 */
	APP,
	/**
	 * 从微信浏览器发起的支付
	 */
	WeixinBrowser,
	/**
	 * 从手机浏览器调用app支付
	 */
	MobileBrowser;
}
