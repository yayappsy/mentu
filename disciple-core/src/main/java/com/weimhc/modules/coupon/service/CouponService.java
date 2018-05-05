/**
 * 
 */
package com.weimhc.modules.coupon.service;


import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.weimhc.modules.coupon.entity.Coupon;
import com.weimhc.modules.coupon.dao.CouponDao;

import com.weimhc.framework.service.impl.CrudServiceImpl;

/**
 * 优惠券Service
 * @author lc
 * @version 2017-06-07
 */
@Service
@Transactional(readOnly = true)
public class CouponService extends CrudServiceImpl<CouponDao, Coupon> {

	
}