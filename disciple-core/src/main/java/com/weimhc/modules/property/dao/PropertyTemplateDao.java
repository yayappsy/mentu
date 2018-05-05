/**
 * 
 */
package com.weimhc.modules.property.dao;

import com.thinkgem.javamg.common.persistence.annotation.MyBatisDao;
import com.weimhc.framework.persistence.CrudDao;
import com.weimhc.modules.property.entity.PropertyTemplate;

/**
 * 模板属性DAO接口
 * @author zsm
 * @version 2017-03-29
 */
@MyBatisDao
public interface PropertyTemplateDao extends CrudDao<PropertyTemplate> {
	
}