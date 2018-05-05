/**
 * 
 */
package com.weimhc.modules.job.entity;

import com.weimhc.modules.user.entity.UserInfo;
import org.hibernate.validator.constraints.Length;

import com.thinkgem.javamg.common.persistence.DataEntity;

/**
 * 应聘管理Entity
 * @author cwl
 * @version 2018-01-08
 */
public class JobIn extends DataEntity<JobIn>{
	
	private static final long serialVersionUID = 1L;
	/**
	 * 企业id
	 * 
	 */		
	private String companyId;
	/**
	 * 企业id
	 *
	 */
	private String companyName;
	/**
	 * 简历id
	 * 
	 */		
	private String resumeId;	
	private String resumeType;
	/**
	 * 是否查看（0未查看，1已查看）
	 * 
	 */		
	private String isLook;	
	/**
	 * 简历状态（0通知面试，1不合适，2已录用，3待定）
	 * 
	 */		
	private String status;

	private String jobId;

	private String jobName;

	private String cityId;

	private String userId;

	private String cityName;
	private UserInfo userInfo;
	private EducationBackground educationBackground;
	private ExpectPractice expectPractice;
	
	public JobIn() {
		super();
	}

	public JobIn(String id){
		super(id);
	}

	/**
	 * 获取企业id
	 * 
	 * @return 企业id
	 */	
	@Length(min=0, max=64)
	public String getCompanyId() {
		return companyId;
	}

	public String getJobId() {
		return jobId;
	}

	public void setJobId(String jobId) {
		this.jobId = jobId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	/**
	 * 设置企业id
	 * 
	 * @param companyId
	 *            企业id


	 */
	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}

	public ExpectPractice getExpectPractice() {
		return expectPractice;
	}

	public void setExpectPractice(ExpectPractice expectPractice) {
		this.expectPractice = expectPractice;
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

	public UserInfo getUserInfo() {
		return userInfo;
	}

	public void setUserInfo(UserInfo userInfo) {
		this.userInfo = userInfo;
	}

	public EducationBackground getEducationBackground() {
		return educationBackground;
	}

	public void setEducationBackground(EducationBackground educationBackground) {
		this.educationBackground = educationBackground;
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
	 * 获取是否查看（0未查看，1已查看）
	 * 
	 * @return 是否查看（0未查看，1已查看）
	 */	
	@Length(min=0, max=1)
	public String getIsLook() {
		return isLook;
	}
	/**
	 * 设置是否查看（0未查看，1已查看）
	 * 
	 * @param isLook
	 *            是否查看（0未查看，1已查看）
	 */
	public void setIsLook(String isLook) {
		this.isLook = isLook;
	}
	
	/**
	 * 获取简历状态（0通知面试，1不合适，2已录用，3待定）
	 * 
	 * @return 简历状态（0通知面试，1不合适，2已录用，3待定）
	 */	
	@Length(min=0, max=1)
	public String getStatus() {
		return status;
	}
	/**
	 * 设置简历状态（0通知面试，1不合适，2已录用，3待定）
	 * 
	 * @param status
	 *            简历状态（0通知面试，1不合适，2已录用，3待定）
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getResumeType() {
		return resumeType;
	}

	public void setResumeType(String resumeType) {
		this.resumeType = resumeType;
	}

	public String getJobName() {
		return jobName;
	}

	public void setJobName(String jobName) {
		this.jobName = jobName;
	}

	public String getCityId() {
		return cityId;
	}

	public void setCityId(String cityId) {
		this.cityId = cityId;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
}