package com.weimhc.framework.sms.hxt.dto.resp;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;

public class AbstractSMSRS implements Serializable {

	public static final String SUCCESS = "Success";
	/**
	 * 
	 */
	private static final long serialVersionUID = 9054811326925022077L;
	/**
	 * 返回状态值：成功返回Success 失败返回：Faild
	 */
	private String returnStatus;
	/**
	 * 返回信息
	 */
	private String message;

	/**
	 * 返回 返回状态值：成功返回Success 失败返回：Faild
	 * 
	 * @return the returnStatus
	 */
	@XmlElement(name = "returnstatus")
	public String getReturnStatus() {
		return returnStatus;
	}

	/**
	 * 设置 返回状态值：成功返回Success 失败返回：Faild
	 * 
	 * @param returnStatus
	 *            the returnStatus to set
	 */
	public void setReturnStatus(String returnStatus) {
		this.returnStatus = returnStatus;
	}

	/**
	 * 返回 返回信息
	 * 
	 * @return the message
	 */
	@XmlElement(name = "message")
	public String getMessage() {
		return message;
	}

	/**
	 * 设置 返回信息
	 * 
	 * @param message
	 *            the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}

}
