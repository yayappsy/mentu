/**
 * 
 */
package com.weimhc.modules.job.entity;

import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.Length;

import com.thinkgem.javamg.common.persistence.DataEntity;

/**
 * 简历Entity
 * @author cwl
 * @version 2018-01-06
 */
public class Resume extends DataEntity<Resume>{
	
	private static final long serialVersionUID = 1L;
	/**
	 * 会员
	 * 
	 */		
	private String userInfoId;	
	/**
	 * 语言
	 * 
	 */		
	private String language;	
	/**
	 * 简历类型
	 * 
	 */		
	private String resumeType;

	private String name;
	
	public Resume() {
		super();
	}

	public Resume(String id){
		super(id);
	}

	/**
	 * 获取会员
	 * 
	 * @return 会员
	 */	
	@Length(min=1, max=64)
	public String getUserInfoId() {
		return userInfoId;
	}
	/**
	 * 设置会员
	 * 
	 * @param userInfoId
	 *            会员
	 */
	public void setUserInfoId(String userInfoId) {
		this.userInfoId = userInfoId;
	}
	
	/**
	 * 获取语言
	 * 
	 * @return 语言
	 */	
	@Length(min=1, max=50)
	@ApiModelProperty(value = "语言（english:英文,chinese:中文）",example = "english")
	public String getLanguage() {
		return language;
	}
	/**
	 * 设置语言
	 * 
	 * @param language
	 *            语言
	 */
	public void setLanguage(String language) {
		this.language = language;
	}
	
	/**
	 * 获取简历类型
	 * 
	 * @return 简历类型
	 */	
	@Length(min=1, max=50)
	@ApiModelProperty(value = "0:在线简历,1:附件简历",example = "0")
	public String getResumeType() {
		return resumeType;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 设置简历类型
	 * 
	 * @param resumeType

	 *            简历类型
	 */
	public void setResumeType(String resumeType) {
		this.resumeType = resumeType;
	}
	
}