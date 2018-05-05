/**
 * 
 */
package com.weimhc.modules.base.dao;

import com.thinkgem.javamg.common.persistence.annotation.MyBatisDao;
import com.weimhc.framework.persistence.CrudDao;
import com.weimhc.modules.base.entity.BodyPart;

/**
 * 身体部位DAO接口
 * @author zsm
 * @version 2017-01-22
 */
@MyBatisDao
public interface BodyPartDao extends CrudDao<BodyPart> {
	
}