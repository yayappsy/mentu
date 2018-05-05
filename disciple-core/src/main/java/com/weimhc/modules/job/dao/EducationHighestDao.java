/**
 * 
 */
package com.weimhc.modules.job.dao;

import com.thinkgem.javamg.common.persistence.annotation.MyBatisDao;
import com.weimhc.modules.job.entity.EducationHighest;

import com.weimhc.framework.persistence.CrudDao;

/**
 * 毕业学校DAO接口
 * @author cwl
 * @version 2018-02-24
 */
@MyBatisDao
public interface EducationHighestDao extends CrudDao<EducationHighest> {

    EducationHighest getByMemberId(EducationHighest educationHighest);
}