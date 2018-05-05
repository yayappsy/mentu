/**
 * 
 */
package com.weimhc.modules.job.dao;

import com.thinkgem.javamg.common.persistence.annotation.MyBatisDao;
import com.weimhc.modules.job.entity.Skill;

import com.weimhc.framework.persistence.CrudDao;

import java.util.List;

/**
 * 技能DAO接口
 * @author cwl
 * @version 2017-12-26
 */
@MyBatisDao
public interface SkillDao extends CrudDao<Skill> {

    List<Skill> getByResumeId(String resumeId);
}