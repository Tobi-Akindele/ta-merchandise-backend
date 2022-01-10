package com.ta.services.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.ta.dao.ProductRepository;
import com.ta.models.Product;
import com.ta.services.ProductService;
import com.ta.utils.BeanUtils;
import com.ta.utils.ConstantUtils;
import com.ta.utils.Utils;

@Service
public class IProductService implements ProductService {
	
	@Autowired
	private ProductRepository productRepository;

	@Override
	public Product createProduct(Product product) {
		product.setCreatedAt(new Date());
		product.setUpdatedAt(new Date());
		return productRepository.save(product);
	}

	@Override
	public Product getById(String productId) {
		return productRepository.findById(productId).orElse(null);
	}

	@Override
	public Product updateProduct(Product productInDb, Product newProduct)
			throws NoSuchFieldException, SecurityException {
		BeanUtils<Product, Product> beanUtils = new BeanUtils<>();
		beanUtils.copyUpdatableFields(productInDb, newProduct, ConstantUtils.UPDATABLE_PRODUCT_FIELDS);
		productInDb.setUpdatedAt(new Date());
		return productRepository.save(productInDb);
	}

	@Override
	public void deleteProduct(Product product) {
		productRepository.delete(product);
	}

	@Override
	public List<Product> getByParams(boolean limit, String category) {
		final Pageable pageRequest = PageRequest.of(0, 5, Sort.by(Direction.DESC, "createdAt"));
		List<Product> products = new ArrayList<>();
		if(limit) {
			products = productRepository.findAll(pageRequest).getContent();
		} else if(!Utils.isEmptyString(category)) {
			products = productRepository.findByCategoriesIn(category, pageRequest).getContent();
		} else {
			products = productRepository.findAll();
		}
		return products;
	}

}
