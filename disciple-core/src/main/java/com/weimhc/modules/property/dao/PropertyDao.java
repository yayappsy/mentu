/**
 * 
 */
package com.weimhc.modules.property.dao;

import com.thinkgem.javamg.common.persistence.annotation.MyBatisDao;
import com.weimhc.framework.persistence.SortableDao;
import com.weimhc.modules.property.entity.Property;

/**
 * 扩展属性DAO接口
 * 
 * @author zsm
 * @version 2017-03-29
 */
@MyBatisDao
public interface PropertyDao extends SortableDao<Property> {
	/**
	 * 维护属性与扩展属性选项之间的关系
	 * 
	 * @param role
	 * @return
	 */
	public int deletePropertyValues(Property property);

	public int insertPropertyValues(Property property);
}