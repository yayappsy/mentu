
package com.weimhc.framework.sms.dto;

import java.util.Map;

import com.weimhc.framework.dto.AbstractDto;

/**
 * 华信发送短信动态数据虚拟类
 * 
 * @author szuo
 * @version 2016年6月27日
 */
public abstract class AbstractSMSDto extends AbstractDto {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7164246559731511093L;

	/***
	 * 短信发送目标手机号
	 */
	private String destination;

	/**
	 * 发送内容
	 */
	private String content;

	/**
	 * 消息类型
	 */
	private SmsType smsType;

	private Map<String, String> parameter;

	public AbstractSMSDto() {
	}

	public AbstractSMSDto(String destination, String content) {
		this(destination, content, null);
	}

	public AbstractSMSDto(String destination, String content, Map<String, String> parameter) {
		this.destination = destination;
		this.content = content;
		this.parameter = parameter;
	}

	/**
	 *
	 * 返回 短信发送目标手机号
	 * 
	 * @return the destination
	 */
	public String getDestination() {
		return destination;
	}

	/**
	 * 设置 短信发送目标手机号
	 * 
	 * @param destination
	 *            the destination to set
	 */
	public void setDestination(String destination) {
		this.destination = destination;
	}

	/**
	 * 返回 发送内容
	 * 
	 * @return the content
	 */
	public String getContent() {
		return content;
	}

	/**
	 * 设置 发送内容
	 * 
	 * @param content
	 *            the content to set
	 */
	public void setContent(String content) {
		this.content = content;
	}

	/**
	 * @return the parameter
	 */
	public Map<String, String> getParameter() {
		return parameter;
	}

	/**
	 * @param parameter
	 *            the parameter to set
	 */
	public void setParameter(Map<String, String> parameter) {
		this.parameter = parameter;
	}

	/**
	 * @return the smsType
	 */
	public SmsType getSmsType() {
		return smsType;
	}

	/**
	 * @param smsType
	 *            the smsType to set
	 */
	public void setSmsType(SmsType smsType) {
		this.smsType = smsType;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((destination == null) ? 0 : destination.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AbstractSMSDto other = (AbstractSMSDto) obj;
		if (destination == null) {
			if (other.destination != null)
				return false;
		} else if (!destination.equals(other.destination))
			return false;
		return true;
	}

}
