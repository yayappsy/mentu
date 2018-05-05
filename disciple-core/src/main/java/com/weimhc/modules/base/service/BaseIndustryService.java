/**
 * 
 */
package com.weimhc.modules.base.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.javamg.common.persistence.Page;
import com.weimhc.framework.service.impl.CrudServiceImpl;
import com.weimhc.modules.base.dao.BaseIndustryDao;
import com.weimhc.modules.base.entity.BaseIndustry;

/**
 * 行业Service
 * @author lc
 * @version 2016-06-03
 */
@Service("baseIndustryService")
@Transactional(readOnly = true)
public class BaseIndustryService extends CrudServiceImpl<BaseIndustryDao, BaseIndustry> {

	public BaseIndustry get(String id) {
		return super.get(id);
	}

	public long count(BaseIndustry industry) {
		return super.count(industry);
	}
	
	public boolean exists(String id) {
		return super.exists(id);
	}
	
	public List<BaseIndustry> findList(BaseIndustry industry) {
		return super.findList(industry);
	}
	
	public Page<BaseIndustry> findPage(Page<BaseIndustry> page, BaseIndustry industry) {
		return super.findPage(page, industry);
	}
	
	@Transactional(readOnly = false)
	public void save(BaseIndustry industry) {
		super.save(industry);
	}
	
	@Transactional(readOnly = false)
	public void deleteEntity(BaseIndustry industry) {
		super.deleteEntity(industry);
	}
	
	@Transactional(readOnly = false)
	public void deleteEntity(String... ids) {
		if (ids != null) {
			BaseIndustry industry = null;
			for (String id : ids) {
				industry = new BaseIndustry(id);
				deleteEntity(industry);
			}
		}
	}
	
}