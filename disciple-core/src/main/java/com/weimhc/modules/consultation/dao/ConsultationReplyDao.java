/**
 * 
 */
package com.weimhc.modules.consultation.dao;

import com.thinkgem.javamg.common.persistence.annotation.MyBatisDao;
import com.weimhc.framework.persistence.CrudDao;
import com.weimhc.modules.consultation.entity.ConsultationReply;

/**
 * 咨询回复DAO接口
 * @author zsm
 * @version 2017-01-05
 */
@MyBatisDao
public interface ConsultationReplyDao extends CrudDao<ConsultationReply> {
	
}