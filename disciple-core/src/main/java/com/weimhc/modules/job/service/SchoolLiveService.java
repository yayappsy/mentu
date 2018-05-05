/**
 * 
 */
package com.weimhc.modules.job.service;


import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.weimhc.modules.job.entity.SchoolLive;
import com.weimhc.modules.job.dao.SchoolLiveDao;

import com.weimhc.framework.service.impl.CrudServiceImpl;

import java.util.List;

/**
 * 校园经历Service
 * @author cwl
 * @version 2017-12-26
 */
@Service
@Transactional(readOnly = true)
public class SchoolLiveService extends CrudServiceImpl<SchoolLiveDao, SchoolLive> {


    public List<SchoolLive> getByResumeId(String resumeId) {

        return dao.getByResumeId(resumeId);
    }
}