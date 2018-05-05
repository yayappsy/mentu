/**
 * 
 */
package com.weimhc.modules.recruit.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.weimhc.framework.service.impl.SortableServiceImpl;
import com.weimhc.modules.recruit.dao.RecruitmentTypeDao;
import com.weimhc.modules.recruit.entity.RecruitmentType;

/**
 * 职位类型Service
 * @author lc
 * @version 2017-04-06
 */
@Service
@Transactional(readOnly = true)
public class RecruitmentTypeService extends SortableServiceImpl<RecruitmentTypeDao, RecruitmentType> {

	
}