package com.bookstore.adminportal.service;

import com.bookstore.adminportal.domain.Order;

import java.util.List;
import java.util.Optional;

public interface OrderService {

    List<Order> findByOrderStatus(String orderStatus);

    Optional<Order> findById(Long id);

    void save(Order order);
}
