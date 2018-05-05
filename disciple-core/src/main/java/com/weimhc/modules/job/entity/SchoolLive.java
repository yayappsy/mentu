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
 * 校园经历Entity
 * @author cwl
 * @version 2017-12-26
 */
public class SchoolLive extends DataEntity<SchoolLive>{
	
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
	 * 城市id
	 * 
	 */		
	private Area city;
	/**
	 * 项目名称
	 * 
	 */		
	private String projectName;	
	/**
	 * 担任职务
	 * 
	 */		
	private String assumeOffice;	
	/**
	 * 开始时间
	 * 
	 */		
	private Date beginDate;	
	/**
	 * 结束时间
	 * 
	 */		
	private Date endDate;	
	/**
	 * 实习描述
	 * 
	 */		
	private String description;	
	
	public SchoolLive() {
		super();
	}

	public SchoolLive(String id){
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
	 * 获取项目名称
	 * 
	 * @return 项目名称
	 */	
	@Length(min=1, max=64)
	public String getProjectName() {
		return projectName;
	}
	/**
	 * 设置项目名称
	 * 
	 * @param projectName
	 *            项目名称
	 */
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	
	/**
	 * 获取担任职务
	 * 
	 * @return 担任职务
	 */	
	@Length(min=1, max=64)
	public String getAssumeOffice() {
		return assumeOffice;
	}
	/**
	 * 设置担任职务
	 * 
	 * @param assumeOffice
	 *            担任职务
	 */
	public void setAssumeOffice(String assumeOffice) {
		this.assumeOffice = assumeOffice;
	}
	
	/**
	 * 获取开始时间
	 * 
	 * @return 开始时间
	 */	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@NotNull()
	public Date getBeginDate() {
		return beginDate;
	}
	/**
	 * 设置开始时间
	 * 
	 * @param beginDate
	 *            开始时间
	 */
	public void setBeginDate(Date beginDate) {
		this.beginDate = beginDate;
	}
	
	/**
	 * 获取结束时间
	 * 
	 * @return 结束时间
	 */	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getEndDate() {
		return endDate;
	}
	/**
	 * 设置结束时间
	 * 
	 * @param endDate
	 *            结束时间
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