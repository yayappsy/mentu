/**
 * 
 */
package com.weimhc.modules.consultation.dao;

import com.thinkgem.javamg.common.persistence.annotation.MyBatisDao;
import com.weimhc.framework.persistence.CrudDao;
import com.weimhc.modules.consultation.entity.ConsultationType;

/**
 * 咨询类型DAO接口
 * @author zsm
 * @version 2017-01-05
 */
@MyBatisDao
public interface ConsultationTypeDao extends CrudDao<ConsultationType> {
	
}