/**
 * 
 */
package com.weimhc.modules.job.entity;

import com.weimhc.modules.sys.entity.Area;
import com.weimhc.modules.user.entity.UserInfo;
import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.thinkgem.javamg.common.persistence.DataEntity;

/**
 * 期望实习Entity
 * @author cwl
 * @version 2017-12-26
 */
public class ExpectPractice extends DataEntity<ExpectPractice>{
	
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
	 * 期望职位
	 * 
	 */		
	private String position;	
	/**
	 * 地点
	 * 
	 */		
	private Area city;
	/**
	 * 天数/周
	 * 
	 */		
	private Integer days;	
	/**
	 * 实习开始月份
	 * 
	 */		
	private Date beginMonth;	
	/**
	 * 实习结束月份
	 * 
	 */		
	private Date endMonth;	
	/**
	 * 期望日薪
	 * 
	 */		
	private Integer dailyWage;	
	/**
	 * 到岗日期
	 * 
	 */		
	private Date arrivalDate;

	private UserInfo userInfo;

	private JobIn jobIn;

	private Double months;

	private EducationBackground educationBackground;
	public Double getMonths() {
		return months;
	}

	public void setMonths(Double months) {
		this.months = months;
	}


	public UserInfo getUserInfo() {
		return userInfo;
	}

	public JobIn getJobIn() {
		return jobIn;
	}

	public void setJobIn(JobIn jobIn) {
		this.jobIn = jobIn;
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

	public ExpectPractice() {
		super();
	}

	public ExpectPractice(String id){
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
	 * 获取期望职位
	 * 
	 * @return 期望职位
	 */	
	@Length(min=1, max=64)
	public String getPosition() {
		return position;
	}
	/**
	 * 设置期望职位
	 * 
	 * @param position
	 *            期望职位
	 */
	public void setPosition(String position) {
		this.position = position;
	}
	
	/**
	 * 获取地点
	 * 
	 * @return 地点
	 */
	public Area getCity() {
		return city;
	}
	/**
	 * 设置地点
	 * 
	 * @param city
	 *            地点
	 */
	public void setCity(Area city) {
		this.city = city;
	}
	
	/**
	 * 获取天数/周
	 * 
	 * @return 天数/周
	 */	
	public Integer getDays() {
		return days;
	}
	/**
	 * 设置天数/周
	 * 
	 * @param days
	 *            天数/周
	 */
	public void setDays(Integer days) {
		this.days = days;
	}
	
	/**
	 * 获取实习开始月份
	 * 
	 * @return 实习开始月份
	 */	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getBeginMonth() {
		return beginMonth;
	}
	/**
	 * 设置实习开始月份
	 * 
	 * @param beginMonth
	 *            实习开始月份
	 */
	public void setBeginMonth(Date beginMonth) {
		this.beginMonth = beginMonth;
	}
	
	/**
	 * 获取实习结束月份
	 * 
	 * @return 实习结束月份
	 */	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getEndMonth() {
		return endMonth;
	}
	/**
	 * 设置实习结束月份
	 * 
	 * @param endMonth
	 *            实习结束月份
	 */
	public void setEndMonth(Date endMonth) {
		this.endMonth = endMonth;
	}
	
	/**
	 * 获取期望日薪
	 * 
	 * @return 期望日薪
	 */	
	public Integer getDailyWage() {
		return dailyWage;
	}
	/**
	 * 设置期望日薪
	 * 
	 * @param dailyWage
	 *            期望日薪
	 */
	public void setDailyWage(Integer dailyWage) {
		this.dailyWage = dailyWage;
	}
	
	/**
	 * 获取到岗日期
	 * 
	 * @return 到岗日期
	 */	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getArrivalDate() {
		return arrivalDate;
	}
	/**
	 * 设置到岗日期
	 * 
	 * @param arrivalDate
	 *            到岗日期
	 */
	public void setArrivalDate(Date arrivalDate) {
		this.arrivalDate = arrivalDate;
	}
	
}