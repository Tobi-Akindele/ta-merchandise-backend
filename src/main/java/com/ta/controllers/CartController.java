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
import org.springframework.web.bind.annotation.RestController;

import com.ta.dtos.Response;
import com.ta.exceptions.BadRequestException;
import com.ta.exceptions.NotFoundException;
import com.ta.models.Cart;
import com.ta.models.User;
import com.ta.services.CartService;
import com.ta.services.UserService;
import com.ta.utils.Utils;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api")
public class CartController {

	@Autowired
	private CartService cartService;
	
	@Autowired
	private UserService userService;
	
	@PostMapping("/cart")
	@PreAuthorize("hasAnyRole('ADMIN', 'USER')")
	public ResponseEntity<?> createCart(@Valid @RequestBody Cart cart) {
		
		if(cart == null)
			throw new BadRequestException("400", "Requestbody is required");
		
		cart = cartService.createCart(cart);
		return ResponseEntity.ok(cart);
	}
	
	@PutMapping("/cart/{cartId}")
	@PreAuthorize("hasAnyRole('ADMIN', 'USER')")
	public ResponseEntity<?> updateCart(@Valid @RequestBody Cart cart, @PathVariable String cartId)
			throws NoSuchFieldException, SecurityException {
		
		if(cart == null || Utils.isEmptyString(cartId))
			throw new BadRequestException("400", "Request parameter is incomplete");
		
		Cart cartInDb = cartService.getById(cartId);
		if(cartInDb == null)
			throw new NotFoundException("404", "Cart not found");
		
		cart = cartService.updateCart(cartInDb, cart);
		return ResponseEntity.ok(cart);
	}
	
	@DeleteMapping("/cart/{cartId}")
	@PreAuthorize("hasAnyRole('ADMIN', 'USER')")
	public ResponseEntity<?> deleteCart(@PathVariable String cartId) {
		
		if(Utils.isEmptyString(cartId))
			throw new BadRequestException("400", "Cart ID is required");
		
		Cart cart = cartService.getById(cartId);
		if(cart == null)
			throw new NotFoundException("400", "Resource not found");
		
		cartService.deleteCart(cart);
		
		return ResponseEntity.ok(new Response("200", "Cart deleted successfully"));
	}
	
	@GetMapping("/cart/{userId}")
	@PreAuthorize("hasAnyRole('ADMIN', 'USER')")
	public ResponseEntity<?> getUserCart(@PathVariable String userId) {
		
		if(Utils.isEmptyString(userId))
			throw new BadRequestException("400", "User ID is required");
		
		User user = userService.getById(userId);
		if(user == null)
			throw new NotFoundException("400", "User not found");
		
		Cart cart = cartService.getByUserId(userId);
		
		return ResponseEntity.ok(cart);
	}
	
	@GetMapping("/carts")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> getCarts() {
		
		List<Cart> carts = cartService.getAllCarts();
		
		return ResponseEntity.ok(carts);
	}
}
