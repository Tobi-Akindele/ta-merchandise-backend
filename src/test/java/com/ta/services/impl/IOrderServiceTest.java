package com.ta.services.impl;

import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.ta.TestUtils.ModelUtils;
import com.ta.dao.OrderRepository;
import com.ta.dtos.Stats;
import com.ta.models.Order;
import com.ta.services.UserService;

public class IOrderServiceTest {
    @Mock
    OrderRepository orderRepository;
    @Mock
    UserService userService;
    @InjectMocks
    IOrderService iOrderService;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testCreateOrder() throws Exception {
    	when(orderRepository.save(any())).thenReturn(ModelUtils.getOrder());
        Order result = iOrderService.createOrder(new Order());
        Assert.assertNotNull(result);
    }

    @Test
    public void testGetById() throws Exception {
    	Optional<Order> order = Optional.of(ModelUtils.getOrder());
    	when(orderRepository.findById(any())).thenReturn(order);
        Order result = iOrderService.getById("orderId");
        Assert.assertNotNull(result);
    }

    @Test
    public void testUpdateOrder() throws Exception {
    	Order order = ModelUtils.getOrder();
    	when(orderRepository.save(any())).thenReturn(order);
        Order result = iOrderService.updateOrder(new Order(), new Order());
        Assert.assertNotNull(result);
    }

    @Test
    public void testDeleteOrder() throws Exception {
    	doNothing().when(orderRepository).delete(any());
        iOrderService.deleteOrder(new Order());
        assertTrue(true);
    }

	@Test
	public void testGetByUserId() throws Exception {
		when(orderRepository.findAllByUserId(anyString())).thenReturn(Arrays.<Order>asList(ModelUtils.getOrder()));

		List<Order> result = iOrderService.getByUserId("userId");
		Assert.assertTrue(!result.isEmpty());
	}

    @Test
    public void testGetAllOrders() throws Exception {
        when(userService.getById(anyString())).thenReturn(ModelUtils.getUser());
        when(orderRepository.findAll()).thenReturn(Arrays.<Order>asList(ModelUtils.getOrder()));

        List<Order> result = iOrderService.getAllOrders();
        Assert.assertTrue(!result.isEmpty());
    }

    @Test
    public void testGetOrderStats() throws Exception {
        when(orderRepository.getOrderStats(any())).thenReturn(Arrays.<Stats>asList(ModelUtils.getStats()));

        List<Stats> result = iOrderService.getOrderStats();
        Assert.assertTrue(!result.isEmpty());
    }
}