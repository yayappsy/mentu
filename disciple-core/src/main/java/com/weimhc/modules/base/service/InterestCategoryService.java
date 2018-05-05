/**
 * 
 */
package com.weimhc.modules.base.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.weimhc.framework.service.impl.SortableServiceImpl;
import com.weimhc.modules.base.dao.InterestCategoryDao;
import com.weimhc.modules.base.entity.InterestCategory;

/**
 * 兴趣 Service
 * 
 * @author lc
 * @version 2017-06-12
 */
@Service
@Transactional(readOnly = true)
public class InterestCategoryService
		extends SortableServiceImpl<InterestCategoryDao, InterestCategory> {

}