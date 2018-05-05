/**
 * 
 */
package com.weimhc.modules.base.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.javamg.common.persistence.Page;
import com.weimhc.framework.service.impl.CrudServiceImpl;
import com.weimhc.modules.base.dao.BodyCategoryDao;
import com.weimhc.modules.base.entity.BodyCategory;

/**
 * 身体部位Service
 * 
 * @author zsm
 * @version 2017-01-22
 */
@Service
@Transactional(readOnly = true)
public class BodyCategoryService extends CrudServiceImpl<BodyCategoryDao, BodyCategory> {

	@Override
	public BodyCategory get(String id) {
		return super.get(id);
	}

	@Override
	public long count(BodyCategory bodyPart) {
		return super.count(bodyPart);
	}

	@Override
	public boolean exists(String id) {
		return super.exists(id);
	}

	@Override
	public List<BodyCategory> findList(BodyCategory bodyPart) {
		return super.findList(bodyPart);
	}

	@Override
	public Page<BodyCategory> findPage(Page<BodyCategory> page, BodyCategory bodyPart) {
		return super.findPage(page, bodyPart);
	}

	@Override
	@Transactional(readOnly = false)
	public void save(BodyCategory bodyPart) {
		super.save(bodyPart);
	}

	@Override
	@Transactional(readOnly = false)
	public void deleteEntity(BodyCategory bodyPart) {
		super.deleteEntity(bodyPart);
	}

	@Override
	@Transactional(readOnly = false)
	public void deleteEntity(String... ids) {
		if (ids != null) {
			BodyCategory bodyPart = null;
			for (String id : ids) {
				bodyPart = new BodyCategory(id);
				deleteEntity(bodyPart);
			}
		}
	}

}