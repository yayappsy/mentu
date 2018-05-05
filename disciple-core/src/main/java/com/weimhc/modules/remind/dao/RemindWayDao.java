/**
 * 
 */
package com.weimhc.modules.remind.dao;

import com.thinkgem.javamg.common.persistence.annotation.MyBatisDao;
import com.weimhc.framework.persistence.CrudDao;
import com.weimhc.modules.remind.entity.RemindWay;

/**
 * 提醒方式DAO接口
 * @author zsm
 * @version 2017-03-23
 */
@MyBatisDao
public interface RemindWayDao extends CrudDao<RemindWay> {
	
}