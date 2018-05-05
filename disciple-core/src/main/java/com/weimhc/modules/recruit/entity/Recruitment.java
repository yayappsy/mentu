/**
 * 
 */
package com.weimhc.modules.recruit.entity;

import java.util.Date;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.thinkgem.javamg.common.persistence.DataEntity;
import com.weimhc.modules.base.entity.Department;
import com.weimhc.modules.base.entity.Education;
import com.weimhc.modules.base.entity.Region;
import com.weimhc.modules.base.entity.WorkingYears;

/**
 * 招聘Entity
 * 
 * @author zsm
 * @version 2017-03-27
 */
public class Recruitment extends DataEntity<Recruitment> {

	private static final long serialVersionUID = 1L;
	/**
	 * 职位名称
	 * 
	 */
	private String jobTitle;
	/**
	 * 性别
	 * 
	 */
	private String gender;
	/**
	 * 薪资范围
	 * 
	 */
	private String salaryRangeId;
	/**
	 * 工作地区
	 * 
	 */
	private Region area;
	/**
	 * 工作部门
	 * 
	 */
	private Department department;
	/**
	 * 工作性质
	 * 
	 */
	private String workingProperty;
	/**
	 * 接受语言
	 * 
	 */
	private String resumeLanguage;
	/**
	 * 年龄限制
	 * 
	 */
	private String ageLimit;
	/**
	 * 截止日期
	 * 
	 */
	private Date deadline;
	/**
	 * 关键词
	 * 
	 */
	private String keywords;
	/**
	 * 招聘人数
	 * 
	 */
	private String number;
	/**
	 * 职位类型
	 * 
	 */
	private RecruitmentType employType;
	/**
	 * 最低学历
	 * 
	 */
	private Education minimumEducation;
	/**
	 * 工作时间
	 * 
	 */
	private WorkingYears workingTime;
	/**
	 * 发布日期
	 * 
	 */
	private Date releaseDate;
	/**
	 * 是否显示
	 * 
	 */
	private Boolean isShow;
	/**
	 * 职位描述
	 * 
	 */
	private String jobDescription;
	/**
	 * 能力要求
	 * 
	 */
	private String requirement;
	/**
	 * 其他
	 * 
	 */
	private String other;
	/**
	 * 职位标记
	 */
	private String positionMark;

	public Recruitment() {
		super();
	}

	public Recruitment(String id) {
		super(id);
	}

	/**
	 * 获取职位名称
	 * 
	 * @return 职位名称
	 */
	@Length(min = 1, max = 64)
	public String getJobTitle() {
		return jobTitle;
	}

	/**
	 * 设置职位名称
	 * 
	 * @param jobTitle
	 *            职位名称
	 */
	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}

	/**
	 * 获取性别
	 * 
	 * @return 性别
	 */
	@Length(min = 1, max = 64)
	public String getGender() {
		return gender;
	}

	/**
	 * 设置性别
	 * 
	 * @param gender
	 *            性别
	 */
	public void setGender(String gender) {
		this.gender = gender;
	}

	/**
	 * 获取薪资范围
	 * 
	 * @return 薪资范围
	 */
	@Length(min = 1, max = 255)
	public String getSalaryRangeId() {
		return salaryRangeId;
	}

	/**
	 * 设置薪资范围
	 * 
	 * @param salaryRangeId
	 *            薪资范围
	 */
	public void setSalaryRangeId(String salaryRangeId) {
		this.salaryRangeId = salaryRangeId;
	}

	/**
	 * 获取工作地区
	 * 
	 * @return 工作地区
	 */
	@NotNull()
	public Region getArea() {
		return area;
	}

	/**
	 * 设置工作地区
	 * 
	 * @param area
	 *            工作地区
	 */
	public void setArea(Region area) {
		this.area = area;
	}

	/**
	 * 获取年龄限制
	 * 
	 * @return 年龄限制
	 */
	@Length(min = 0, max = 64)
	public String getAgeLimit() {
		return ageLimit;
	}

	/**
	 * 设置年龄限制
	 * 
	 * @param ageLimit
	 *            年龄限制
	 */
	public void setAgeLimit(String ageLimit) {
		this.ageLimit = ageLimit;
	}

	/**
	 * 获取截止日期
	 * 
	 * @return 截止日期
	 */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@NotNull()
	public Date getDeadline() {
		return deadline;
	}

	/**
	 * 设置截止日期
	 * 
	 * @param deadline
	 *            截止日期
	 */
	public void setDeadline(Date deadline) {
		this.deadline = deadline;
	}

	/**
	 * 获取关键词
	 * 
	 * @return 关键词
	 */
	@Length(min = 1, max = 64)
	public String getKeywords() {
		return keywords;
	}

	/**
	 * 设置关键词
	 * 
	 * @param keywords
	 *            关键词
	 */
	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}

	/**
	 * 获取招聘人数
	 * 
	 * @return 招聘人数
	 */
	@Length(min = 1, max = 11)
	public String getNumber() {
		return number;
	}

	/**
	 * 设置招聘人数
	 * 
	 * @param number
	 *            招聘人数
	 */
	public void setNumber(String number) {
		this.number = number;
	}

	/**
	 * 获取职位类型
	 * 
	 * @return 职位类型
	 */
	public RecruitmentType getEmployType() {
		return employType;
	}

	/**
	 * 设置职位类型
	 * 
	 * @param employType
	 *            职位类型
	 */
	public void setEmployType(RecruitmentType employType) {
		this.employType = employType;
	}

	/**
	 * 获取最低学历
	 * 
	 * @return 最低学历
	 */
	public Education getMinimumEducation() {
		return minimumEducation;
	}

	/**
	 * 设置最低学历
	 * 
	 * @param minimumEducationId
	 *            最低学历
	 */
	public void setMinimumEducation(Education minimumEducation) {
		this.minimumEducation = minimumEducation;
	}

	/**
	 * 获取工作时间
	 * 
	 * @return 工作时间
	 */
	public WorkingYears getWorkingTime() {
		return workingTime;
	}

	/**
	 * 设置工作时间
	 * 
	 * @param workingTimeId
	 *            工作时间
	 */
	public void setWorkingTime(WorkingYears workingTime) {
		this.workingTime = workingTime;
	}

	/**
	 * 获取发布日期
	 * 
	 * @return 发布日期
	 */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@NotNull()
	public Date getReleaseDate() {
		return releaseDate;
	}

	/**
	 * 设置发布日期
	 * 
	 * @param releaseDate
	 *            发布日期
	 */
	public void setReleaseDate(Date releaseDate) {
		this.releaseDate = releaseDate;
	}

	/**
	 * 获取是否显示
	 * 
	 * @return 是否显示
	 */
	@NotNull()
	public Boolean getIsShow() {
		return isShow;
	}

	/**
	 * 设置是否显示
	 * 
	 * @param isShow
	 *            是否显示
	 */
	public void setIsShow(Boolean isShow) {
		this.isShow = isShow;
	}

	/**
	 * 获取职位描述
	 * 
	 * @return 职位描述
	 */
	public String getJobDescription() {
		return jobDescription;
	}

	/**
	 * 设置职位描述
	 * 
	 * @param jobDescription
	 *            职位描述
	 */
	public void setJobDescription(String jobDescription) {
		this.jobDescription = jobDescription;
	}

	/**
	 * 获取能力要求
	 * 
	 * @return 能力要求
	 */
	public String getRequirement() {
		return requirement;
	}

	/**
	 * 设置能力要求
	 * 
	 * @param requirement
	 *            能力要求
	 */
	public void setRequirement(String requirement) {
		this.requirement = requirement;
	}

	/**
	 * 获取其他
	 * 
	 * @return 其他
	 */
	public String getOther() {
		return other;
	}

	/**
	 * 设置其他
	 * 
	 * @param other
	 *            其他
	 */
	public void setOther(String other) {
		this.other = other;
	}

	/**
	 * 获取工作部门
	 * 
	 * @return the department
	 */
	public Department getDepartment() {
		return department;
	}

	/**
	 * 设置 工作部门
	 * 
	 * @param department
	 *            the department to set
	 */
	public void setDepartment(Department department) {
		this.department = department;
	}

	/**
	 * 获取工作性质
	 * 
	 * @return the workingProperty
	 */
	public String getWorkingProperty() {
		return workingProperty;
	}

	/**
	 * 设置工作性质
	 * 
	 * @param workingProperty
	 *            the workingProperty to set
	 */
	public void setWorkingProperty(String workingProperty) {
		this.workingProperty = workingProperty;
	}

	/**
	 * @return the resumeLanguage
	 */
	public String getResumeLanguage() {
		return resumeLanguage;
	}

	/**
	 * @param resumeLanguage
	 *            the resumeLanguage to set
	 */
	public void setResumeLanguage(String resumeLanguage) {
		this.resumeLanguage = resumeLanguage;
	}

	/**
	 * @return the positionMark
	 */
	public String getPositionMark() {
		return positionMark;
	}

	/**
	 * @param positionMark
	 *            the positionMark to set
	 */
	public void setPositionMark(String positionMark) {
		this.positionMark = positionMark;
	}

}