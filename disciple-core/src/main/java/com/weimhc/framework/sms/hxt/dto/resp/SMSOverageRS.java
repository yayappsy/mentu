package com.weimhc.framework.sms.hxt.dto.resp;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * 
 * 余额及已发送量查询接口 返回数据
 *
 * @author laozh
 * @version 2017年4月26日
 */
@XmlRootElement(name = "returnsms")
public class SMSOverageRS {

	/**
	 * 支付方式 后付费，预付费
	 */
	private String payInfo;

	/**
	 * 已发送条数
	 */
	private String overage;

	/**
	 * 总点数 当支付方式为预付费是返回总充值点数
	 */
	private String sendTotal;

	/**
	 * 返回 支付方式
	 * 
	 * @return the payInfo
	 */
	@XmlElement(name = "payInfo")
	public String getPayInfo() {
		return payInfo;
	}

	/**
	 * 设置 支付方式
	 * 
	 * @param payInfo
	 *            the payInfo to set
	 */
	public void setPayInfo(String payInfo) {
		this.payInfo = payInfo;
	}

	/**
	 * 返回 已发送条数
	 * 
	 * @return the overage
	 */
	@XmlElement(name = "overage")
	public String getOverage() {
		return overage;
	}

	/**
	 * 设置 已发送条数
	 * 
	 * @param overage
	 *            the overage to set
	 */
	public void setOverage(String overage) {
		this.overage = overage;
	}

	/**
	 * 返回 总点数(总充值点数)
	 * 
	 * @return the sendTotal
	 */
	@XmlElement(name = "sendTotal")
	public String getSendTotal() {
		return sendTotal;
	}

	/**
	 * 设置 总点数(总充值点数)
	 * 
	 * @param sendTotal
	 *            the sendTotal to set
	 */
	public void setSendTotal(String sendTotal) {
		this.sendTotal = sendTotal;
	}

}
