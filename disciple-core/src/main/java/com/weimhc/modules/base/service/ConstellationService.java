/**
 * 
 */
package com.weimhc.modules.base.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.javamg.common.persistence.Page;
import com.weimhc.framework.service.impl.CrudServiceImpl;
import com.weimhc.modules.base.dao.ConstellationDao;
import com.weimhc.modules.base.entity.Constellation;

/**
 * 星座Service
 * @author lc
 * @version 2016-06-24
 */
@Service
@Transactional(readOnly = true)
public class ConstellationService extends CrudServiceImpl<ConstellationDao, Constellation> {

	public Constellation get(String id) {
		return super.get(id);
	}

	public long count(Constellation constellation) {
		return super.count(constellation);
	}
	
	public boolean exists(String id) {
		return super.exists(id);
	}
	
	public List<Constellation> findList(Constellation constellation) {
		return super.findList(constellation);
	}
	
	public Page<Constellation> findPage(Page<Constellation> page, Constellation constellation) {
		return super.findPage(page, constellation);
	}
	
	@Transactional(readOnly = false)
	public void save(Constellation constellation) {
		super.save(constellation);
	}
	
	@Transactional(readOnly = false)
	public void deleteEntity(Constellation constellation) {
		super.deleteEntity(constellation);
	}
	
	@Transactional(readOnly = false)
	public void deleteEntity(String... ids) {
		if (ids != null) {
			Constellation constellation = null;
			for (String id : ids) {
				constellation = new Constellation(id);
				deleteEntity(constellation);
			}
		}
	}
	
}