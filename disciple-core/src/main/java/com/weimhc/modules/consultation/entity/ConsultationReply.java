/**
 * 
 */
package com.weimhc.modules.consultation.entity;

import java.util.List;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.javamg.common.persistence.DataEntity;
import com.weimhc.modules.user.entity.UserInfo;

/**
 * 咨询回复Entity
 * 
 * @author zsm
 * @version 2017-01-05
 */
public class ConsultationReply extends DataEntity<ConsultationReply> {

	private static final long serialVersionUID = 1L;
	/**
	 * 咨询的问题
	 * 
	 */
	private Consultation consultation;
	/**
	 * 回复者
	 * 
	 */
	private UserInfo replier;
	/**
	 * 回复内容
	 * 
	 */
	private String content;

	/**
	 * 是否显示
	 */
	private Boolean isShow;

	/**
	 * 该咨询回复 关联的回复
	 */
	private ConsultationReply parent;

	/**
	 * 该咨询回复 关联的回复列表
	 */
	private List<ConsultationReply> replys;

	public ConsultationReply() {
		super();
	}

	public ConsultationReply(String id) {
		super(id);
	}

	/**
	 * 获取咨询的问题
	 * 
	 * @return 咨询的问题
	 */
	public Consultation getConsultation() {
		return consultation;
	}

	/**
	 * 设置咨询的问题
	 * 
	 * @param consultation
	 *            咨询的问题
	 */
	public void setConsultation(Consultation consultation) {
		this.consultation = consultation;
	}

	/**
	 * 获取回复者
	 * 
	 * @return 回复者
	 */
	public UserInfo getReplier() {
		return replier;
	}

	/**
	 * 设置回复者
	 * 
	 * @param replier
	 *            回复者
	 */
	public void setReplier(UserInfo replier) {
		this.replier = replier;
	}

	/**
	 * 获取回复内容
	 * 
	 * @return 回复内容
	 */
	@Length(min = 1, max = 255)
	public String getContent() {
		return content;
	}

	/**
	 * 设置回复内容
	 * 
	 * @param content
	 *            回复内容
	 */
	public void setContent(String content) {
		this.content = content;
	}

	/**
	 * 返回 该咨询回复 关联的回复
	 * 
	 * @return the parent
	 */
	public ConsultationReply getParent() {
		return parent;
	}

	/**
	 * 设置 该咨询回复 关联的回复
	 * 
	 * @param parent
	 *            the parent to set
	 */
	public void setParent(ConsultationReply parent) {
		this.parent = parent;
	}

	/**
	 * 返回 该医师建议 关联的回复列表
	 * 
	 * @return the replys
	 */
	public List<ConsultationReply> getReplys() {
		return replys;
	}

	/**
	 * 设置 该医师建议 关联的回复列表
	 * 
	 * @param replys
	 *            the replys to set
	 */
	public void setReplys(List<ConsultationReply> replys) {
		this.replys = replys;
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