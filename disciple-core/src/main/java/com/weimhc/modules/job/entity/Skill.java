/**
 * 
 */
package com.weimhc.modules.job.entity;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.javamg.common.persistence.DataEntity;

/**
 * 技能Entity
 * @author cwl
 * @version 2017-12-26
 */
public class Skill extends DataEntity<Skill>{
	
	private static final long serialVersionUID = 1L;
	/**
	 * 简历id
	 * 
	 */		
	private String resumeId;	
	/**
	 * 会员id
	 * 
	 */		
	private String memberId;	
	/**
	 * 技能类型
	 * 
	 */		
	private String type;	
	/**
	 * 技能说明
	 * 
	 */		
	private String explain;	
	
	public Skill() {
		super();
	}

	public Skill(String id){
		super(id);
	}

	/**
	 * 获取简历id
	 * 
	 * @return 简历id
	 */	
	@Length(min=0, max=64)
	public String getResumeId() {
		return resumeId;
	}
	/**
	 * 设置简历id
	 * 
	 * @param resumeId
	 *            简历id
	 */
	public void setResumeId(String resumeId) {
		this.resumeId = resumeId;
	}
	
	/**
	 * 获取会员id
	 * 
	 * @return 会员id
	 */	
	@Length(min=0, max=64)
	public String getMemberId() {
		return memberId;
	}
	/**
	 * 设置会员id
	 * 
	 * @param memberId
	 *            会员id
	 */
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	
	/**
	 * 获取技能类型
	 * 
	 * @return 技能类型
	 */	
	@Length(min=1, max=255)
	public String getType() {
		return type;
	}
	/**
	 * 设置技能类型
	 * 
	 * @param type
	 *            技能类型
	 */
	public void setType(String type) {
		this.type = type;
	}
	
	/**
	 * 获取技能说明
	 * 
	 * @return 技能说明
	 */	
	public String getExplain() {
		return explain;
	}
	/**
	 * 设置技能说明
	 * 
	 * @param explain
	 *            技能说明
	 */
	public void setExplain(String explain) {
		this.explain = explain;
	}
	
}