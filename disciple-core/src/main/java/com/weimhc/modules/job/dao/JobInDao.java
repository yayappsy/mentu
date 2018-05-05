/**
 * 
 */
package com.weimhc.modules.job.dao;

import com.thinkgem.javamg.common.persistence.annotation.MyBatisDao;
import com.weimhc.modules.job.entity.JobIn;

import com.weimhc.framework.persistence.CrudDao;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 应聘管理DAO接口
 * @author cwl
 * @version 2018-01-08
 */
@MyBatisDao
public interface JobInDao extends CrudDao<JobIn> {

    Integer handleNum(String companyId);

    void lookResume(@Param(value = "companyId") String companyId, @Param(value = "resumeId") String resumeId);

    Integer getJobInNum(@Param(value = "companyId") String companyId,@Param(value = "status") String status,@Param(value = "today") String today);

    List<JobIn> reJobInList(@Param(value = "userId") String userId, @Param(value = "type") String type);
}