package com.ta.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ta.dtos.Response;
import com.ta.dtos.Stats;
import com.ta.exceptions.BadRequestException;
import com.ta.exceptions.NotFoundException;
import com.ta.models.Order;
import com.ta.models.User;
import com.ta.services.OrderService;
import com.ta.services.UserService;
import com.ta.utils.Utils;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api")
public class OrderController {

	@Autowired
	private OrderService orderService;
	
	@Autowired
	private UserService userService;
	
	@PostMapping("/order")
	@PreAuthorize("hasAnyRole('ADMIN', 'USER')")
	public ResponseEntity<?> createOrder(@Valid @RequestBody Order order) {
		
		if(order == null)
			throw new BadRequestException("400", "Requestbody is required");
		
		order = orderService.createOrder(order);
		return ResponseEntity.ok(order);
	}
	
	@PutMapping("/order/{orderId}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> updateOrder(@Valid @RequestBody Order order, @PathVariable String orderId)
			throws NoSuchFieldException, SecurityException {
		
		if(order == null || Utils.isEmptyString(orderId))
			throw new BadRequestException("400", "Request parameter is incomplete");
		
		Order orderInDb = orderService.getById(orderId);
		if(orderInDb == null)
			throw new NotFoundException("404", "Order not found");
		
		order = orderService.updateOrder(orderInDb, order);
		return ResponseEntity.ok(order);
	}
	
	@DeleteMapping("/order/{orderId}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> deleteOrder(@PathVariable String orderId) {
		
		if(Utils.isEmptyString(orderId))
			throw new BadRequestException("400", "Order ID is required");
		
		Order order = orderService.getById(orderId);
		if(order == null)
			throw new NotFoundException("400", "Resource not found");
		
		orderService.deleteOrder(order);
		
		return ResponseEntity.ok(new Response("200", "Order deleted successfully"));
	}
	
	@GetMapping("/order/{userId}")
	@PreAuthorize("hasAnyRole('ADMIN', 'USER')")
	public ResponseEntity<?> getUserOrder(@PathVariable String userId) {
		
		if(Utils.isEmptyString(userId))
			throw new BadRequestException("400", "User ID is required");
		
		User user = userService.getById(userId);
		if(user == null)
			throw new NotFoundException("400", "User not found");
		
		List<Order> orders = orderService.getByUserId(userId);
		
		return ResponseEntity.ok(orders);
	}
	
	@GetMapping("/orders")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> getOrders() {
		
		List<Order> orders = orderService.getAllOrders();
		
		return ResponseEntity.ok(orders);
	}
	
	@GetMapping("/orders/income")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> getOrderStats() {
		
		List<Stats> stats = orderService.getOrderStats();
		
		return ResponseEntity.ok(stats);
	}
}
