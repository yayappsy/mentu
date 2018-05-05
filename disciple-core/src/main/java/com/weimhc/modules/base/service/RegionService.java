/**
 * 
 */
package com.weimhc.modules.base.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.javamg.common.persistence.Page;
import com.weimhc.framework.service.impl.CrudServiceImpl;
import com.weimhc.modules.base.dao.RegionDao;
import com.weimhc.modules.base.entity.Region;

/**
 * 全国行政区Service
 * @author zsm
 * @version 2016-10-05
 */
@Service
@Transactional(readOnly = true)
public class RegionService extends CrudServiceImpl<RegionDao, Region> {

	public Region get(String id) {
		return super.get(id);
	}

	public long count(Region region) {
		return super.count(region);
	}
	
	public boolean exists(String id) {
		return super.exists(id);
	}
	
	public List<Region> findList(Region region) {
		return super.findList(region);
	}
	
	public Page<Region> findPage(Page<Region> page, Region region) {
		return super.findPage(page, region);
	}
	
	@Transactional(readOnly = false)
	public void save(Region region) {
		super.save(region);
	}
	
	@Transactional(readOnly = false)
	public void deleteEntity(Region region) {
		super.deleteEntity(region);
	}
	
	@Transactional(readOnly = false)
	public void deleteEntity(String... ids) {
		if (ids != null) {
			Region region = null;
			for (String id : ids) {
				region = new Region(id);
				deleteEntity(region);
			}
		}
	}
	
}