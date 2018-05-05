package com.weimhc.framework.sms.hxt.dto.resp;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "callbox")
public class CallBox implements Serializable {

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
	 * 上行内容
	 */
	private String content;
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
	 * 返回 上行内容
	 * 
	 * @return the content
	 */
	@XmlElement(name = "content")
	public String getContent() {
		return content;
	}

	/**
	 * 设置 上行内容
	 * 
	 * @param content
	 *            the content to set
	 */
	public void setContent(String content) {
		this.content = content;
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
