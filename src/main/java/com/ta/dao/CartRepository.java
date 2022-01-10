package com.ta.dao;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.ta.models.Cart;

@Repository
public interface CartRepository extends MongoRepository<Cart, String> {

	Optional<Cart> findByUserId(String userId);

}
