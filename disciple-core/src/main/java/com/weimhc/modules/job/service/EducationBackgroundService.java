/**
 * 
 */
package com.weimhc.modules.job.service;


import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.weimhc.modules.job.entity.EducationBackground;
import com.weimhc.modules.job.dao.EducationBackgroundDao;

import com.weimhc.framework.service.impl.CrudServiceImpl;

import java.util.List;

/**
 * 教育背景Service
 * @author cwl
 * @version 2017-12-26
 */
@Service
@Transactional(readOnly = true)
public class EducationBackgroundService extends CrudServiceImpl<EducationBackgroundDao, EducationBackground> {


    public List<EducationBackground> getByResumeId(String resumeId) {
        return dao.getByResumeId(resumeId);
    }
}