/**
 * 
 */
package com.weimhc.modules.payment.dao;

import com.thinkgem.javamg.common.persistence.annotation.MyBatisDao;
import com.weimhc.modules.payment.entity.PaymentTurnover;

import com.weimhc.framework.persistence.CrudDao;

/**
 * 订单支付流水DAO接口
 * @author lc
 * @version 2017-06-08
 */
@MyBatisDao
public interface PaymentTurnoverDao extends CrudDao<PaymentTurnover> {

}