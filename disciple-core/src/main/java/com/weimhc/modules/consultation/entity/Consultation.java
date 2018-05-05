/**
 * 
 */
package com.weimhc.modules.consultation.entity;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.javamg.common.persistence.DataEntity;
import com.weimhc.modules.user.entity.UserInfo;

/**
 * 咨询Entity
 * 
 * @author zsm
 * @version 2017-01-05
 */
public class Consultation extends DataEntity<Consultation> {

	private static final long serialVersionUID = 1L;
	/**
	 * 咨询者
	 * 
	 */
	private UserInfo consultant;
	/**
	 * 咨询类型
	 * 
	 */
	private ConsultationType consultationType;
	/**
	 * 咨询内容
	 * 
	 */
	private String content;

	/**
	 * 是否显示
	 */
	private Boolean isShow;

	/**
	 * 回复状态
	 */
	private ReplyStatus replyStatus = ReplyStatus.waiting;

	public Consultation() {
		super();
	}

	public Consultation(String id) {
		super(id);
	}

	/**
	 * 获取咨询者
	 * 
	 * @return 咨询者
	 */
	public UserInfo getConsultant() {
		return consultant;
	}

	/**
	 * 设置咨询者
	 * 
	 * @param consultant
	 *            咨询者
	 */
	public void setConsultant(UserInfo consultant) {
		this.consultant = consultant;
	}

	/**
	 * 获取咨询类型
	 * 
	 * @return 咨询类型
	 */
	public ConsultationType getConsultationType() {
		return consultationType;
	}

	/**
	 * 设置咨询类型
	 * 
	 * @param consultationType
	 *            咨询类型
	 */
	public void setConsultationType(ConsultationType consultationType) {
		this.consultationType = consultationType;
	}

	/**
	 * 获取咨询内容
	 * 
	 * @return 咨询内容
	 */
	@Length(min = 1, max = 255)
	public String getContent() {
		return content;
	}

	/**
	 * 设置咨询内容
	 * 
	 * @param content
	 *            咨询内容
	 */
	public void setContent(String content) {
		this.content = content;
	}

	/**
	 * 返回 回复状态
	 * 
	 * @return the replyStatus
	 */
	public ReplyStatus getReplyStatus() {
		return replyStatus;
	}

	/**
	 * 设置 回复状态
	 * 
	 * @param replyStatus
	 *            the replyStatus to set
	 */
	public void setReplyStatus(ReplyStatus replyStatus) {
		this.replyStatus = replyStatus;
	}

	/**
	 * 返回 是否显示
	 * 
	 * @return the isShow
	 */
	public Boolean getIsShow() {
		return isShow;
	}

	/**
	 * 设置 是否显示
	 * 
	 * @param isShow
	 *            the isShow to set
	 */
	public void setIsShow(Boolean isShow) {
		this.isShow = isShow;
	}

}