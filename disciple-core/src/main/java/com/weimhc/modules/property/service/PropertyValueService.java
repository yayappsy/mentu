/**
 * 
 */
package com.weimhc.modules.property.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.javamg.common.persistence.Page;
import com.weimhc.framework.service.impl.CrudServiceImpl;
import com.weimhc.modules.property.dao.PropertyValueDao;
import com.weimhc.modules.property.entity.PropertyValue;

/**
 * 扩展属性选项Service
 * @author zsm
 * @version 2017-03-29
 */
@Service
@Transactional(readOnly = true)
public class PropertyValueService extends CrudServiceImpl<PropertyValueDao, PropertyValue> {

	public PropertyValue get(String id) {
		return super.get(id);
	}

	public long count(PropertyValue propertyValue) {
		return super.count(propertyValue);
	}
	
	public boolean exists(String id) {
		return super.exists(id);
	}
	
	public List<PropertyValue> findList(PropertyValue propertyValue) {
		return super.findList(propertyValue);
	}
	
	public Page<PropertyValue> findPage(Page<PropertyValue> page, PropertyValue propertyValue) {
		return super.findPage(page, propertyValue);
	}
	
	@Transactional(readOnly = false)
	public void save(PropertyValue propertyValue) {
		super.save(propertyValue);
	}
	
	@Transactional(readOnly = false)
	public void deleteEntity(PropertyValue propertyValue) {
		super.deleteEntity(propertyValue);
	}
	
	@Transactional(readOnly = false)
	public void deleteEntity(String... ids) {
		if (ids != null) {
			PropertyValue propertyValue = null;
			for (String id : ids) {
				propertyValue = new PropertyValue(id);
				deleteEntity(propertyValue);
			}
		}
	}
	
}