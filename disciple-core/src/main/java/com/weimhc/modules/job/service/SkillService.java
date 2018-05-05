/**
 * 
 */
package com.weimhc.modules.job.service;


import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.weimhc.modules.job.entity.Skill;
import com.weimhc.modules.job.dao.SkillDao;

import com.weimhc.framework.service.impl.CrudServiceImpl;

import java.util.List;

/**
 * 技能Service
 * @author cwl
 * @version 2017-12-26
 */
@Service
@Transactional(readOnly = true)
public class SkillService extends CrudServiceImpl<SkillDao, Skill> {


    public List<Skill> getByResumeId(String resumeId) {
        return dao.getByResumeId(resumeId);
    }
}