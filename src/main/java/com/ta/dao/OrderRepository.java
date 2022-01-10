package com.ta.dao;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.ta.models.Order;

@Repository
public interface OrderRepository extends MongoRepository<Order, String> {

	List<Order> findAllByUserId(String userId);

}
