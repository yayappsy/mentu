/**
 * 
 */
package com.weimhc.modules.message.entity;

import java.util.List;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.javamg.common.persistence.DataEntity;

/**
 * 站内信Entity
 * 
 * @author zsm
 * @version 2017-03-23
 */
public class InternalMessage extends DataEntity<InternalMessage> {

	private static final long serialVersionUID = 1L;
	/**
	 * 发送人
	 * 
	 */
	private String senderId;
	/**
	 * 消息类型
	 */
	private InternalMessageType internalMessageType;
	/**
	 * 标题
	 */
	private String title;

	/**
	 * 内容
	 */
	private String content;
	/**
	 * 是否群发
	 * 
	 */
	private Boolean isMass;

	private List<String> recipientIds;

	public InternalMessage() {
		super();
	}

	public InternalMessage(String id) {
		super(id);
	}

	/**
	 * 设置默认值
	 */
	@Override
	public void setDefaultValue() {
		if (this.getIsMass() == null) {
			this.setIsMass(false);
		}
	}

	/**
	 * 获取发送人
	 * 
	 * @return 发送人
	 */
	@Length(min = 1, max = 64)
	public String getSenderId() {
		return senderId;
	}

	/**
	 * 设置发送人
	 * 
	 * @param senderId
	 *            发送人
	 */
	public void setSenderId(String senderId) {
		this.senderId = senderId;
	}

	/**
	 * 获取标题
	 * 
	 * @return 标题
	 */
	@Length(min = 1, max = 255)
	public String getTitle() {
		return title;
	}

	/**
	 * 设置标题
	 * 
	 * @param title
	 *            标题
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * 获取内容
	 * 
	 * @return 内容
	 */
	@Length(min = 0, max = 255)
	public String getContent() {
		return content;
	}

	/**
	 * 设置内容
	 * 
	 * @param content
	 *            内容
	 */
	public void setContent(String content) {
		this.content = content;
	}

	/**
	 * 获取是否群发
	 * 
	 * @return 是否群发
	 */
	public Boolean getIsMass() {
		return isMass;
	}

	/**
	 * 设置是否群发
	 * 
	 * @param isMass
	 *            是否群发
	 */
	public void setIsMass(Boolean isMass) {
		this.isMass = isMass;
	}

	/**
	 * 返回 接收人列表
	 * 
	 * @return the recipientIds
	 */
	public List<String> getRecipientIds() {
		return recipientIds;
	}

	/**
	 * 设置 接收人列表
	 * 
	 * @param recipientIds
	 *            the recipientIds to set
	 */
	public void setRecipientIds(List<String> recipientIds) {
		this.recipientIds = recipientIds;
	}

	/**
	 * 获取消息类型
	 * 
	 * @return the internalMessageType
	 */
	public InternalMessageType getMessageType() {
		return internalMessageType;
	}

	/**
	 * 设置消息类型
	 * 
	 * @param internalMessageType
	 *            the internalMessageType to set
	 */
	public void setMessageType(InternalMessageType internalMessageType) {
		this.internalMessageType = internalMessageType;
	}
}