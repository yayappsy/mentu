/**
 * 
 */
package com.weimhc.modules.job.service;


import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.weimhc.modules.job.entity.Access;
import com.weimhc.modules.job.dao.AccessDao;

import com.weimhc.framework.service.impl.CrudServiceImpl;

/**
 * 个人评价Service
 * @author cwl
 * @version 2018-01-07
 */
@Service
@Transactional(readOnly = true)
public class AccessService extends CrudServiceImpl<AccessDao, Access> {


    public Access getByResumeId(String resumeId) {
        return dao.getByResumeId(resumeId);
    }
}