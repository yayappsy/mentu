/**
 * 
 */
package com.weimhc.modules.job.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.weimhc.modules.company.entity.CompanyLabel;
import com.weimhc.modules.job.entity.JobLabel;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.javamg.common.persistence.Page;
import com.thinkgem.javamg.common.service.ServiceException;
import com.thinkgem.javamg.common.service.impl.CrudServiceImpl;
import com.thinkgem.javamg.common.utils.StringUtils;
import com.weimhc.modules.job.entity.Job;
import com.weimhc.modules.company.entity.Company;
import com.weimhc.modules.job.dao.JobDao;

/**
 * 职位Service
 * @author lc
 * @version 2017-11-15
 */
@Service
@Transactional(readOnly = true)
public class JobService extends CrudServiceImpl<JobDao, Job> {

	public Job get(String id) {
		return super.get(id);
	}

	public long count(Job job) {
		return super.count(job);
	}
	
	public boolean exists(String id) {
		return super.exists(id);
	}
	
	public List<Job> findList(Job job) {
		return super.findList(job);
	}
	
	public Page<Job> findPage(Page<Job> page, Job job) {
		return super.findPage(page, job);
	}
	
	@Transactional(readOnly = false)
	public void save(Job job) {
		super.save(job);
	}
	
	@Transactional(readOnly = false)
	public void delete(Job job) {
		super.delete(job);
	}
	
	/**
	 * 更新职位信息
	 * 
	 * @param job
	 */
	@Transactional(readOnly = false)
	public void saveJob(Job job) {
		save(job);
/*
		if (StringUtils.isNotBlank(job.getId())) {
			// 更新职位与标签关联
			dao.deleteJobLabel(job);
			if (job.getJobLabelList() != null && job.getJobLabelList().size() > 0) {
				dao.insertJobLabel(job);
			} else {
				throw new ServiceException(job.getName() + "没有设置标签！");
			}

		}
*/
	}


	public List<Job> getCompanyJobs(String companyId, String labelId, String isHitJob) {
		return dao.getCompanyJobs(companyId, labelId, isHitJob);
	}

	public Integer getHitNum() {
		return dao.getHitNum();
	}

	public Integer getPublishCount(String companyId) {
		return dao.getPublishCount(companyId);
	}

	public Integer getJobNum(Job job) {
		return dao.getJobNum(job);
	}

	public List<Job> getJobNames(String id) {
		return dao.getJobNames(id);
	}

	@Transactional(readOnly = false)
	public void deleteJob(Job job) {
		dao.deleteJob(job);
	}

	@Transactional(readOnly = false)
	public Boolean fresh(List<String> ids) {
		for (String id : ids) {
			dao.fresh(id);
		}
		return true;
	}

	/**
	 * 查询已过期的时候就更新过期状态
	 */
    @Transactional(readOnly = false)
    public void overdueJob() {
        dao.overdueJob();
    }

    @Transactional(readOnly = false)
    public void addNumber(String jobId) {
        dao.addNumber(jobId);
    }

    public Integer getJobInNumTotal(String companyId) {
        return dao.getJobInNumTotal(companyId);
    }

    public Integer getJobNumTotal(String companyId) {
        return dao.getJobNumTotal(companyId);
    }

    public Integer getJobInFaceTotal(String companyId) {
        return dao.getJobInFaceTotal(companyId);
    }

    public Page<Job> findPagePush(Page<Job> page, Job job) {
        job.setPage(page);
        page.setList(dao.findListPush(job));
        return page;
    }

    public Job getPush(String id) {
        return dao.getPush(id);
    }

    /**
     * 获取内推的企业ids
     * @return
     */
    public String getPushIds() {
        return dao.getPushIds();
    }

    @Transactional(readOnly = false)
    public Boolean bindCompanyJob(String companyId, String jobId) {
        dao.bindCompanyJob(companyId, jobId);
        return true;
    }
}