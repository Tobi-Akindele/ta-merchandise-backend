package com.ta.utils;

public class ConstantUtils {
	
	public static final String[] ORDER_STATUS = { "PENDING" };

	public static final String[] UPDATABLE_USER_FIELDS = { "firstName", "lastName" };

	public static final String[] UPDATABLE_PRODUCT_FIELDS = { "title", "description", "image", "categories", "size",
			"color", "price" };
	
	public static final String[] UPDATABLE_CART_FIELDS = { "cartProducts" };
	
	public static final String[] UPDATABLE_ORDER_FIELDS = { "products", "amount", "address", "status" };
}
