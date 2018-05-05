package com.weimhc.framework.sms.hxt.dto.resp;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "statusbox")
public class StatusBox implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 9054811326925022077L;
	/**
	 * 对应的手机号码
	 */
	private String mobile;
	/**
	 * 同一批任务ID
	 */
	private String taskId;
	/**
	 * 状态报告----10：发送成功，20：发送失败
	 */
	private String status;
	/**
	 * 接收时间
	 */
	private String receiveTime;

	/**
	 * 返回 对应的手机号码
	 * 
	 * @return the mobile
	 */
	@XmlElement(name = "mobile")
	public String getMobile() {
		return mobile;
	}

	/**
	 * 设置 对应的手机号码
	 * 
	 * @param mobile
	 *            the mobile to set
	 */
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	/**
	 * 返回 同一批任务ID
	 * 
	 * @return the taskId
	 */
	@XmlElement(name = "taskid")
	public String getTaskId() {
		return taskId;
	}

	/**
	 * 设置 同一批任务ID
	 * 
	 * @param taskId
	 *            the taskId to set
	 */
	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}

	/**
	 * 返回 状态报告----10：发送成功，20：发送失败
	 * 
	 * @return the status
	 */
	@XmlElement(name = "status")
	public String getStatus() {
		return status;
	}

	/**
	 * 设置 状态报告----10：发送成功，20：发送失败
	 * 
	 * @param status
	 *            the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * 返回 接收时间
	 * 
	 * @return the receiveTime
	 */
	@XmlElement(name = "receivetime")
	public String getReceiveTime() {
		return receiveTime;
	}

	/**
	 * 设置 接收时间
	 * 
	 * @param receiveTime
	 *            the receiveTime to set
	 */
	public void setReceiveTime(String receiveTime) {
		this.receiveTime = receiveTime;
	}

}
