/**
 * 
 */
package com.weimhc.modules.base.dao;

import com.thinkgem.javamg.common.persistence.annotation.MyBatisDao;
import com.weimhc.modules.base.entity.OnlineCustomerService;

import com.weimhc.framework.persistence.SortableDao;

/**
 * 在线客服DAO接口
 * @author lc
 * @version 2017-07-03
 */
@MyBatisDao
public interface OnlineCustomerServiceDao extends SortableDao<OnlineCustomerService> {

}