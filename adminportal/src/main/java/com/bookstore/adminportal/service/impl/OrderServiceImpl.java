package com.bookstore.adminportal.service.impl;

import com.bookstore.adminportal.domain.Order;
import com.bookstore.adminportal.repository.OrderRepository;
import com.bookstore.adminportal.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;


    @Override
    public List<Order> findByOrderStatus() {
        return orderRepository.findByOrderStatus();
    }

    @Override
    public List<Order> findByOrderStatusShipping() {
        return orderRepository.findByOrderStatusShipping();
    }

    @Override
    public List<Order> findByOrderStatusComplete() {
        return orderRepository.findByOrderStatusComplete();
    }

    @Override
    public List<Order> findByOrderStatusCancel() {
        return orderRepository.findByOrderStatusCancel();
    }

    @Override
    public Integer getNumberOfOrder(String orderStatus, Integer month, Integer year) {
        return orderRepository.getNumberOfOrder(orderStatus, month, year);
    }

    @Override
    public List<Order> findByOrderStatusAndOrderDate(String orderStatus, Integer month, Integer year) {
        return orderRepository.findByOrderStatusAndOrderDate(orderStatus, month, year);
    }

    @Override
    public Optional<Order> findById(Long id) {
        return orderRepository.findById(id);
    }

    @Override
    public void save(Order order) {
        orderRepository.save(order);
    }


}
