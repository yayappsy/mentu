package com.weimhc.framework.sms.hxt.dto.resp;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * 
 * 发送接口 返回数据
 *
 * @author laozh
 * @version 2017年4月26日
 */
@XmlRootElement(name = "returnsms")
public class StatusApiRS extends AbstractSMSRS {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4830972716191720170L;
	/**
	 * 状态
	 */
	private List<StatusBox> statusBox;

	/**
	 * 错误信息
	 */
	private List<ErrorStatus> errorStatus;

	/**
	 * 返回 状态
	 * 
	 * @return the statusBox
	 */
	@XmlElement(name = "statusbox")
	public List<StatusBox> getStatusBox() {
		return statusBox;
	}

	/**
	 * 设置 状态
	 * 
	 * @param statusBox
	 *            the statusBox to set
	 */
	public void setStatusBox(List<StatusBox> statusBox) {
		this.statusBox = statusBox;
	}

	/**
	 * 返回 错误信息
	 * 
	 * @return the errorStatus
	 */
	@XmlElement(name = "errorstatus")
	public List<ErrorStatus> getErrorStatus() {
		return errorStatus;
	}

	/**
	 * 设置 错误信息
	 * 
	 * @param errorStatus
	 *            the errorStatus to set
	 */
	public void setErrorStatus(List<ErrorStatus> errorStatus) {
		this.errorStatus = errorStatus;
	}

}
