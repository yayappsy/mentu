/**
 * 
 */
package com.weimhc.modules.payment.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.weimhc.framework.service.impl.CrudServiceImpl;
import com.weimhc.modules.payment.dao.PaymentTurnoverDao;
import com.weimhc.modules.payment.entity.PaymentTurnover;

/**
 * 订单支付流水Service
 * 
 * @author lc
 * @version 2017-06-08
 */
@Service
@Transactional(readOnly = true)
public class PaymentTurnoverService
		extends CrudServiceImpl<PaymentTurnoverDao, PaymentTurnover> {

	/**
	 * @param paymentSn
	 * @return
	 */
	public PaymentTurnover findBySn(String paymentSn) {
		PaymentTurnover entity = new PaymentTurnover();
		entity.setSn(paymentSn);
		entity = dao.getEntity(entity);
		return entity;
	}

}