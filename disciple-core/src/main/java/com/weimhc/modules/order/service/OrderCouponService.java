/**
 * 
 */
package com.weimhc.modules.order.service;


import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.weimhc.modules.order.entity.OrderCoupon;
import com.weimhc.modules.order.dao.OrderCouponDao;

import com.weimhc.framework.service.impl.CrudServiceImpl;

/**
 * 订单优惠券Service
 * @author lc
 * @version 2017-06-22
 */
@Service
@Transactional(readOnly = true)
public class OrderCouponService extends CrudServiceImpl<OrderCouponDao, OrderCoupon> {

	
}