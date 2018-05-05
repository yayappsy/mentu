/**
 * 
 */
package com.weimhc.modules.payment.dao;

import com.thinkgem.javamg.common.persistence.annotation.MyBatisDao;
import com.weimhc.framework.persistence.CrudDao;
import com.weimhc.modules.payment.entity.PaymentMethod;

/**
 * 支付方式DAO接口
 * @author zsm
 * @version 2016-02-17
 */
@MyBatisDao
public interface PaymentMethodDao extends CrudDao<PaymentMethod> {
	
}