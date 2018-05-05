
package com.weimhc.framework.sms.hxcloud;

import com.weimhc.framework.sms.dto.AbstractSMSDto;

/**
 * 华信发送短信动态数据虚拟类
 * 
 * @author szuo
 * @version 2016年6月27日
 */
public abstract class AbstractHXSMSTemplate extends AbstractSMSDto {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3749356517785088617L;

	/***
	 * 产品id
	 */
	protected String productId;

	/***
	 * 签名id
	 */
	protected String signingId;

	/***
	 * 模板ID
	 */
	protected String templateId;

	/***
	 * 短信发送目标手机号
	 */
	private String destination;

	/**
	 *
	 * 返回 短信发送目标手机号
	 * 
	 * @return the destination
	 */
	@Override
	public String getDestination() {
		return destination;
	}

	/**
	 * 设置 短信发送目标手机号
	 * 
	 * @param destination
	 *            the destination to set
	 */
	@Override
	public void setDestination(String destination) {
		this.destination = destination;
	}

	/**
	 * 返回该模板对应的短信产品id
	 * 
	 * @param productId
	 *            the productId to set
	 */
	public void setProductId(String productId) {
		this.productId = productId;
	}

	/**
	 * 返回该模板对应的短信产品id
	 * 
	 * @return
	 */
	public abstract String getProductId();

	/**
	 *
	 * 返回 签名id
	 * 
	 * @return the signingId
	 */
	public abstract String getSigningId();

	/**
	 * 设置 签名id
	 * 
	 * @param signingId
	 *            the signingId to set
	 */
	public void setSigningId(String signingId) {
		this.signingId = signingId;
	}

	/**
	 *
	 * 返回 模板ID
	 * 
	 * @return the templateId
	 */
	public abstract String getTemplateId();

	/**
	 * 设置 模板ID
	 * 
	 * @param templateId
	 *            the templateId to set
	 */
	public void setTemplateId(String templateId) {
		this.templateId = templateId;
	}
}
