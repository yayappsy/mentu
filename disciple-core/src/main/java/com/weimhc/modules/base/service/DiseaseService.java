/**
 * 
 */
package com.weimhc.modules.base.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.javamg.common.persistence.Page;
import com.weimhc.framework.service.impl.CrudServiceImpl;
import com.weimhc.modules.base.dao.DiseaseDao;
import com.weimhc.modules.base.entity.Disease;

/**
 * 疾病Service
 * @author lc
 * @version 2017-01-06
 */
@Service
@Transactional(readOnly = true)
public class DiseaseService extends CrudServiceImpl<DiseaseDao, Disease> {

	public Disease get(String id) {
		return super.get(id);
	}

	public long count(Disease disease) {
		return super.count(disease);
	}
	
	public boolean exists(String id) {
		return super.exists(id);
	}
	
	public List<Disease> findList(Disease disease) {
		return super.findList(disease);
	}
	
	public Page<Disease> findPage(Page<Disease> page, Disease disease) {
		return super.findPage(page, disease);
	}
	
	@Transactional(readOnly = false)
	public void save(Disease disease) {
		super.save(disease);
	}
	
	@Transactional(readOnly = false)
	public void deleteEntity(Disease disease) {
		super.deleteEntity(disease);
	}
	
	@Transactional(readOnly = false)
	public void deleteEntity(String... ids) {
		if (ids != null) {
			Disease disease = null;
			for (String id : ids) {
				disease = new Disease(id);
				deleteEntity(disease);
			}
		}
	}
	
}