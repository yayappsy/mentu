/**
 * 
 */
package com.weimhc.modules.payment.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.javamg.common.persistence.Page;
import com.weimhc.framework.service.impl.CrudServiceImpl;
import com.weimhc.modules.payment.dao.PaymentMethodDao;
import com.weimhc.modules.payment.entity.PaymentMethod;

/**
 * 支付方式Service
 * @author zsm
 * @version 2016-02-17
 */
@Service
@Transactional(readOnly = true)
public class PaymentMethodService extends CrudServiceImpl<PaymentMethodDao, PaymentMethod> {

	public PaymentMethod get(String id) {
		return super.get(id);
	}

	public long count(PaymentMethod paymentMethod) {
		return super.count(paymentMethod);
	}
	
	public boolean exists(String id) {
		return super.exists(id);
	}
	
	public List<PaymentMethod> findList(PaymentMethod paymentMethod) {
		return super.findList(paymentMethod);
	}
	
	public Page<PaymentMethod> findPage(Page<PaymentMethod> page, PaymentMethod paymentMethod) {
		return super.findPage(page, paymentMethod);
	}
	
	@Transactional(readOnly = false)
	public void save(PaymentMethod paymentMethod) {
		super.save(paymentMethod);
	}
	
	@Transactional(readOnly = false)
	public void deleteEntity(PaymentMethod paymentMethod) {
		super.deleteEntity(paymentMethod);
	}
	
	@Transactional(readOnly = false)
	public void deleteEntity(String... ids) {
		if (ids != null) {
			PaymentMethod paymentMethod = null;
			for (String id : ids) {
				paymentMethod = new PaymentMethod(id);
				deleteEntity(paymentMethod);
			}
		}
	}
	
}