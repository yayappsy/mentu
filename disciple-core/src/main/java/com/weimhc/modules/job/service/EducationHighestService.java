/**
 * 
 */
package com.weimhc.modules.job.service;


import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.weimhc.modules.job.entity.EducationHighest;
import com.weimhc.modules.job.dao.EducationHighestDao;

import com.weimhc.framework.service.impl.CrudServiceImpl;

/**
 * 毕业学校Service
 * @author cwl
 * @version 2018-02-24
 */
@Service
@Transactional(readOnly = true)
public class EducationHighestService extends CrudServiceImpl<EducationHighestDao, EducationHighest> {


    public EducationHighest getByMemberId(String memberId) {
        EducationHighest educationHighest = new EducationHighest();
        educationHighest.setMemberId(memberId);
        return dao.getByMemberId(educationHighest);
    }
}