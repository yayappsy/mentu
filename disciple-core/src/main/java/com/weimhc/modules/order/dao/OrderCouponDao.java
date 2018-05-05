/**
 * 
 */
package com.weimhc.modules.order.dao;

import com.thinkgem.javamg.common.persistence.annotation.MyBatisDao;
import com.weimhc.modules.order.entity.OrderCoupon;

import com.weimhc.framework.persistence.CrudDao;

/**
 * 订单优惠券DAO接口
 * @author lc
 * @version 2017-06-22
 */
@MyBatisDao
public interface OrderCouponDao extends CrudDao<OrderCoupon> {

}