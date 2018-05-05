/**
 * 
 */
package com.weimhc.modules.remind.dao;

import com.thinkgem.javamg.common.persistence.annotation.MyBatisDao;
import com.weimhc.framework.persistence.SortableDao;
import com.weimhc.modules.remind.entity.RemindParameter;

/**
 * 模板可选参数DAO接口
 * @author zsm
 * @version 2017-04-14
 */
@MyBatisDao
public interface RemindParameterDao extends SortableDao<RemindParameter> {
	
}