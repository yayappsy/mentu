/**
 *
 */
package com.weimhc.modules.job.entity;

import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.Length;

import com.thinkgem.javamg.common.persistence.DataEntity;

/**
 * 收发信息Entity
 * @author cwl
 * @version 2018-02-24
 */
public class Communication extends DataEntity<Communication>{

	private static final long serialVersionUID = 1L;
	/**
	 * 发送id
	 *
	 */
	private String userId;
	private String userName;
	/**
	 * 接收id
	 *
	 */
	private String companyId;
	private String companyName;
	/**
	 * 类型（1,用户发送给企业  2，企业发送给用户）
	 *
	 */
	private String type;
	/**
	 * 内容
	 *
	 */
	private String content;
	/**
	 * 是否已读（0未读，1已读）
	 *
	 */
	private String isRead;

	public Communication() {
		super();
	}

	public Communication(String id){
		super(id);
	}

	/**
	 * 获取发送id
	 *
	 * @return 发送id
	 */
	@Length(min=0, max=64)
	@ApiModelProperty(value = "用户id")
	public String getUserId() {
		return userId;
	}
	/**
	 * 设置发送id
	 *
	 * @param userId
	 *            发送id
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}

	/**
	 * 获取接收id
	 *
	 * @return 接收id
	 */
	@Length(min=0, max=64)
	@ApiModelProperty(value = "企业id")
	public String getCompanyId() {
		return companyId;
	}
	/**
	 * 设置接收id
	 *
	 * @param companyId
	 *            接收id
	 */
	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}

	/**
	 * 获取类型（1,用户发送给企业  2，企业发送给用户）
	 *
	 * @return 类型（1,用户发送给企业  2，企业发送给用户）
	 */
	@Length(min=0, max=1)
	@ApiModelProperty(value = "类型（1,用户发送给企业  2，企业发送给用户）")
	public String getType() {
		return type;
	}
	/**
	 * 设置类型（1,用户发送给企业  2，企业发送给用户）
	 *
	 * @param type
	 *            类型（1,用户发送给企业  2，企业发送给用户）
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * 获取内容
	 *
	 * @return 内容
	 */
	@Length(min=0, max=255)
	@ApiModelProperty(value = "内容")
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
	 * 获取是否已读（0未读，1已读）
	 *
	 * @return 是否已读（0未读，1已读）
	 */
	@Length(min=0, max=1)
	@ApiModelProperty(value = "是否已读（0未读，1已读）")
	public String getIsRead() {
		return isRead;
	}
	/**
	 * 设置是否已读（0未读，1已读）
	 *
	 * @param isRead
	 *            是否已读（0未读，1已读）
	 */
	public void setIsRead(String isRead) {
		this.isRead = isRead;
	}

	@ApiModelProperty(value = "用户名称")
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}




	@ApiModelProperty(value = "企业名称")
	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
}