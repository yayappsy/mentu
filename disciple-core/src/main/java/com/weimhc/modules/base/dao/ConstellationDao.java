/**
 * 
 */
package com.weimhc.modules.base.dao;

import com.thinkgem.javamg.common.persistence.annotation.MyBatisDao;
import com.weimhc.framework.persistence.CrudDao;
import com.weimhc.modules.base.entity.Constellation;

/**
 * 星座DAO接口
 * @author lc
 * @version 2016-06-24
 */
@MyBatisDao
public interface ConstellationDao extends CrudDao<Constellation> {
	
}