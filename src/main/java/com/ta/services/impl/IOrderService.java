package com.ta.services.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ta.dao.OrderRepository;
import com.ta.models.Order;
import com.ta.services.OrderService;
import com.ta.utils.BeanUtils;
import com.ta.utils.ConstantUtils;

@Service
public class IOrderService implements OrderService {
	
	@Autowired
	private OrderRepository orderRepository;

	@Override
	public Order createOrder(Order order) {
		order.setCreatedAt(new Date());
		order.setUpdatedAt(new Date());
		order.setStatus(ConstantUtils.ORDER_STATUS[0]);
		return orderRepository.save(order);
	}

	@Override
	public Order getById(String orderId) {
		return orderRepository.findById(orderId).orElse(null);
	}

	@Override
	public Order updateOrder(Order orderInDb, Order order) 
			throws NoSuchFieldException, SecurityException {
		BeanUtils<Order, Order> beanUtils = new BeanUtils<>();
		beanUtils.copyUpdatableFields(orderInDb, order, ConstantUtils.UPDATABLE_ORDER_FIELDS);
		orderInDb.setUpdatedAt(new Date());
		return orderRepository.save(orderInDb);
	}

	@Override
	public void deleteOrder(Order order) {
		orderRepository.delete(order);
	}

	@Override
	public List<Order> getByUserId(String userId) {
		return orderRepository.findAllByUserId(userId);
	}

	@Override
	public List<Order> getAllOrders() {
		return orderRepository.findAll();
	}

}
