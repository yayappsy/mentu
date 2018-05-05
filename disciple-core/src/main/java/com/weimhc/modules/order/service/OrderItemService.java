/**
 * 
 */
package com.weimhc.modules.order.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.javamg.common.persistence.Page;
import com.weimhc.framework.service.impl.CrudServiceImpl;
import com.weimhc.modules.order.dao.OrderItemDao;
import com.weimhc.modules.order.entity.OrderItem;

/**
 * 订单项Service
 * 
 * @author lc
 * @version 2017-01-03
 */
@Service
@Transactional(readOnly = true)
public class OrderItemService extends CrudServiceImpl<OrderItemDao, OrderItem> {

	@Override
	public OrderItem get(String id) {
		return super.get(id);
	}

	public long count(OrderItem orderItem) {
		return super.count(orderItem);
	}

	@Override
	public boolean exists(String id) {
		return super.exists(id);
	}

	public List<OrderItem> findList(OrderItem orderItem) {
		return super.findList(orderItem);
	}

	public Page<OrderItem> findPage(Page<OrderItem> page, OrderItem orderItem) {
		return super.findPage(page, orderItem);
	}

	@Transactional(readOnly = false)
	public void save(OrderItem orderItem) {
		super.save(orderItem);
	}

	@Transactional(readOnly = false)
	public void deleteEntity(OrderItem orderItem) {
		super.deleteEntity(orderItem);
	}

	@Override
	@Transactional(readOnly = false)
	public void deleteEntity(String... ids) {
		if (ids != null) {
			OrderItem orderItem = null;
			for (String id : ids) {
				orderItem = new OrderItem(id);
				deleteEntity(orderItem);
			}
		}
	}

}