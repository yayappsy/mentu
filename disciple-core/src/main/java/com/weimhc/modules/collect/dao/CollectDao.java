/**
 * 
 */
package com.weimhc.modules.collect.dao;


import com.thinkgem.javamg.common.persistence.annotation.MyBatisDao;
import com.weimhc.framework.persistence.CrudDao;
import com.weimhc.modules.collect.entity.Collect;

/**
 * 收藏DAO接口
 * @author lc
 * @version 2017-11-15
 */
@MyBatisDao
public interface CollectDao extends CrudDao<Collect> {
	
}