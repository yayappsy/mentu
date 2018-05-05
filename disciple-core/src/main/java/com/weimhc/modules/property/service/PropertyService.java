/**
 * 
 */
package com.weimhc.modules.property.service;

import java.util.List;
import java.util.stream.Stream;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.javamg.common.persistence.Page;
import com.weimhc.framework.service.impl.SortableServiceImpl;
import com.weimhc.framework.utils.ConstantUtils;
import com.weimhc.modules.property.dao.PropertyDao;
import com.weimhc.modules.property.dao.PropertyValueDao;
import com.weimhc.modules.property.entity.Property;
import com.weimhc.modules.property.entity.PropertyValue;

/**
 * 扩展属性Service
 * 
 * @author zsm
 * @version 2017-03-29
 */
@Service
@Transactional(readOnly = true)
public class PropertyService
		extends SortableServiceImpl<PropertyDao, Property> {

	@Autowired
	private PropertyValueDao propertyValueDao;

	@Override
	public Property get(String id) {
		Property property = super.get(id);
		if (property != null) {
			property.setPropertyValueList(
					propertyValueDao.findAllList(new PropertyValue(property)));
		}
		return property;
	}

	@Override
	public long count(Property property) {
		return super.count(property);
	}

	@Override
	public boolean exists(String id) {
		return super.exists(id);
	}

	@Override
	public List<Property> findList(Property property) {
		return super.findList(property);
	}

	@Override
	public Page<Property> findPage(Page<Property> page, Property property) {
		return super.findPage(page, property);
	}

	@Override
	@Transactional(readOnly = false)
	public void save(Property property) {
		if (property.getShowType().getHasOptions()) {
			dao.deletePropertyValues(property);
			Stream<String> names = property.getPropertyValueList().stream()
					.map(pv -> {
						if (pv.getIsNewRecord()) {
							pv.preInsert();
						}
						return pv.getName();
					});
			property.setPropertyValues(StringUtils.join(names.toArray(),
					ConstantUtils.SEPARATOR_COMMA));
		} else {
			property.setPropertyValues("");
		}
		super.save(property);
		dao.insertPropertyValues(property);
	}

	@Override
	@Transactional(readOnly = false)
	public void deleteEntity(Property property) {
		super.deleteEntity(property);
	}

	@Override
	@Transactional(readOnly = false)
	public void deleteEntity(String... ids) {
		if (ids != null) {
			Property property = null;
			for (String id : ids) {
				property = new Property(id);
				deleteEntity(property);
			}
		}
	}

}