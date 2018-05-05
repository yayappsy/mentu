/**
 * 
 */
package com.weimhc.modules.job.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.javamg.common.persistence.Page;
import com.weimhc.modules.job.entity.JobLabel;
import com.weimhc.framework.service.impl.CrudServiceImpl;
import com.weimhc.modules.job.dao.JobLabelDao;

/**
 * 职位标签Service
 * @author lc
 * @version 2017-11-15
 */
@Service
@Transactional(readOnly = true)
public class JobLabelService extends CrudServiceImpl<JobLabelDao, JobLabel> {

	public JobLabel get(String id) {
		return super.get(id);
	}

	public long count(JobLabel jobLabel) {
		return super.count(jobLabel);
	}
	
	public boolean exists(String id) {
		return super.exists(id);
	}
	
	public List<JobLabel> findList(JobLabel jobLabel) {
		return super.findList(jobLabel);
	}
	
	public Page<JobLabel> findPage(Page<JobLabel> page, JobLabel jobLabel) {
		return super.findPage(page, jobLabel);
	}
	
	@Transactional(readOnly = false)
	public void save(JobLabel jobLabel) {
		super.save(jobLabel);
	}
	
	@Transactional(readOnly = false)
	public void delete(JobLabel jobLabel) {
		super.delete(jobLabel);
	}
	public List<JobLabel> findJobLabel(JobLabel jobLabel){
		return dao.findJobLabel(jobLabel);
	}
}