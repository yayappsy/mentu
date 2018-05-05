/**
 * 
 */
package com.weimhc.modules.coupon.dao;

import com.thinkgem.javamg.common.persistence.annotation.MyBatisDao;
import com.weimhc.modules.coupon.entity.Coupon;

import com.weimhc.framework.persistence.CrudDao;

/**
 * 优惠券DAO接口
 * @author lc
 * @version 2017-06-07
 */
@MyBatisDao
public interface CouponDao extends CrudDao<Coupon> {

}