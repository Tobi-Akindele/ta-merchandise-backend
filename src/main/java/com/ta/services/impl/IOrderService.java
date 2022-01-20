package com.ta.services.impl;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ta.dao.OrderRepository;
import com.ta.dtos.Stats;
import com.ta.models.Order;
import com.ta.services.OrderService;
import com.ta.services.UserService;
import com.ta.utils.BeanUtils;
import com.ta.utils.ConstantUtils;

@Service
public class IOrderService implements OrderService {
	
	@Autowired
	private OrderRepository orderRepository;
	
    @Autowired
    private UserService userService;

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
		List<Order> orders = orderRepository.findAll();
		orders.forEach(order -> {
			order.setUser(userService.getById(order.getUserId()));
		});
		return orders;
	}

	@Override
	public List<Stats> getOrderStats() {
		LocalDateTime ldt = LocalDateTime.now().minusMonths(2);
		Date previousMonth = Date.from(ldt.atZone(ZoneId.systemDefault()).toInstant());
		return orderRepository.getOrderStats(previousMonth);
	}

}
