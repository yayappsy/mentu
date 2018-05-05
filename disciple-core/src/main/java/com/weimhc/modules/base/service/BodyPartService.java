/**
 * 
 */
package com.weimhc.modules.base.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.javamg.common.persistence.Page;
import com.weimhc.framework.service.impl.CrudServiceImpl;
import com.weimhc.modules.base.dao.BodyPartDao;
import com.weimhc.modules.base.entity.BodyPart;

/**
 * 身体部位Service
 * @author zsm
 * @version 2017-01-22
 */
@Service
@Transactional(readOnly = true)
public class BodyPartService extends CrudServiceImpl<BodyPartDao, BodyPart> {

	public BodyPart get(String id) {
		return super.get(id);
	}

	public long count(BodyPart bodyPart) {
		return super.count(bodyPart);
	}
	
	public boolean exists(String id) {
		return super.exists(id);
	}
	
	public List<BodyPart> findList(BodyPart bodyPart) {
		return super.findList(bodyPart);
	}
	
	public Page<BodyPart> findPage(Page<BodyPart> page, BodyPart bodyPart) {
		return super.findPage(page, bodyPart);
	}
	
	@Transactional(readOnly = false)
	public void save(BodyPart bodyPart) {
		super.save(bodyPart);
	}
	
	@Transactional(readOnly = false)
	public void deleteEntity(BodyPart bodyPart) {
		super.deleteEntity(bodyPart);
	}
	
	@Transactional(readOnly = false)
	public void deleteEntity(String... ids) {
		if (ids != null) {
			BodyPart bodyPart = null;
			for (String id : ids) {
				bodyPart = new BodyPart(id);
				deleteEntity(bodyPart);
			}
		}
	}
	
}