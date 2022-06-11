package com.bookstore.userportal.service;


import com.bookstore.userportal.domain.*;

public interface OrderService {
	Order createOrder(ShoppingCart shoppingCart,
					  ShippingAddress shippingAddress,
					  BillingAddress billingAddress,
					  Payment payment,
					  String shippingMethod,
					  User user);
	
	Order findById(Long id);

	void save(Order order);
}
