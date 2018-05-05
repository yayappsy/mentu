/**
 * 
 */
package com.weimhc.modules.base.service;


import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.weimhc.modules.base.entity.OnlineCustomerService;
import com.weimhc.modules.base.dao.OnlineCustomerServiceDao;

import com.weimhc.framework.service.impl.SortableServiceImpl;

/**
 * 在线客服Service
 * @author lc
 * @version 2017-07-03
 */
@Service
@Transactional(readOnly = true)
public class OnlineCustomerServiceService extends SortableServiceImpl<OnlineCustomerServiceDao, OnlineCustomerService> {

	
}