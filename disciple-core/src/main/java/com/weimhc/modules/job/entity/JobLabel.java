/**
 * 
 */
package com.weimhc.modules.job.entity;

import org.hibernate.validator.constraints.Length;
import java.util.List;
import com.google.common.collect.Lists;

import com.thinkgem.javamg.common.persistence.DataEntity;
import com.weimhc.framework.persistence.SortableEntity;

/**
 * 职位标签Entity
 * @author lc
 * @version 2017-11-15
 */
public class JobLabel extends SortableEntity<JobLabel> {
	
	private static final long serialVersionUID = 1L;
	private String name;		// 标签名称
	
	private Job job;// 根据职位查询标签
	public JobLabel() {
		super();
	}

	public JobLabel(String id){
		super(id);
	}

	@Length(min=1, max=255)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the job
	 */
	public Job getJob() {
		return job;
	}

	/**
	 * @param job the job to set
	 */
	public void setJob(Job job) {
		this.job = job;
	}
	
}