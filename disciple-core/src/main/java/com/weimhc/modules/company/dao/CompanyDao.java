/**
 * 
 */
package com.weimhc.modules.company.dao;

import com.thinkgem.javamg.common.persistence.annotation.MyBatisDao;
import com.weimhc.framework.persistence.CrudDao;
import com.weimhc.modules.company.entity.Company;
import com.weimhc.modules.sys.entity.User;

import java.util.List;

/**
 * 企业DAO接口
 * @author lc
 * @version 2017-11-13
 */
@MyBatisDao
public interface CompanyDao extends CrudDao<Company> {
	/**
	 * 删除企业标签关联数据
	 * 
	 * @param user
	 * @return
	 */
	public int deleteCompanyLabel(Company company);

	/**
	 * 插入企业标签关联数据
	 * 
	 * @param user
	 * @return
	 */
	public int insertCompanyLabel(Company company);

    String isPass(String id);

    String getCompanyId(String id);

    Integer setAuditing(String companyId);

	void updateProofData(Company company);

}