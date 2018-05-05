/**
 * 
 */
package com.weimhc.modules.recruit.dao;

import com.thinkgem.javamg.common.persistence.annotation.MyBatisDao;
import com.weimhc.framework.persistence.CrudDao;
import com.weimhc.modules.recruit.entity.Recruitment;

/**
 * 招聘DAO接口
 * @author zsm
 * @version 2017-03-27
 */
@MyBatisDao
public interface RecruitmentDao extends CrudDao<Recruitment> {
	
}