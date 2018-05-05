/**
 * 
 */
package com.weimhc.modules.remind.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.weimhc.framework.service.impl.SortableServiceImpl;
import com.weimhc.modules.remind.dao.RemindParameterDao;
import com.weimhc.modules.remind.entity.RemindParameter;

/**
 * 模板可选参数Service
 * @author zsm
 * @version 2017-04-14
 */
@Service
@Transactional(readOnly = true)
public class RemindParameterService extends SortableServiceImpl<RemindParameterDao, RemindParameter> {

	
}