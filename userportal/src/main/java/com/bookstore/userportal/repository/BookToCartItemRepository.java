package com.bookstore.userportal.repository;

import com.bookstore.userportal.domain.BookToCartItem;
import com.bookstore.userportal.domain.CartItem;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface BookToCartItemRepository extends CrudRepository<BookToCartItem, Long> {
	
	void deleteByCartItem(CartItem cartItem);

}
