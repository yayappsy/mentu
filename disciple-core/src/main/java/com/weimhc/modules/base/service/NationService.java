/**
 * 
 */
package com.weimhc.modules.base.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.javamg.common.persistence.Page;
import com.weimhc.framework.service.impl.CrudServiceImpl;
import com.weimhc.modules.base.dao.NationDao;
import com.weimhc.modules.base.entity.Nation;

/**
 * 民族Service
 * @author lc
 * @version 2017-01-05
 */
@Service
@Transactional(readOnly = true)
public class NationService extends CrudServiceImpl<NationDao, Nation> {

	public Nation get(String id) {
		return super.get(id);
	}

	public long count(Nation nation) {
		return super.count(nation);
	}
	
	public boolean exists(String id) {
		return super.exists(id);
	}
	
	public List<Nation> findList(Nation nation) {
		return super.findList(nation);
	}
	
	public Page<Nation> findPage(Page<Nation> page, Nation nation) {
		return super.findPage(page, nation);
	}
	
	@Transactional(readOnly = false)
	public void save(Nation nation) {
		super.save(nation);
	}
	
	@Transactional(readOnly = false)
	public void deleteEntity(Nation nation) {
		super.deleteEntity(nation);
	}
	
	@Transactional(readOnly = false)
	public void deleteEntity(String... ids) {
		if (ids != null) {
			Nation nation = null;
			for (String id : ids) {
				nation = new Nation(id);
				deleteEntity(nation);
			}
		}
	}
	
}