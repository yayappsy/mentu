/**
 * 
 */
package com.weimhc.modules.base.dao;

import com.thinkgem.javamg.common.persistence.annotation.MyBatisDao;
import com.weimhc.framework.persistence.SortableDao;
import com.weimhc.modules.base.entity.Department;

/**
 * 工作部门DAO接口
 * @author lc
 * @version 2017-04-06
 */
@MyBatisDao
public interface DepartmentDao extends SortableDao<Department> {
	
}