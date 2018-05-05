/**
 * 
 */
package com.weimhc.modules.job.dao;

import com.thinkgem.javamg.common.persistence.annotation.MyBatisDao;
import com.weimhc.modules.job.entity.CollectStore;

import com.weimhc.framework.persistence.CrudDao;
import org.apache.ibatis.annotations.Param;

/**
 * 收藏表DAO接口
 * @author cwl
 * @version 2018-01-07
 */
@MyBatisDao
public interface CollectStoreDao extends CrudDao<CollectStore> {

    void deleteByOther(CollectStore collectStore);

    String getCollectJobIdsByUserId(@Param(value = "memberId") String memberId);

    String getCollectCompanyIdsByUserId(@Param(value = "memberId") String memberId);
}