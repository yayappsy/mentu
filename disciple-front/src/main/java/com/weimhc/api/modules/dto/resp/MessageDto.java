/**
 * 
 */
package com.weimhc.api.modules.dto.resp;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.weimhc.framework.dto.AbstractIdDto;
import com.weimhc.framework.web.utils.MessageSourceUtils;
import com.weimhc.modules.message.entity.InternalMessage;
import com.weimhc.modules.message.entity.InternalMessageType;
import com.weimhc.modules.sys.utils.UserUtils;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.format.annotation.DateTimeFormat;
import springfox.documentation.annotations.ApiIgnore;

import java.util.Date;

/**
 * 预约DTO
 * 
 * @author lc
 * @version 2016-12-15
 */
public class MessageDto extends AbstractIdDto {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1095501047336970672L;

	/**
	 * 是否已查看
	 * 
	 */
	private Boolean isRead;

	private Date createDate;

	/**
	 * 消息
	 * 
	 */
	private InternalMessage message;

	/**
	 * @return the message
	 */
	@ApiIgnore
	@JsonIgnore
	public InternalMessage getMessage() {
		return message;
	}

	/**
	 * @param message
	 *            the message to set
	 */
	public void setMessage(InternalMessage message) {
		this.message = message;
	}

	/**
	 * 获取发送人
	 * 
	 * @return the senderId
	 */
	@ApiModelProperty(value = "发送人")
	public String getSenderId() {
		if (getMessage() != null) {
			return getMessage().getSenderId();
		}
		return "";
	}

	/**
	 * 获取发送人
	 * 
	 * @return the senderId
	 */
	@ApiModelProperty(value = "发送人昵称")
	public String getSenderNickname() {
		if (getMessage() != null) {
			return UserUtils.get(getMessage().getSenderId()).getNickname();
		}
		return "";
	}

	/**
	 * 获取标题
	 * 
	 * @return the title
	 */
	@ApiModelProperty(value = "标题")
	public String getTitle() {
		if (getMessage() != null) {
			return getMessage().getTitle();
		}
		return "";
	}

	/**
	 * @return the content
	 */
	@ApiModelProperty(value = "消息内容")
	public String getContent() {
		if (getMessage() != null) {
			return getMessage().getContent();
		}
		return "";
	}

	/**
	 * @return the isMass
	 */
	@ApiModelProperty(value = "是否群发")
	public Boolean getIsMass() {
		if (getMessage() != null) {
			return getMessage().getIsMass();
		}
		return false;
	}

	/**
	 * @return the isRead
	 */
	@ApiModelProperty(value = "是否已读")
	public Boolean getIsRead() {
		return isRead;
	}

	/**
	 * @param isRead
	 *            the isRead to set
	 */
	public void setIsRead(Boolean isRead) {
		this.isRead = isRead;
	}

	/**
	 * @return the messageType
	 */
	@ApiModelProperty(value = "消息类型")
	public InternalMessageType getMessageType() {
		if (getMessage() != null) {
			return getMessage().getMessageType();
		}
		return null;
	}

	@ApiModelProperty(value = "消息类型描述")
	public String getMessageTypeDestription() {
		if (getMessageType() != null) {
			return MessageSourceUtils
					.getMessage("InternalMessageType." + getMessageType());
		}
		return "";
	}

	/**
	 * @return the createDate
	 */
	@ApiModelProperty(value = "接收时间")
	@JsonFormat(pattern = "yyyy-MM-dd")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	public Date getCreateDate() {
		return createDate;
	}

	/**
	 * @param createDate
	 *            the createDate to set
	 */
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

}