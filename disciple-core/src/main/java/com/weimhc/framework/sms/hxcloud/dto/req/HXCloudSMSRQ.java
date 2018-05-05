
package com.weimhc.framework.sms.hxcloud.dto.req;

import java.io.Serializable;

/**
 * 华信通讯发送短信 请求包体
 * 
 * @author szuo
 * @version 2016年6月27日
 */
public class HXCloudSMSRQ implements Serializable {

	private static final long serialVersionUID = -8359071891753304145L;

	/***
	 * 已订购的产品ID
	 */
	private String productId;
	/***
	 * 短信发送目标及参数的列表，通过destination:指定发送目标手机号
	 */
	private String dynadatas;
	/***
	 * 模板ID
	 */
	private String templateId;
	/***
	 * 签名ID
	 */
	private String signingId;
	/***
	 * 回调地址
	 */
	private String callbackUrl;

	/**
	 *
	 * 返回 已订购的产品ID
	 * 
	 * @return the productId
	 */
	public String getProductId() {
		return productId;
	}

	/**
	 * 设置 已订购的产品ID
	 * 
	 * @param productId
	 *            the productId to set
	 */
	public void setProductId(String productId) {
		this.productId = productId;
	}

	/**
	 *
	 * 返回 短信发送目标及参数的列表
	 * 
	 * @return the dynadatas
	 */
	public String getDynadatas() {
		return dynadatas;
	}

	/**
	 * 设置 短信发送目标及参数的列表
	 * 
	 * @param dynadatas
	 *            the dynadatas to set
	 */
	public void setDynadatas(String dynadatas) {
		this.dynadatas = dynadatas;
	}

	/**
	 *
	 * 返回 模板ID
	 * 
	 * @return the templateId
	 */
	public String getTemplateId() {
		return templateId;
	}

	/**
	 * 设置 模板ID
	 * 
	 * @param templateId
	 *            the templateId to set
	 */
	public void setTemplateId(String templateId) {
		this.templateId = templateId;
	}

	/**
	 *
	 * 返回 签名ID
	 * 
	 * @return the signingId
	 */
	public String getSigningId() {
		return signingId;
	}

	/**
	 * 设置 签名ID
	 * 
	 * @param signingId
	 *            the signingId to set
	 */
	public void setSigningId(String signingId) {
		this.signingId = signingId;
	}

	/**
	 *
	 * 返回 回调地址
	 * 
	 * @return the callbackUrl
	 */
	public String getCallbackUrl() {
		return callbackUrl;
	}

	/**
	 * 设置 回调地址
	 * 
	 * @param callbackUrl
	 *            the callbackUrl to set
	 */
	public void setCallbackUrl(String callbackUrl) {
		this.callbackUrl = callbackUrl;
	}

}
