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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ta.dtos.Response;
import com.ta.exceptions.BadRequestException;
import com.ta.exceptions.NotFoundException;
import com.ta.models.Product;
import com.ta.services.ProductService;
import com.ta.utils.Utils;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api")
public class ProductController {

	@Autowired
	private ProductService productService;
	
	@PostMapping("/product")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> createProduct(@Valid @RequestBody Product product) {
		
		if(product == null)
			throw new BadRequestException("400", "Requestbody is required");
		
		product = productService.createProduct(product);
		return ResponseEntity.ok(new Response("Product created successfully", product));
	}
	
	@PutMapping("/product/{productId}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> updateProduct(@Valid @RequestBody Product product, @PathVariable String productId)
			throws NoSuchFieldException, SecurityException {
		
		if(product == null || Utils.isEmptyString(productId))
			throw new BadRequestException("400", "Request parameter is incomplete");
		
		Product productInDb = productService.getById(productId);
		if(productInDb == null)
			throw new NotFoundException("404", "Product not found");
		
		product = productService.updateProduct(productInDb, product);
		return ResponseEntity.ok(new Response("Product updated successfully", product));
	}
	
	@DeleteMapping("/product/{productId}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> deleteProduct(@PathVariable String productId) {
		
		if(Utils.isEmptyString(productId))
			throw new BadRequestException("400", "Product ID is required");
		
		Product product = productService.getById(productId);
		if(product == null)
			throw new NotFoundException("400", "Resource not found");
		
		productService.deleteProduct(product);
		
		return ResponseEntity.ok(new Response("200", "Product deleted successfully"));
	}
	
	@GetMapping("/product/{productId}")
	public ResponseEntity<?> getProduct(@PathVariable String productId) {
		
		if(Utils.isEmptyString(productId))
			throw new BadRequestException("400", "Product ID is required");
		
		Product product = productService.getById(productId);
		if(product == null)
			throw new NotFoundException("400", "Resource not found");
		
		return ResponseEntity.ok(new Response("Product retrieved successfully", product));
	}
	
	@GetMapping("/products")
	public ResponseEntity<?> getUsers(@RequestParam(required = false) boolean limit,
			@RequestParam(required = false) String category) {
		
		List<Product> products = productService.getByParams(limit, category);
		
		return ResponseEntity.ok(new Response("Users retrieved successfully", products));
	}
}
