/**
 * 
 */
package com.weimhc.modules.base.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.javamg.common.persistence.Page;
import com.weimhc.framework.service.impl.CrudServiceImpl;
import com.weimhc.modules.base.dao.EducationDao;
import com.weimhc.modules.base.entity.Education;

/**
 * 学历Service
 * @author lc
 * @version 2016-06-24
 */
@Service
@Transactional(readOnly = true)
public class EducationService extends CrudServiceImpl<EducationDao, Education> {

	public Education get(String id) {
		return super.get(id);
	}

	public long count(Education education) {
		return super.count(education);
	}
	
	public boolean exists(String id) {
		return super.exists(id);
	}
	
	public List<Education> findList(Education education) {
		return super.findList(education);
	}
	
	public Page<Education> findPage(Page<Education> page, Education education) {
		return super.findPage(page, education);
	}
	
	@Transactional(readOnly = false)
	public void save(Education education) {
		super.save(education);
	}
	
	@Transactional(readOnly = false)
	public void deleteEntity(Education education) {
		super.deleteEntity(education);
	}
	
	@Transactional(readOnly = false)
	public void deleteEntity(String... ids) {
		if (ids != null) {
			Education education = null;
			for (String id : ids) {
				education = new Education(id);
				deleteEntity(education);
			}
		}
	}
	
}