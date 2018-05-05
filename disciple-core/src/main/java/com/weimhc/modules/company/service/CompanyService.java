/**
 * 
 */
package com.weimhc.modules.company.service;

import java.util.List;

import com.weimhc.modules.user.dao.UserInfoDao;
import com.weimhc.modules.user.entity.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.javamg.common.persistence.Page;
import com.thinkgem.javamg.common.service.ServiceException;
import com.thinkgem.javamg.common.utils.StringUtils;
import com.weimhc.modules.company.entity.Company;
import com.weimhc.modules.sys.entity.User;
import com.weimhc.modules.sys.utils.UserUtils;
import com.weimhc.modules.user.entity.BusinessSystem;
import com.weimhc.modules.user.entity.IdentityType;
import com.weimhc.framework.service.impl.CrudServiceImpl;
import com.weimhc.modules.company.dao.CompanyDao;

/**
 * 企业Service
 * @author lc
 * @version 2017-11-13
 */
@Service
@Transactional(readOnly = true)
public class CompanyService extends CrudServiceImpl<CompanyDao, Company> {

	@Autowired
	private UserInfoDao userInfoDao;

	public Company get(String id) {
		return super.get(id);
	}

	public long count(Company company) {
		return super.count(company);
	}
	
	public boolean exists(String id) {
		return super.exists(id);
	}
	
	public List<Company> findList(Company company) {
		return super.findList(company);
	}
	
	public Page<Company> findPage(Page<Company> page, Company company) {
		return super.findPage(page, company);
	}
	
	@Transactional(readOnly = false)
	public void save(Company company) {
		super.save(company);
	}
	

	/**
	 * 更新企业信息
	 * 
	 * @param company
	 */
	@Transactional(readOnly = false)
	public void saveCompany(Company company) {
		save(company);
/*		if (StringUtils.isNotBlank(company.getId())) {
			// 更新企业与标签关联
			dao.deleteCompanyLabel(company);
			if (company.getCompanyLabelList() != null && company.getCompanyLabelList().size() > 0) {
				dao.insertCompanyLabel(company);
			} else {
				throw new ServiceException(company.getName() + "没有设置标签！");
			}
		}*/
	}

	
	@Transactional(readOnly = false)
	public void delete(Company company) {
		super.delete(company);
	}

	@Transactional(readOnly = false)
	public void bindPhone(String id, String phone) {
		UserInfo userInfo = userInfoDao.get(id);
		userInfo.setMobile(phone);
		userInfoDao.update(userInfo);
	}

	@Transactional(readOnly = false)
	public Integer setAuditing(String companyId) {
		return dao.setAuditing(companyId);
	}

	@Transactional(readOnly = false)
	public void updateProofData(Company company) {
		dao.updateProofData(company);
	}

	public List<Company> getByName(String name) {
		Company company = new Company();
		company.setName(name);
		return dao.findList(company);
	}
}