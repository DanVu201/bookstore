package com.bookstore.adminportal.service;

import com.bookstore.adminportal.domain.Order;

import java.util.List;
import java.util.Optional;

public interface OrderService {

    List<Order> findByOrderStatus();

    List<Order> findByOrderStatusShipping();

    List<Order> findByOrderStatusComplete();

    List<Order> findByOrderStatusCancel();

    Optional<Order> findById(Long id);

    void save(Order order);
}
