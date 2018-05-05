package com.weimhc.framework.sms.hxt.dto.resp;

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
public class SMSSendRS extends AbstractSMSRS {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1783888855129358092L;

	/**
	 * 返回余额
	 */
	private String remainPoint;

	/**
	 * 本次任务的序列ID
	 */
	private String taskID;

	/**
	 * 检查次数
	 */
	private String successCounts;

	/**
	 * 返回 返回余额
	 * 
	 * @return the remainPoint
	 */
	@XmlElement(name = "remainpoint")
	public String getRemainPoint() {
		return remainPoint;
	}

	/**
	 * 设置 返回余额
	 * 
	 * @param remainPoint
	 *            the remainPoint to set
	 */
	public void setRemainPoint(String remainPoint) {
		this.remainPoint = remainPoint;
	}

	/**
	 * 返回 本次任务的序列ID
	 * 
	 * @return the taskID
	 */
	@XmlElement(name = "taskID")
	public String getTaskID() {
		return taskID;
	}

	/**
	 * 设置 本次任务的序列ID
	 * 
	 * @param taskID
	 *            the taskID to set
	 */
	public void setTaskID(String taskID) {
		this.taskID = taskID;
	}

	/**
	 * 返回 成功短信数
	 * 
	 * @return the successCounts
	 */
	@XmlElement(name = "successCounts")
	public String getSuccessCounts() {
		return successCounts;
	}

	/**
	 * 设置 成功短信数
	 * 
	 * @param successCounts
	 *            the successCounts to set
	 */
	public void setSuccessCounts(String successCounts) {
		this.successCounts = successCounts;
	}

}
