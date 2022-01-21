package com.ta.TestUtils;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import com.ta.dtos.Stats;
import com.ta.models.CartProducts;
import com.ta.models.Contact;
import com.ta.models.NewsLetter;
import com.ta.models.Order;
import com.ta.models.Product;
import com.ta.models.User;

public class ModelUtils {

	public static User getUser() {
		User user = new User();
		user.setId("53fdfdioe");
		user.setFirstName("First Name");
		user.setLastName("Last Name");
		user.setUsername("username");
		return user;
	}
	
	public static Contact getContact() {
		Contact contact = new Contact();
		contact.setId("dfds97397hifkdi7");
		contact.setName("FirstName LastName");
		contact.setEmail("email@email.com");
		contact.setMessage("message");
		return contact;
	}
	
	public static NewsLetter getNewsLetter() {
		NewsLetter newsLetter = new NewsLetter();
		newsLetter.setId("dfds97397hifkdi7");
		newsLetter.setEmail("email@email.com");
		return newsLetter;
	}
	
	public static Order getOrder() {
		
		Set<CartProducts> products = new HashSet<>();
		CartProducts cartProduct = new CartProducts("dfds97397hifkdi7", 230L);
		products.add(cartProduct);
		
		Order order = new Order();
		order.setId("dfds97397hifkdi7");
		order.setUserId("dfds97397hifkdi7");
		order.setProducts(products);
		order.setAmount(new BigDecimal(500.99));
		order.setAddress("address");
		return order;
	}
	
	public static Stats getStats() {
		Stats stats = new Stats();
		stats.setId(1L);
		stats.setTotal(200L);
		return stats;
	}
	
	public static Product getProduct() {
		Product product = new Product();
		product.setId("dfds97397hifkdi7");
		product.setTitle("Product");
		product.setDescription("Product Description");
		product.setImage("image");
		product.setCategories(Arrays.asList("CategoryOne, CategoryTwo"));
		product.setSize(Arrays.asList("SizeOne, SizeTwo"));
		product.setColor(Arrays.asList("ColorOne, ColorTwo"));
		product.setInStock(true);
		product.setPrice(new BigDecimal(2.99));
		return product;
	}
}
