package com.ta.services;

import java.util.List;

import com.ta.models.Cart;

public interface CartService {

	public Cart createCart(Cart cart);

	public Cart getById(String cartId);

	public Cart updateCart(Cart cartInDb, Cart cart) throws NoSuchFieldException, SecurityException;

	public void deleteCart(Cart cart);

	public Cart getByUserId(String userId);

	public List<Cart> getAllCarts();
}
