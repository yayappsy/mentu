/**
 * 
 */
package com.weimhc.modules.member.dao;

import com.thinkgem.javamg.common.persistence.annotation.MyBatisDao;
import com.weimhc.modules.member.entity.MemberCoupon;

import com.weimhc.framework.persistence.CrudDao;

/**
 * 学员优惠券DAO接口
 * @author lc
 * @version 2017-06-07
 */
@MyBatisDao
public interface MemberCouponDao extends CrudDao<MemberCoupon> {

}