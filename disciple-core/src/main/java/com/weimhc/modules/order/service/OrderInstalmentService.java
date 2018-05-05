/**
 * 
 */
package com.weimhc.modules.order.service;


import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.weimhc.modules.order.entity.OrderInstalment;
import com.weimhc.modules.order.dao.OrderInstalmentDao;

import com.weimhc.framework.service.impl.CrudServiceImpl;

/**
 * 分期订单Service
 * @author lc
 * @version 2017-06-14
 */
@Service
@Transactional(readOnly = true)
public class OrderInstalmentService extends CrudServiceImpl<OrderInstalmentDao, OrderInstalment> {

	
}