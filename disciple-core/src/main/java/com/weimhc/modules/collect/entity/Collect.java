/**
 * 
 */
package com.weimhc.modules.collect.entity;

import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

import com.thinkgem.javamg.common.persistence.DataEntity;
import com.weimhc.modules.company.entity.Company;
import com.weimhc.modules.job.entity.Job;
import com.weimhc.modules.user.entity.UserInfo;

/**
 * 收藏Entity
 * @author lc
 * @version 2017-11-15
 */
public class Collect extends DataEntity<Collect> {
	
	private static final long serialVersionUID = 1L;
	private UserInfo userInfo;		// 用户
	private CollectCategory category;		// 类别
	private Company company;		// 收藏企业
	private Job job;		// 收藏职位
	
	public Collect() {
		super();
	}

	public Collect(String id){
		super(id);
	}
	public enum CollectCategory{
		/**
		 * 企业
		 */
		company,
		/**
		 * 职位
		 */
		job;
	}

	@NotNull()
	public UserInfo getUserInfo() {
		return userInfo;
	}

	public void setUserInfo(UserInfo userInfo) {
		this.userInfo = userInfo;
	}
	
	@Length(min=1, max=50)
	public CollectCategory getCategory() {
		return category;
	}

	public void setCategory(CollectCategory category) {
		this.category = category;
	}
	
	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}
	
	public Job getJob() {
		return job;
	}

	public void setJob(Job job) {
		this.job = job;
	}
	
}