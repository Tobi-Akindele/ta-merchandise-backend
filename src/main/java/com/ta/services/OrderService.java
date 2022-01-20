package com.ta.services;

import java.util.List;

import com.ta.dtos.Stats;
import com.ta.models.Order;

public interface OrderService {

	public Order createOrder(Order order);

	public Order getById(String orderId);

	public Order updateOrder(Order orderInDb, Order order) throws NoSuchFieldException, SecurityException;

	public void deleteOrder(Order order);

	public List<Order> getByUserId(String userId);

	public List<Order> getAllOrders();

	public List<Stats> getOrderStats();
}
