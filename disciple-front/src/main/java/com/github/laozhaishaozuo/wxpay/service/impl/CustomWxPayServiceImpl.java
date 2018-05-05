package com.github.laozhaishaozuo.wxpay.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.github.binarywang.wxpay.bean.request.WxPayUnifiedOrderRequest;
import com.github.binarywang.wxpay.bean.result.WxPayUnifiedOrderResult;
import com.github.binarywang.wxpay.exception.WxPayException;
import com.github.binarywang.wxpay.service.impl.WxPayServiceImpl;
import com.github.binarywang.wxpay.util.SignUtils;

public class CustomWxPayServiceImpl extends WxPayServiceImpl {

	/**
	 * 该接口调用“统一下单”接口，并拼装发起支付请求需要的参数
	 * 详见http://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1421141115&token=
	 * &lang=zh_CN
	 *
	 * <p>
	 * 公众号支付 JSAPI
	 * https://pay.weixin.qq.com/wiki/doc/api/jsapi.php?chapter=7_7&index=6
	 * </p>
	 * 
	 * <p>
	 * APP支付 APP
	 * https://pay.weixin.qq.com/wiki/doc/api/app/app.php?chapter=9_12&index=2
	 * </p>
	 * 
	 * @param request
	 *            请求对象，注意一些参数如appid、mchid等不用设置，方法内会自动从配置对象中获取到（前提是对应配置中已经设置）
	 */
	@Override
	public Map<String, String> getPayInfo(WxPayUnifiedOrderRequest request)
			throws WxPayException {
		WxPayUnifiedOrderResult unifiedOrderResult = this.unifiedOrder(request);
		String prepayId = unifiedOrderResult.getPrepayId();
		if (StringUtils.isBlank(prepayId)) {
			throw new RuntimeException(
					String.format("无法获取prepay id，错误代码： '%s'，信息：%s。",
							unifiedOrderResult.getErrCode(),
							unifiedOrderResult.getErrCodeDes()));
		}

		Map<String, String> payInfo = new HashMap<>();
		if (WxTradeType.JSAPI.name().equals(request.getTradeType())
				|| WxTradeType.NATIVE.name().equals(request.getTradeType())) {
			// https://pay.weixin.qq.com/wiki/doc/api/jsapi.php?chapter=7_7&index=6
			payInfo.put("appId", getConfig().getAppId());
			// 支付签名时间戳，注意微信jssdk中的所有使用timestamp字段均为小写。
			// 但最新版的支付后台生成签名使用的timeStamp字段名需大写其中的S字符
			payInfo.put("timeStamp",
					String.valueOf(System.currentTimeMillis() / 1000));
			payInfo.put("nonceStr", String.valueOf(System.currentTimeMillis()));
			payInfo.put("package", "prepay_id=" + prepayId);
			payInfo.put("signType", "MD5");
			if (WxTradeType.NATIVE.name().equals(request.getTradeType())) {
				payInfo.put("codeUrl", unifiedOrderResult.getCodeURL());
			}
			payInfo.put("paySign", SignUtils.createSign(payInfo,
					this.getConfig().getMchKey()));
		} else if (WxTradeType.APP.name().equals(request.getTradeType())) {
			// 参考
			// https://pay.weixin.qq.com/wiki/doc/api/app/app.php?chapter=9_12&index=2
			// APP支付绑定的是微信开放平台上的账号，APPID为开放平台上绑定APP后发放的参数
			String appId = getConfig().getAppId();
			Map<String, String> configMap = new HashMap<>();
			// 此map用于参与调起sdk支付的二次签名,格式全小写，timestamp只能是10位,格式固定，切勿修改
			String partnerid = getConfig().getMchId();
			configMap.put("prepayid", prepayId);
			configMap.put("partnerid", partnerid);
			configMap.put("package", "Sign=WXPay");
			configMap.put("timestamp",
					String.valueOf(System.currentTimeMillis() / 1000));
			configMap.put("noncestr",
					String.valueOf(System.currentTimeMillis()));
			configMap.put("appid", appId);
			// 此map用于客户端与微信服务器交互
			payInfo.put("sign", SignUtils.createSign(configMap,
					this.getConfig().getMchKey()));
			payInfo.put("prepayId", prepayId);
			payInfo.put("partnerId", partnerid);
			payInfo.put("appId", appId);
			payInfo.put("packageValue", "Sign=WXPay");
			payInfo.put("timeStamp",
					String.valueOf(System.currentTimeMillis() / 1000));
			payInfo.put("nonceStr", String.valueOf(System.currentTimeMillis()));

		}

		return payInfo;
	}
}
