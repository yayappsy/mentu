/**
 * 
 */
package com.weimhc.modules.job.entity;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.javamg.common.persistence.DataEntity;

/**
 * 简历附件Entity
 * @author cwl
 * @version 2018-01-07
 */
public class RelatedOption extends DataEntity<RelatedOption>{
	
	private static final long serialVersionUID = 1L;
	/**
	 * 简历
	 * 
	 */		
	private String resumeId;
	/**
	 * 附件名称
	 *
	 */
	private String name;
	/**
	 * 会员
	 * 
	 */		
	private String memberId;	
	/**
	 * 附件
	 * 
	 */		
	private String options;
	private String type;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public RelatedOption() {
		super();
	}

	public RelatedOption(String id){
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
	 * 获取附件
	 * 
	 * @return 附件
	 */	
	@Length(min=1, max=255)
	public String getOptions() {
		return options;
	}
	/**
	 * 设置附件
	 * 
	 * @param options
	 *            附件
	 */
	public void setOptions(String options) {
		this.options = options;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}