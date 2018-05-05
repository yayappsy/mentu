/**
 * 
 */
package com.weimhc.modules.job.entity;

import com.weimhc.modules.sys.entity.Area;
import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import javax.validation.constraints.NotNull;

import com.thinkgem.javamg.common.persistence.DataEntity;

/**
 * 教育背景Entity
 * @author cwl
 * @version 2017-12-26
 */
public class EducationBackground extends DataEntity<EducationBackground>{
	
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
	 * 学校
	 * 
	 */		
	private String school;	
	/**
	 * 专业
	 * 
	 */		
	private String majorId;
	/**
	 * 是否最高学历(0不是，1是)
	 */
	private String eduHighest;
	/**
	 * 学历
	 * 
	 */		
	private String educationId;	
	/**
	 * 城市
	 * 
	 */		
	private Area city;
	/**
	 * 开始在校时间
	 * 
	 */		
	private Date beginDate;	
	/**
	 * 结束在校时间
	 * 
	 */		
	private Date endDate;	
	/**
	 * 成绩排名
	 * 
	 */		
	private String classRank;	
	/**
	 * 主修课程
	 * 
	 */		
	private String majorCourse;	
	/**
	 * 荣誉奖励
	 * 
	 */		
	private String honor;	
	
	public EducationBackground() {
		super();
	}

	public EducationBackground(String id){
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
	 * 获取学校
	 * 
	 * @return 学校
	 */	
	@Length(min=1, max=50)
	public String getSchool() {
		return school;
	}
	/**
	 * 设置学校
	 * 
	 * @param school
	 *            学校
	 */
	public void setSchool(String school) {
		this.school = school;
	}
	
	/**
	 * 获取专业
	 * 
	 * @return 专业
	 */	
	@Length(min=1, max=64)
	public String getMajorId() {
		return majorId;
	}
	/**
	 * 设置专业
	 * 
	 * @param majorId
	 *            专业
	 */
	public void setMajorId(String majorId) {
		this.majorId = majorId;
	}
	
	/**
	 * 获取学历
	 * 
	 * @return 学历
	 */	
	@Length(min=1, max=64)
	public String getEducationId() {
		return educationId;
	}
	/**
	 * 设置学历
	 * 
	 * @param educationId
	 *            学历
	 */
	public void setEducationId(String educationId) {
		this.educationId = educationId;
	}
	
	/**
	 * 获取城市
	 * 
	 * @return 城市
	 */
	public Area getCity() {
		return city;
	}
	/**
	 * 设置城市
	 * 
	 * @param city
	 *            城市
	 */
	public void setCity(Area city) {
		this.city = city;
	}
	
	/**
	 * 获取开始在校时间
	 * 
	 * @return 开始在校时间
	 */	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@NotNull()
	public Date getBeginDate() {
		return beginDate;
	}
	/**
	 * 设置开始在校时间
	 * 
	 * @param beginDate
	 *            开始在校时间
	 */
	public void setBeginDate(Date beginDate) {
		this.beginDate = beginDate;
	}
	
	/**
	 * 获取结束在校时间
	 * 
	 * @return 结束在校时间
	 */	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getEndDate() {
		return endDate;
	}
	/**
	 * 设置结束在校时间
	 * 
	 * @param endDate
	 *            结束在校时间
	 */
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	
	/**
	 * 获取成绩排名
	 * 
	 * @return 成绩排名
	 */	
	@Length(min=0, max=64)
	public String getClassRank() {
		return classRank;
	}
	/**
	 * 设置成绩排名
	 * 
	 * @param classRank
	 *            成绩排名
	 */
	public void setClassRank(String classRank) {
		this.classRank = classRank;
	}
	
	/**
	 * 获取主修课程
	 * 
	 * @return 主修课程
	 */	
	@Length(min=0, max=255)
	public String getMajorCourse() {
		return majorCourse;
	}
	/**
	 * 设置主修课程
	 * 
	 * @param majorCourse
	 *            主修课程
	 */
	public void setMajorCourse(String majorCourse) {
		this.majorCourse = majorCourse;
	}
	
	/**
	 * 获取荣誉奖励
	 * 
	 * @return 荣誉奖励
	 */	
	@Length(min=0, max=255)
	public String getHonor() {
		return honor;
	}
	/**
	 * 设置荣誉奖励
	 * 
	 * @param honor
	 *            荣誉奖励
	 */
	public void setHonor(String honor) {
		this.honor = honor;
	}

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public String getEduHighest() {
		return eduHighest;
	}

	public void setEduHighest(String eduHighest) {
		this.eduHighest = eduHighest;
	}
}