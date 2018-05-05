/**
 * 
 */
package com.weimhc.modules.base.dao;

import com.thinkgem.javamg.common.persistence.annotation.MyBatisDao;
import com.weimhc.framework.persistence.CrudDao;
import com.weimhc.modules.base.entity.BaseIndustry;

/**
 * 行业DAO接口
 * @author lc
 * @version 2016-06-03
 */
@MyBatisDao
public interface BaseIndustryDao extends CrudDao<BaseIndustry> {
	
}