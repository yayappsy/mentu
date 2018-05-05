/**
 * 
 */
package com.weimhc.modules.job.service;


import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.weimhc.modules.job.entity.PracticeLive;
import com.weimhc.modules.job.dao.PracticeLiveDao;

import com.weimhc.framework.service.impl.CrudServiceImpl;

import java.util.List;

/**
 * 实习经历Service
 * @author cwl
 * @version 2017-12-26
 */
@Service
@Transactional(readOnly = true)
public class PracticeLiveService extends CrudServiceImpl<PracticeLiveDao, PracticeLive> {


    public List<PracticeLive> getByResumeId(String resumeId) {
        return dao.getByResumeId(resumeId);
    }
}