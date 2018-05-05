/**
 * 
 */
package com.weimhc.modules.base.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.javamg.common.persistence.Page;
import com.weimhc.framework.service.impl.CrudServiceImpl;
import com.weimhc.modules.base.dao.BodyPartItemDao;
import com.weimhc.modules.base.entity.BodyPartItem;

/**
 * 身体部位细节Service
 * @author zsm
 * @version 2017-01-22
 */
@Service
@Transactional(readOnly = true)
public class BodyPartItemService extends CrudServiceImpl<BodyPartItemDao, BodyPartItem> {

	public BodyPartItem get(String id) {
		return super.get(id);
	}

	public long count(BodyPartItem bodyPartItem) {
		return super.count(bodyPartItem);
	}
	
	public boolean exists(String id) {
		return super.exists(id);
	}
	
	public List<BodyPartItem> findList(BodyPartItem bodyPartItem) {
		return super.findList(bodyPartItem);
	}
	
	public Page<BodyPartItem> findPage(Page<BodyPartItem> page, BodyPartItem bodyPartItem) {
		return super.findPage(page, bodyPartItem);
	}
	
	@Transactional(readOnly = false)
	public void save(BodyPartItem bodyPartItem) {
		super.save(bodyPartItem);
	}
	
	@Transactional(readOnly = false)
	public void deleteEntity(BodyPartItem bodyPartItem) {
		super.deleteEntity(bodyPartItem);
	}
	
	@Transactional(readOnly = false)
	public void deleteEntity(String... ids) {
		if (ids != null) {
			BodyPartItem bodyPartItem = null;
			for (String id : ids) {
				bodyPartItem = new BodyPartItem(id);
				deleteEntity(bodyPartItem);
			}
		}
	}
	
}