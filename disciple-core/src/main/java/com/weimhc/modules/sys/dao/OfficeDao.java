/**
 * 
 */
package com.weimhc.modules.sys.dao;

import com.thinkgem.javamg.common.persistence.TreeDao;
import com.thinkgem.javamg.common.persistence.annotation.MyBatisDao;
import com.weimhc.modules.sys.entity.Office;

/**
 * 机构DAO接口
 * 
 * @version 2014-05-16
 */
@MyBatisDao
public interface OfficeDao extends TreeDao<Office> {
	
}
