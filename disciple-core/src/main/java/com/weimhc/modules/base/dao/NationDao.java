/**
 * 
 */
package com.weimhc.modules.base.dao;

import com.thinkgem.javamg.common.persistence.annotation.MyBatisDao;
import com.weimhc.framework.persistence.CrudDao;
import com.weimhc.modules.base.entity.Nation;

/**
 * 民族DAO接口
 * @author lc
 * @version 2017-01-05
 */
@MyBatisDao
public interface NationDao extends CrudDao<Nation> {
	
}