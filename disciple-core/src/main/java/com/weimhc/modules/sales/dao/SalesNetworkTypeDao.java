/**
 * 
 */
package com.weimhc.modules.sales.dao;

import com.thinkgem.javamg.common.persistence.annotation.MyBatisDao;
import com.weimhc.modules.sales.entity.SalesNetworkType;

import com.weimhc.framework.persistence.SortableDao;

/**
 * 营业网点类型DAO接口
 * @author zsm
 * @version 2017-04-28
 */
@MyBatisDao
public interface SalesNetworkTypeDao extends SortableDao<SalesNetworkType> {
	
}