/**
 * 
 */
package com.ta.dao;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.ta.dtos.Stats;
import com.ta.models.User;

/**
 * @author Tobi Akindele
 *
 */

@Repository
public interface UserRepository extends MongoRepository<User, String> {

	Optional<User> findByUsername(String username);
	
	Optional<User> findByEmail(String email);

	@Aggregation(pipeline = {"{ $match: { createdAt: { $gte: ?0 } } }",
			"{ $project: { month: { $month: \"$createdAt\" }, }, }",
			"{ $group: { _id: \"$month\", total: { $sum: 1 }, }, }"})
	List<Stats> getUserStats(Date date);
}