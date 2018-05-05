/**
 * 
 */
package com.weimhc.modules.gen.dao;

import com.thinkgem.javamg.common.persistence.annotation.MyBatisDao;
import com.weimhc.framework.persistence.CrudDao;
import com.weimhc.modules.gen.entity.GenTable;
import com.weimhc.modules.gen.entity.GenTableColumn;

/**
 * 业务表字段DAO接口
 * 
 * @version 2013-10-15
 */
@MyBatisDao
public interface GenTableColumnDao extends CrudDao<GenTableColumn> {

	public void deleteByGenTableId(GenTable genTable);
}
