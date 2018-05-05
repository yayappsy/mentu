
package com.weimhc.framework.sms.hxcloud.dto.resp;

import java.io.Serializable;

/**
 * 华信通讯发送短信 请求包体
 * 
 * @author szuo
 * @version 2016年6月27日
 */
public class HXCloudSMSRS implements Serializable {

	private static final long serialVersionUID = -8359071891753304145L;

	/***
	 * 发送唯一编码
	 */
	private String transactId;
	/***
	 * 发送状态 0：正常
	 */
	private String errorCode;
	/***
	 * 消息列表
	 */
	private String msg;

	/**
	 *
	 * 返回 发送唯一编码
	 * 
	 * @return the transactId
	 */
	public String getTransactId() {
		return transactId;
	}

	/**
	 * 设置 发送唯一编码
	 * 
	 * @param transactId
	 *            the transactId to set
	 */
	public void setTransactId(String transactId) {
		this.transactId = transactId;
	}

	/**
	 *
	 * 返回 发送状态
	 * 
	 * @return the errorCode
	 */
	public String getErrorCode() {
		return errorCode;
	}

	/**
	 * 设置 发送状态
	 * 
	 * @param errorCode
	 *            the errorCode to set
	 */
	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	/**
	 *
	 * 返回 消息列表
	 * 
	 * @return the msg
	 */
	public String getMsg() {
		return msg;
	}

	/**
	 * 设置 消息列表
	 * 
	 * @param msg
	 *            the msg to set
	 */
	public void setMsg(String msg) {
		this.msg = msg;
	}

	@Override
	public String toString() {
		return "errorCode: " + errorCode + "transactId: " + transactId + "msg: "
				+ msg;
	}
}
