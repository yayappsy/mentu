/**
 * 
 */
package com.weimhc.modules.base.dao;

import com.thinkgem.javamg.common.persistence.annotation.MyBatisDao;
import com.weimhc.framework.persistence.CrudDao;
import com.weimhc.modules.base.entity.SearchEngine;

/**
 * 搜索引擎DAO接口
 * 
 * @author zsm
 * @version 2017-04-11
 */
@MyBatisDao
public interface SearchEngineDao extends CrudDao<SearchEngine> {

}