package com.weimhc.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.github.binarywang.wxpay.config.WxPayConfig;
import com.github.binarywang.wxpay.service.WxPayService;
import com.github.laozhaishaozuo.wxpay.service.impl.CustomWxPayServiceImpl;
import com.thinkgem.javamg.common.config.Global;

/**
 * @author Binary Wang
 */
@Configuration
public class WxPayConfiguration {

	@Bean
	public WxPayConfig config() {
		WxPayConfig payConfig = new WxPayConfig();
		payConfig.setAppId(Global.getConfig("wxpay.pay.appId"));// 设置微信公众号的appid
		payConfig.setMchId(Global.getConfig("wxpay.pay.mchId"));// 微信支付商户号
		payConfig.setMchKey(Global.getConfig("wxpay.pay.mchKey"));// 微信支付商户密钥
		payConfig.setSubAppId(Global.getConfig("wxpay.pay.subAppId"));// 服务商模式下的子商户公众账号ID
		payConfig.setSubMchId(Global.getConfig("wxpay.pay.subMchId"));// 服务商模式下的子商户号
		payConfig.setKeyPath(Global.getConfig("weixin.pay.keyPath"));// apiclient_cert.p12的文件的绝对路径

		return payConfig;
	}

	@Bean
	public WxPayService wxPayService(WxPayConfig payConfig) {
		WxPayService wxPayService = new CustomWxPayServiceImpl();
		wxPayService.setConfig(payConfig);
		return wxPayService;
	}

}
