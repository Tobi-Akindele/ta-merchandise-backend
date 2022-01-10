package com.ta.services;

import java.util.List;

import com.ta.models.Product;

public interface ProductService {

	public Product createProduct(Product product);

	public Product getById(String productId);

	public Product updateProduct(Product productInDb, Product newProduct)
			throws NoSuchFieldException, SecurityException;

	public void deleteProduct(Product product);

	public List<Product> getByParams(boolean limit, String category);
}
