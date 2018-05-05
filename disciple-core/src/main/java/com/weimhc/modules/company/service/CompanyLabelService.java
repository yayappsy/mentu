/**
 * 
 */
package com.weimhc.modules.company.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.javamg.common.persistence.Page;
import com.weimhc.modules.company.entity.CompanyLabel;
import com.weimhc.framework.service.impl.CrudServiceImpl;
import com.weimhc.modules.company.dao.CompanyLabelDao;

/**
 * 企业标签Service
 * @author lc
 * @version 2017-11-15
 */
@Service
@Transactional(readOnly = true)
public class CompanyLabelService extends CrudServiceImpl<CompanyLabelDao, CompanyLabel> {

	public CompanyLabel get(String id) {
		return super.get(id);
	}

	public long count(CompanyLabel companyLabel) {
		return super.count(companyLabel);
	}
	
	public boolean exists(String id) {
		return super.exists(id);
	}
	
	public List<CompanyLabel> findList(CompanyLabel companyLabel) {
		return super.findList(companyLabel);
	}
	
	public Page<CompanyLabel> findPage(Page<CompanyLabel> page, CompanyLabel companyLabel) {
		return super.findPage(page, companyLabel);
	}
	
	@Transactional(readOnly = false)
	public void save(CompanyLabel companyLabel) {
		super.save(companyLabel);
	}
	
	@Transactional(readOnly = false)
	public void delete(CompanyLabel companyLabel) {
		super.delete(companyLabel);
	}
	
	public List<CompanyLabel> findCompanyLabel(CompanyLabel companyLabel){
		return dao.findCompanyLabel(companyLabel);
	}

	public Map<String, String> getLabels(String companyId) {
		Map<String, String> map = new HashMap<>();
		List<CompanyLabel> companyLabels = dao.findCompanyLabelByCompanyId(companyId);
		for (CompanyLabel companyLabel : companyLabels) {
			map.put(companyLabel.getId(), companyLabel.getName());
		}
		return map;
	}


	public List<String> getCompanyWeal(String companyId) {
		List<String> weals = new ArrayList<>();
		List<CompanyLabel> companyLabels = dao.listByCompanyId(companyId);
		for (CompanyLabel temp : companyLabels) {
			weals.add(temp.getName());
		}
		return weals;
	}

	public Map<String, String> getCompanyLabel(String companyId) {
		Map<String, String> map = new HashMap<>();
		List<CompanyLabel> companyLabels = dao.listByCompanyId(companyId);
		for (CompanyLabel temp : companyLabels) {
			map.put(temp.getId(), temp.getName());
		}
		return map;
	}
}