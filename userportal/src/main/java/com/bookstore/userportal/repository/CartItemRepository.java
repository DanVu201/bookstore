package com.bookstore.userportal.repository;

import com.bookstore.userportal.domain.CartItem;
import com.bookstore.userportal.domain.Order;
import com.bookstore.userportal.domain.ShoppingCart;
import org.springframework.data.repository.CrudRepository;

import java.util.List;


public interface CartItemRepository extends CrudRepository<CartItem, Long>{
	
	List<CartItem> findByShoppingCart(ShoppingCart shoppingCart);
	
	List<CartItem> findByOrder(Order order);
}
