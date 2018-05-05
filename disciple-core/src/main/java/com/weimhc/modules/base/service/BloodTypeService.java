/**
 * 
 */
package com.weimhc.modules.base.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.javamg.common.persistence.Page;
import com.weimhc.framework.service.impl.CrudServiceImpl;
import com.weimhc.modules.base.dao.BloodTypeDao;
import com.weimhc.modules.base.entity.BloodType;

/**
 * 血型Service
 * @author lc
 * @version 2016-06-24
 */
@Service
@Transactional(readOnly = true)
public class BloodTypeService extends CrudServiceImpl<BloodTypeDao, BloodType> {

	public BloodType get(String id) {
		return super.get(id);
	}

	public long count(BloodType bloodType) {
		return super.count(bloodType);
	}
	
	public boolean exists(String id) {
		return super.exists(id);
	}
	
	public List<BloodType> findList(BloodType bloodType) {
		return super.findList(bloodType);
	}
	
	public Page<BloodType> findPage(Page<BloodType> page, BloodType bloodType) {
		return super.findPage(page, bloodType);
	}
	
	@Transactional(readOnly = false)
	public void save(BloodType bloodType) {
		super.save(bloodType);
	}
	
	@Transactional(readOnly = false)
	public void deleteEntity(BloodType bloodType) {
		super.deleteEntity(bloodType);
	}
	
	@Transactional(readOnly = false)
	public void deleteEntity(String... ids) {
		if (ids != null) {
			BloodType bloodType = null;
			for (String id : ids) {
				bloodType = new BloodType(id);
				deleteEntity(bloodType);
			}
		}
	}
	
}