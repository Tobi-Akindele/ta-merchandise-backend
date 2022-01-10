package com.ta.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.ta.models.Product;

@Repository
public interface ProductRepository extends MongoRepository<Product, String> {

	Page<Product> findByCategoriesIn(String category, Pageable pageRequest);

}
