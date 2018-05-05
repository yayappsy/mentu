/**
 * 
 */
package com.weimhc.modules.order.dao;

import com.thinkgem.javamg.common.persistence.annotation.MyBatisDao;
import com.weimhc.framework.persistence.CrudDao;
import com.weimhc.modules.order.entity.OrderItem;

/**
 * 订单项DAO接口
 * 
 * @author lc
 * @version 2017-01-03
 */
@MyBatisDao
public interface OrderItemDao extends CrudDao<OrderItem> {

}