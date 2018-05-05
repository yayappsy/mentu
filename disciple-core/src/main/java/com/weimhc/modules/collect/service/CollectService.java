/**
 * 
 */
package com.weimhc.modules.collect.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.javamg.common.persistence.Page;
import com.weimhc.modules.collect.entity.Collect;
import com.weimhc.framework.service.impl.CrudServiceImpl;
import com.weimhc.modules.collect.dao.CollectDao;

/**
 * 收藏Service
 * @author lc
 * @version 2017-11-15
 */
@Service
@Transactional(readOnly = true)
public class CollectService extends CrudServiceImpl<CollectDao, Collect> {

	public Collect get(String id) {
		return super.get(id);
	}

	public long count(Collect collect) {
		return super.count(collect);
	}
	
	public boolean exists(String id) {
		return super.exists(id);
	}
	
	public List<Collect> findList(Collect collect) {
		return super.findList(collect);
	}
	
	public Page<Collect> findPage(Page<Collect> page, Collect collect) {
		return super.findPage(page, collect);
	}
	
	@Transactional(readOnly = false)
	public void save(Collect collect) {
		super.save(collect);
	}
	
	@Transactional(readOnly = false)
	public void delete(Collect collect) {
		super.delete(collect);
	}
	
}