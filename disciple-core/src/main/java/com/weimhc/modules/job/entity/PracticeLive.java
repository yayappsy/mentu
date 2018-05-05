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
 * 实习经历Entity
 * @author cwl
 * @version 2017-12-26
 */
public class PracticeLive extends DataEntity<PracticeLive>{
	
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
	 * 公司名称
	 * 
	 */		
	private String companyName;	
	/**
	 * 职位名称
	 * 
	 */		
	private String jobName;	
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
	 * 实习描述
	 * 
	 */		
	private String description;	
	
	public PracticeLive() {
		super();
	}

	public PracticeLive(String id){
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
	 * 获取公司名称
	 * 
	 * @return 公司名称
	 */	
	@Length(min=1, max=64)
	public String getCompanyName() {
		return companyName;
	}
	/**
	 * 设置公司名称
	 * 
	 * @param companyName
	 *            公司名称
	 */
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	
	/**
	 * 获取职位名称
	 * 
	 * @return 职位名称
	 */	
	@Length(min=1, max=64)
	public String getJobName() {
		return jobName;
	}
	/**
	 * 设置职位名称
	 * 
	 * @param jobName
	 *            职位名称
	 */
	public void setJobName(String jobName) {
		this.jobName = jobName;
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
	 * 获取实习描述
	 * 
	 * @return 实习描述
	 */	
	@Length(min=0, max=255)
	public String getDescription() {
		return description;
	}
	/**
	 * 设置实习描述
	 * 
	 * @param description
	 *            实习描述
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	
}