/**
 * 
 */
package com.weimhc.modules.order.service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.javamg.common.persistence.Page;
import com.weimhc.framework.service.impl.CrudServiceImpl;
import com.weimhc.modules.coupon.entity.Coupon;
import com.weimhc.modules.member.dao.MemberCouponDao;
import com.weimhc.modules.member.entity.MemberCoupon;
import com.weimhc.modules.order.dao.OrderCouponDao;
import com.weimhc.modules.order.dao.OrderDao;
import com.weimhc.modules.order.dao.OrderInstalmentDao;
import com.weimhc.modules.order.dao.OrderItemDao;
import com.weimhc.modules.order.entity.Order;
import com.weimhc.modules.order.entity.OrderCoupon;
import com.weimhc.modules.order.entity.OrderInstalment;
import com.weimhc.modules.order.entity.OrderItem;
import com.weimhc.modules.order.entity.OrderStatus;
import com.weimhc.modules.order.entity.PaymentStatus;
import com.weimhc.modules.payment.entity.PaymentTurnover;
import com.weimhc.modules.product.dao.ProductDao;
import com.weimhc.modules.product.dao.ProductExtraDao;
import com.weimhc.modules.product.entity.ProductInstalment;

/**
 * 订单Service
 * 
 * @author lc
 * @version 2017-01-03
 */
@Service
@Transactional(readOnly = true)
public class OrderService extends CrudServiceImpl<OrderDao, Order> {

	@Autowired
	private OrderItemDao orderItemDao;

	@Autowired
	private ProductDao productDao;

	@Autowired
	private MemberCouponDao memberCouponDao;

	@Autowired
	private OrderCouponDao orderCouponDao;
	@Autowired
	private OrderInstalmentDao orderInstalmentDao;
	@Autowired
	private ProductExtraDao productExtraDao;

	@Override
	public Order get(String id) {
		return super.get(id);
	}

	@Override
	public long count(Order shopOrder) {
		return super.count(shopOrder);
	}

	@Override
	public boolean exists(String id) {
		return super.exists(id);
	}

	@Override
	public List<Order> findList(Order shopOrder) {
		return super.findList(shopOrder);
	}

	@Override
	public Page<Order> findPage(Page<Order> page, Order shopOrder) {
		return super.findPage(page, shopOrder);
	}

	@Override
	@Transactional(readOnly = false)
	public void save(Order shopOrder) {
		super.save(shopOrder);
	}

	@Transactional(readOnly = false)
	public void saveOrder(Order shopOrder, List<OrderItem> orderItems,
			List<Coupon> couponList,
			List<ProductInstalment> productInstalmentList) {
		super.save(shopOrder);
		for (OrderItem orderItem : orderItems) {
			orderItem.setOrder(shopOrder);
			orderItem.preInsert();
			orderItemDao.insert(orderItem);
		}
		for (Coupon coupon : couponList) {
			MemberCoupon memberCoupon = new MemberCoupon();
			memberCoupon.setStudent(shopOrder.getMember());
			memberCoupon.setCoupon(coupon);
			memberCoupon = memberCouponDao.getEntity(memberCoupon);
			memberCouponDao.delete(memberCoupon);
			OrderCoupon orderCoupon = new OrderCoupon();
			orderCoupon.setOrder(shopOrder);
			orderCoupon.setCoupon(coupon);
			orderCoupon.setCouponName(coupon.getName());
			orderCoupon.setQuantity(1);
			orderCoupon.preInsert();
			orderCouponDao.insert(orderCoupon);
		}
		for (ProductInstalment productInstalment : productInstalmentList) {
			OrderInstalment orderInstalment = new OrderInstalment();
			orderInstalment.setOrder(shopOrder);
			orderInstalment.setProduct(productInstalment.getProduct());
			orderInstalment.setProductName(orderInstalment.getProductName());
			orderInstalment.setName(productInstalment.getName());
			orderInstalment.setDescription(productInstalment.getDescription());
			orderInstalment.setPeriods(productInstalment.getPeriods());
			orderInstalment.setIsOver(false);
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(new Date());
			calendar.add(Calendar.MONTH, +productInstalment.getPeriods());
			Date date = calendar.getTime();
			orderInstalment.setExpire(date);
			orderInstalment.preInsert();
			orderInstalmentDao.insert(orderInstalment);
		}
	}

	/**
	 * 根据对订单号查询订单
	 * 
	 * @param sn
	 * @return
	 */
	public Order findBySn(String sn) {
		Order order = new Order();
		order.setSn(sn);
		return this.getEntity(order);
	}

	/**
	 * 订单支付操作
	 * 
	 * @param order
	 * @param paymentTurnover
	 */
	@Transactional(readOnly = false)
	public void payOrder(Order order, PaymentTurnover paymentTurnover) {
		order = dao.getEntity(order);
		order.setPaymentDate(new Date());
		order.setStatus(OrderStatus.completed);
		order.setPaymentStatus(PaymentStatus.paid);
		dao.payOrder(order);
		orderPaymented(order);
	}

	/**
	 * 支付后
	 * 
	 * @param order
	 * @param orderItem
	 */
	private void orderPaymented(Order order) {
		List<OrderItem> orderItems = orderItemDao
				.findAllList(new OrderItem(order));

	}

	@Override
	@Transactional(readOnly = false)
	public void deleteEntity(Order shopOrder) {
		super.deleteEntity(shopOrder);
	}

	@Override
	@Transactional(readOnly = false)
	public void deleteEntity(String... ids) {
		if (ids != null) {
			Order shopOrder = null;
			for (String id : ids) {
				shopOrder = new Order(id);
				deleteEntity(shopOrder);
			}
		}
	}

	public void updateOrderStatus(Order order) {
		dao.updateOrderStatus(order);
	}

}