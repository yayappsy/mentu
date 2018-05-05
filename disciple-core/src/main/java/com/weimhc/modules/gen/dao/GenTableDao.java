/**
 * 
 */
package com.weimhc.modules.gen.dao;

import com.thinkgem.javamg.common.persistence.annotation.MyBatisDao;
import com.weimhc.framework.persistence.CrudDao;
import com.weimhc.modules.gen.entity.GenTable;

/**
 * 业务表DAO接口
 * 
 * @version 2013-10-15
 */
@MyBatisDao
public interface GenTableDao extends CrudDao<GenTable> {

}
