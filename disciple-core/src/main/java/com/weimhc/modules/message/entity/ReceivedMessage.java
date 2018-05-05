/**
 * 
 */
package com.weimhc.modules.message.entity;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.javamg.common.persistence.DataEntity;

/**
 * 接收到的消息Entity
 * 
 * @author zsm
 * @version 2017-03-23
 */
public class ReceivedMessage extends DataEntity<ReceivedMessage> {

	private static final long serialVersionUID = 1L;
	/**
	 * 消息
	 * 
	 */
	private InternalMessage message;
	/**
	 * 接收人
	 * 
	 */
	private String recipientId;
	/**
	 * 是否已查看
	 * 
	 */
	private Boolean isRead;
	/**
	 * 是否已收藏
	 * 
	 */
	private Boolean isFavorite;

	public ReceivedMessage() {
		super();
	}

	public ReceivedMessage(String id) {
		super(id);
	}

	@Override
	public void preInsert() {
		super.preInsert();
		setDefaultValue();
	}
	@Override
	public void preUpdate() {
		super.preUpdate();
		setDefaultValue();
	}
	/**
	 * 设置默认值
	 */
	@Override
	public void setDefaultValue() {
		if (this.getIsRead() == null) {
			this.setIsRead(false);
		}
		if (this.getIsFavorite() == null) {
			this.setIsFavorite(false);
		}
	}

	/**
	 * 获取消息
	 * 
	 * @return 消息
	 */
	@NotNull
	public InternalMessage getMessage() {
		return message;
	}

	/**
	 * 设置消息
	 * 
	 * @param messageId
	 *            消息
	 */
	public void setMessage(InternalMessage message) {
		this.message = message;
	}

	/**
	 * 获取接收人
	 * 
	 * @return 接收人
	 */
	@Length(min = 1, max = 64)
	public String getRecipientId() {
		return recipientId;
	}

	/**
	 * 设置接收人
	 * 
	 * @param recipientId
	 *            接收人
	 */
	public void setRecipientId(String recipientId) {
		this.recipientId = recipientId;
	}

	/**
	 * 获取是否已查看
	 * 
	 * @return 是否已查看
	 */
	public Boolean getIsRead() {
		return isRead;
	}

	/**
	 * 设置是否已查看
	 * 
	 * @param isRead
	 *            是否已查看
	 */
	public void setIsRead(Boolean isRead) {
		this.isRead = isRead;
	}

	/**
	 * 获取是否已收藏
	 * 
	 * @return 是否已收藏
	 */
	public Boolean getIsFavorite() {
		return isFavorite;
	}

	/**
	 * 设置是否已收藏
	 * 
	 * @param isFavorite
	 *            是否已收藏
	 */
	public void setIsFavorite(Boolean isFavorite) {
		this.isFavorite = isFavorite;
	}

}