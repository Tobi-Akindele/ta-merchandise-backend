/**
 * 
 */
package com.ta.dao;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.ta.models.User;

/**
 * @author Tobi Akindele
 *
 */

@Repository
public interface UserRepository extends MongoRepository<User, String> {

	Optional<User> findByUsername(String username);
	
	Optional<User> findByEmail(String email);
}
