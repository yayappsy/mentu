package com.weimhc.framework.sms.hxt.dto.resp;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "errorstatus")
public class ErrorStatus implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 9054811326925022077L;
	/**
	 * 错误码
	 */
	private String error;
	/**
	 * 错误描述
	 */
	private String remark;

	/**
	 * 返回 错误码
	 * 
	 * @return the error
	 */
	@XmlElement(name = "error")
	public String getError() {
		return error;
	}

	/**
	 * 设置 错误码
	 * 
	 * @param error
	 *            the error to set
	 */
	public void setError(String error) {
		this.error = error;
	}

	/**
	 * 返回 错误描述
	 * 
	 * @return the remark
	 */
	@XmlElement(name = "remark")
	public String getRemark() {
		return remark;
	}

	/**
	 * 设置 错误描述
	 * 
	 * @param remark
	 *            the remark to set
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}

}
