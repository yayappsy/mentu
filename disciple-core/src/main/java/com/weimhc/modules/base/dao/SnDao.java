/**
 * 
 */
package com.weimhc.modules.base.dao;

import com.thinkgem.javamg.common.persistence.annotation.MyBatisDao;
import com.weimhc.framework.persistence.CrudDao;
import com.weimhc.modules.base.entity.Sn;
import com.weimhc.modules.base.entity.SnType;

/**
 * 编号DAO接口
 * 
 * @author zsm
 * @version 2016-02-01
 */
@MyBatisDao
public interface SnDao extends CrudDao<Sn> {

	/**
	 * 生成编号
	 * 
	 * @param type
	 * @return
	 */
	String generate(SnType type);

}