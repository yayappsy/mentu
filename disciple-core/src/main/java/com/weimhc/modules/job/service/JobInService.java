/**
 * 
 */
package com.weimhc.modules.job.service;


import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.weimhc.modules.job.entity.JobIn;
import com.weimhc.modules.job.dao.JobInDao;

import com.weimhc.framework.service.impl.CrudServiceImpl;

import java.util.List;

/**
 * 应聘管理Service
 * @author cwl
 * @version 2018-01-08
 */
@Service
@Transactional(readOnly = true)
public class JobInService extends CrudServiceImpl<JobInDao, JobIn> {


    /**
     * 未读简历数量
     * @param companyId
     * @return
     */
    public Integer handleNum(String companyId) {
        return dao.handleNum(companyId);
    }

    @Transactional(readOnly = false)
    public void lookResume(String companyId, String resumeId) {
        dao.lookResume(companyId, resumeId);
    }

    public Integer getJobInNum(String companyId,String status,String dateStr) {
        return dao.getJobInNum(companyId,status,dateStr);
    }

    public List<JobIn> reJobInList(String memberId, String type) {
        return dao.reJobInList(memberId, type);
    }
}