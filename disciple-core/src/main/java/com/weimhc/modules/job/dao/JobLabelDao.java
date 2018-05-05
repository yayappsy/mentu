/**
 * 
 */
package com.weimhc.modules.job.dao;

import java.util.List;

import com.thinkgem.javamg.common.persistence.annotation.MyBatisDao;
import com.weimhc.framework.persistence.CrudDao;
import com.weimhc.modules.company.entity.CompanyLabel;
import com.weimhc.modules.job.entity.JobLabel;
import org.apache.ibatis.annotations.Param;

/**
 * 职位标签DAO接口
 * @author lc
 * @version 2017-11-15
 */
@MyBatisDao
public interface JobLabelDao extends CrudDao<JobLabel> {
	public List<JobLabel> findJobLabel(JobLabel jobLabel);

	void insertJobLabel(@Param(value = "jobId") String jobId, @Param(value = "jobLabelId") String jobLabelId, @Param(value = "id") String id);
}