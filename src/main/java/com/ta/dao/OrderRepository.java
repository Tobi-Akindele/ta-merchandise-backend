package com.ta.dao;

import java.util.Date;
import java.util.List;

import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.ta.dtos.Stats;
import com.ta.models.Order;

@Repository
public interface OrderRepository extends MongoRepository<Order, String> {

	List<Order> findAllByUserId(String userId);

	@Aggregation(pipeline = { "{ $match: {createdAt: { $gte: ?0 } } }", 
			"{ $project: { month: { $month: \"$createdAt\" }, sales: \"$amount\", }, }", 
			"{ $group: { _id: \"$month\", total: { $sum: \"$sales\" }, }, }"})
	List<Stats> getOrderStats(Date previousMonth);

}
