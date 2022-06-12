package com.bookstore.adminportal.service;

import com.bookstore.adminportal.domain.Order;

import java.util.List;
import java.util.Optional;

public interface OrderService {

    List<Order> findByOrderStatus();

    List<Order> findByOrderStatusShipping();

    List<Order> findByOrderStatusComplete();

    List<Order> findByOrderStatusCancel();

    Integer getNumberOfOrder(String orderStatus, Integer month, Integer year);

    List<Order> findByOrderStatusAndOrderDate(String orderStatus, Integer month, Integer year);

    Optional<Order> findById(Long id);

    void save(Order order);
}
