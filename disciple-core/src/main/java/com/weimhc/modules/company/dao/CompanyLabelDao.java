/**
 * 
 */
package com.weimhc.modules.company.dao;

import java.util.List;
import java.util.Map;

import com.thinkgem.javamg.common.persistence.annotation.MyBatisDao;
import com.weimhc.framework.persistence.CrudDao;
import com.weimhc.modules.company.entity.CompanyLabel;
import org.apache.ibatis.annotations.Param;

/**
 * 企业标签DAO接口
 * @author lc
 * @version 2017-11-15
 */
@MyBatisDao
public interface CompanyLabelDao extends CrudDao<CompanyLabel> {
	public List<CompanyLabel> findCompanyLabel(CompanyLabel companyLabel);

    List<CompanyLabel> findCompanyLabelByCompanyId(String companyId);

    List<CompanyLabel> listByCompanyId(String companyId);

    void saveLabelList(@Param(value = "companyId") String companyId, @Param(value = "companyLabelId") String companyLabelId,@Param(value = "id") String id);
}