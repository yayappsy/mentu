/**
 * 
 */
package com.weimhc.modules.property.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.javamg.common.persistence.Page;
import com.weimhc.framework.service.impl.CrudServiceImpl;
import com.weimhc.modules.property.dao.PropertyTemplateDao;
import com.weimhc.modules.property.entity.PropertyTemplate;

/**
 * 模板属性Service
 * @author zsm
 * @version 2017-03-29
 */
@Service
@Transactional(readOnly = true)
public class PropertyTemplateService extends CrudServiceImpl<PropertyTemplateDao, PropertyTemplate> {

	public PropertyTemplate get(String id) {
		return super.get(id);
	}

	public long count(PropertyTemplate propertyTemplate) {
		return super.count(propertyTemplate);
	}
	
	public boolean exists(String id) {
		return super.exists(id);
	}
	
	public List<PropertyTemplate> findList(PropertyTemplate propertyTemplate) {
		return super.findList(propertyTemplate);
	}
	
	public Page<PropertyTemplate> findPage(Page<PropertyTemplate> page, PropertyTemplate propertyTemplate) {
		return super.findPage(page, propertyTemplate);
	}
	
	@Transactional(readOnly = false)
	public void save(PropertyTemplate propertyTemplate) {
		super.save(propertyTemplate);
	}
	
	@Transactional(readOnly = false)
	public void deleteEntity(PropertyTemplate propertyTemplate) {
		super.deleteEntity(propertyTemplate);
	}
	
	@Transactional(readOnly = false)
	public void deleteEntity(String... ids) {
		if (ids != null) {
			PropertyTemplate propertyTemplate = null;
			for (String id : ids) {
				propertyTemplate = new PropertyTemplate(id);
				deleteEntity(propertyTemplate);
			}
		}
	}
	
}