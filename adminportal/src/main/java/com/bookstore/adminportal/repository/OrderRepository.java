package com.bookstore.adminportal.repository;

import com.bookstore.adminportal.domain.Order;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface OrderRepository extends CrudRepository<Order, Long> {

    List<Order> findByOrderStatus(String orderStatus);

    Optional<Order> findById(Long id);
}
