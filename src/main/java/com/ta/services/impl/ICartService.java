package com.ta.services.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ta.dao.CartRepository;
import com.ta.models.Cart;
import com.ta.services.CartService;
import com.ta.utils.BeanUtils;
import com.ta.utils.ConstantUtils;

@Service
public class ICartService implements CartService {
	
	@Autowired
	private CartRepository cartRepository;

	@Override
	public Cart createCart(Cart cart) {
		cart.setCreatedAt(new Date());
		cart.setUpdatedAt(new Date());
		return cartRepository.save(cart);
	}

	@Override
	public Cart getById(String cartId) {
		return cartRepository.findById(cartId).orElse(null);
	}

	@Override
	public Cart updateCart(Cart cartInDb, Cart cart) 
			throws NoSuchFieldException, SecurityException {
		BeanUtils<Cart, Cart> beanUtils = new BeanUtils<>();
		beanUtils.copyUpdatableFields(cartInDb, cart, ConstantUtils.UPDATABLE_CART_FIELDS);
		cartInDb.setUpdatedAt(new Date());
		return cartRepository.save(cartInDb);
	}

	@Override
	public void deleteCart(Cart cart) {
		cartRepository.delete(cart);
	}

	@Override
	public Cart getByUserId(String userId) {
		return cartRepository.findByUserId(userId).orElse(null);
	}

	@Override
	public List<Cart> getAllCarts() {
		return cartRepository.findAll();
	}
}
