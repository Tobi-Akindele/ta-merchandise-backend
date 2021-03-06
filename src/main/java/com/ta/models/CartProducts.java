package com.ta.models;

public class CartProducts {

	private String productId;
	private Long quantity;
	
	public CartProducts() {
	}
	
	public CartProducts(String productId, Long quantity) {
		this.productId = productId;
		this.quantity = quantity;
	}

	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}
	public Long getQuantity() {
		return quantity;
	}
	public void setQuantity(Long quantity) {
		this.quantity = quantity;
	}
}
