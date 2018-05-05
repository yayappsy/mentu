/**
 * 
 */
package com.weimhc.modules.base.dao;

import com.thinkgem.javamg.common.persistence.annotation.MyBatisDao;
import com.weimhc.framework.persistence.CrudDao;
import com.weimhc.modules.base.entity.Region;

/**
 * 全国行政区DAO接口
 * @author zsm
 * @version 2016-10-05
 */
@MyBatisDao
public interface RegionDao extends CrudDao<Region> {
	
}