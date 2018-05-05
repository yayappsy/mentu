/**
 * 
 */
package com.weimhc.modules.job.entity;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.javamg.common.persistence.DataEntity;

/**
 * 个人评价Entity
 * @author cwl
 * @version 2018-01-07
 */
public class Access extends DataEntity<Access>{
	
	private static final long serialVersionUID = 1L;
	/**
	 * 简历
	 * 
	 */		
	private String resumeId;	
	/**
	 * 会员
	 * 
	 */		
	private String memberId;	
	/**
	 * 评价内容
	 * 
	 */		
	private String access;	
	
	public Access() {
		super();
	}

	public Access(String id){
		super(id);
	}

	/**
	 * 获取简历
	 * 
	 * @return 简历
	 */	
	@Length(min=1, max=64)
	public String getResumeId() {
		return resumeId;
	}
	/**
	 * 设置简历
	 * 
	 * @param resumeId
	 *            简历
	 */
	public void setResumeId(String resumeId) {
		this.resumeId = resumeId;
	}
	
	/**
	 * 获取会员
	 * 
	 * @return 会员
	 */	
	@Length(min=1, max=64)
	public String getMemberId() {
		return memberId;
	}
	/**
	 * 设置会员
	 * 
	 * @param memberId
	 *            会员
	 */
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	
	/**
	 * 获取评价内容
	 * 
	 * @return 评价内容
	 */	
	@Length(min=1, max=255)
	public String getAccess() {
		return access;
	}
	/**
	 * 设置评价内容
	 * 
	 * @param access
	 *            评价内容
	 */
	public void setAccess(String access) {
		this.access = access;
	}
	
}