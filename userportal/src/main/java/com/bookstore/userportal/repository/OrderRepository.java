package com.bookstore.userportal.repository;

import com.bookstore.userportal.domain.Order;
import org.springframework.data.repository.CrudRepository;

public interface OrderRepository extends CrudRepository<Order, Long>{

}
