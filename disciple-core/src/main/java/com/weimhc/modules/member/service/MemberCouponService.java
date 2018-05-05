/**
 * 
 */
package com.weimhc.modules.member.service;


import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.weimhc.modules.member.entity.MemberCoupon;
import com.weimhc.modules.member.dao.MemberCouponDao;

import com.weimhc.framework.service.impl.CrudServiceImpl;

/**
 * 学员优惠券Service
 * @author lc
 * @version 2017-06-07
 */
@Service
@Transactional(readOnly = true)
public class MemberCouponService extends CrudServiceImpl<MemberCouponDao, MemberCoupon> {

	
}