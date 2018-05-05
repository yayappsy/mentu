/**
 * 
 */
package com.weimhc.modules.job.service;


import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.weimhc.modules.job.entity.LearningLive;
import com.weimhc.modules.job.dao.LearningLiveDao;

import com.weimhc.framework.service.impl.CrudServiceImpl;

import java.util.List;

/**
 * 学术经历Service
 * @author cwl
 * @version 2017-12-26
 */
@Service
@Transactional(readOnly = true)
public class LearningLiveService extends CrudServiceImpl<LearningLiveDao, LearningLive> {


    public List<LearningLive> getByResumeId(String resumeId) {
        return dao.getByResumeId(resumeId);
    }
}