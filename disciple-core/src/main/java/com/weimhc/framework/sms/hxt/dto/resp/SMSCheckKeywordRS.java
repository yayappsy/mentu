package com.weimhc.framework.sms.hxt.dto.resp;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * 
 * 非法关键词查询 返回数据
 *
 * @author laozh
 * @version 2017年4月26日
 */
@XmlRootElement(name = "returnsms")
public class SMSCheckKeywordRS extends AbstractSMSRS {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1994675821456663352L;

	/**
	 * 检查次数
	 */
	private String checkCounts;

	/**
	 * 返回 检查次数
	 * 
	 * @return the checkCounts
	 */
	@XmlElement(name = "checkCounts")
	public String getCheckCounts() {
		return checkCounts;
	}

	/**
	 * 设置 检查次数
	 * 
	 * @param checkCounts
	 *            the checkCounts to set
	 */
	public void setCheckCounts(String checkCounts) {
		this.checkCounts = checkCounts;
	}

}
