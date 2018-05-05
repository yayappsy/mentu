/**
 * 
 */
package com.weimhc.modules.sales.dao;

import com.thinkgem.javamg.common.persistence.annotation.MyBatisDao;
import com.weimhc.framework.persistence.TreeDao;
import com.weimhc.modules.sales.entity.SalesNetwork;

/**
 * 营业网点DAO接口
 * @author zsm
 * @version 2017-04-28
 */
@MyBatisDao
public interface SalesNetworkDao extends TreeDao<SalesNetwork> {
	
}