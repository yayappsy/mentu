/**
 * 
 */
package com.weimhc.modules.order.dao;

import com.thinkgem.javamg.common.persistence.annotation.MyBatisDao;
import com.weimhc.modules.order.entity.OrderInstalment;

import com.weimhc.framework.persistence.CrudDao;

/**
 * 分期订单DAO接口
 * @author lc
 * @version 2017-06-14
 */
@MyBatisDao
public interface OrderInstalmentDao extends CrudDao<OrderInstalment> {

}