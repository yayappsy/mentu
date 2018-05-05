/**
 * 
 */
package com.github.laozhaishaozuo.wxpay.service.impl;

/**
 * @author laozh
 *
 */
public enum WxTradeType {
	/**
	 * 公众号支付(适用于PC网站支付，公众号内部支付)
	 */
	JSAPI,
	/**
	 * 原生扫码支付
	 */
	NATIVE,
	/**
	 * APP支付(商户在app中集成支付使用)
	 */
	APP,
	/**
	 * H5支付(商户在微信客户端外的移动端网页展示商品或服务)
	 */
	MWEB;
}
