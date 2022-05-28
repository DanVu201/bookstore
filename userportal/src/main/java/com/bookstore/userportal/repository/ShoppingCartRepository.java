package com.bookstore.userportal.repository;

import com.bookstore.userportal.domain.ShoppingCart;
import org.springframework.data.repository.CrudRepository;


public interface ShoppingCartRepository extends CrudRepository<ShoppingCart, Long> {

}
