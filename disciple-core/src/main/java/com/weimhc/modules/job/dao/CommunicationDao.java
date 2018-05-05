/**
 * 
 */
package com.weimhc.modules.job.dao;

import com.thinkgem.javamg.common.persistence.annotation.MyBatisDao;
import com.weimhc.modules.job.entity.Communication;

import com.weimhc.framework.persistence.CrudDao;
import org.apache.ibatis.annotations.Param;

/**
 * 收发信息DAO接口
 * @author cwl
 * @version 2018-01-22
 */
@MyBatisDao
public interface CommunicationDao extends CrudDao<Communication> {

    void isRead(@Param(value = "sendId") String sendId, @Param(value = "receiveId") String receiveId);
}