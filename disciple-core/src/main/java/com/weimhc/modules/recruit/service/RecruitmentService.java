/**
 * 
 */
package com.weimhc.modules.recruit.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.javamg.common.persistence.Page;
import com.weimhc.framework.service.impl.CrudServiceImpl;
import com.weimhc.modules.recruit.dao.RecruitmentDao;
import com.weimhc.modules.recruit.entity.Recruitment;

/**
 * 招聘Service
 * @author zsm
 * @version 2017-03-27
 */
@Service
@Transactional(readOnly = true)
public class RecruitmentService extends CrudServiceImpl<RecruitmentDao, Recruitment> {

	public Recruitment get(String id) {
		return super.get(id);
	}

	public long count(Recruitment recruitment) {
		return super.count(recruitment);
	}
	
	public boolean exists(String id) {
		return super.exists(id);
	}
	
	public List<Recruitment> findList(Recruitment recruitment) {
		return super.findList(recruitment);
	}
	
	public Page<Recruitment> findPage(Page<Recruitment> page, Recruitment recruitment) {
		return super.findPage(page, recruitment);
	}
	
	@Transactional(readOnly = false)
	public void save(Recruitment recruitment) {
		super.save(recruitment);
	}
	
	@Transactional(readOnly = false)
	public void deleteEntity(Recruitment recruitment) {
		super.deleteEntity(recruitment);
	}
	
	@Transactional(readOnly = false)
	public void deleteEntity(String... ids) {
		if (ids != null) {
			Recruitment recruitment = null;
			for (String id : ids) {
				recruitment = new Recruitment(id);
				deleteEntity(recruitment);
			}
		}
	}
	
}