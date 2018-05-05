/**
 * 
 */
package com.weimhc.modules.job.dao;

import com.thinkgem.javamg.common.persistence.annotation.MyBatisDao;
import com.weimhc.modules.job.entity.Resume;

import com.weimhc.framework.persistence.CrudDao;
import org.apache.ibatis.annotations.Param;

/**
 * 简历DAO接口
 * @author cwl
 * @version 2018-01-06
 */
@MyBatisDao
public interface ResumeDao extends CrudDao<Resume> {

    void updateUpdateDate(String resumeId);

    Integer moduleIsExist(@Param(value = "resumeId") String resumeId, @Param(value = "moduleTableName") String moduleTableName);

    Integer moduleIsExistRelatedOption(@Param(value = "resumeId") String resumeId, @Param(value = "moduleTableName") String moduleTableName, @Param(value = "type") String type);
}