/**
 * 
 */
package com.weimhc.modules.job.dao;

import com.thinkgem.javamg.common.persistence.CrudDao;
import com.thinkgem.javamg.common.persistence.annotation.MyBatisDao;
import com.weimhc.modules.company.entity.Company;
import com.weimhc.modules.company.entity.CompanyLabel;
import com.weimhc.modules.job.entity.Job;
import com.weimhc.modules.job.entity.JobLabel;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 职位DAO接口
 * @author lc
 * @version 2017-11-15
 */
@MyBatisDao
public interface JobDao extends CrudDao<Job> {
	/**
	 * 删除职位标签关联数据
	 *
	 * @param Job
	 * @return
	 */
	public int deleteJobLabel(Job job);

	/**
	 * 插入职位标签关联数据
	 *
	 * @param Job
	 * @return
	 */
	public int insertJobLabel(Job job);


    Integer getHitNum();

    Integer getPublishCount(String companyId);

	Integer getJobNum(Job job);

	List<JobLabel> getLabels(String jobId);

    List<Job> getJobNames(@Param(value = "companyId") String id);

    void lineOff(String id);

    void deleteJob(Job job);

	void fresh(String id);

	void overdueJob();

    void addNumber(String id);

	Integer getJobInNumTotal(String companyId);

	Integer getJobNumTotal(String companyId);

	Integer getJobInFaceTotal(String companyId);

    List<Job> findListPush(Job job);

	Job getPush(String id);

    String getPushIds();

	void bindCompanyJob(@Param(value = "companyPushId") String companyId, @Param(value = "id") String jobId);

    List<Job> getCompanyJobs(@Param("companyId") String companyId, @Param("labelId") String labelId, @Param("isHitJob") String isHitJob);
}