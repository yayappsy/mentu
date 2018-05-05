/**
 * 
 */
package com.weimhc.modules.recruit.dao;

import com.thinkgem.javamg.common.persistence.annotation.MyBatisDao;
import com.weimhc.framework.persistence.SortableDao;
import com.weimhc.modules.recruit.entity.RecruitmentType;

/**
 * 职位类型DAO接口
 * @author lc
 * @version 2017-04-06
 */
@MyBatisDao
public interface RecruitmentTypeDao extends SortableDao<RecruitmentType> {
	
}