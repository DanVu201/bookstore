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
    public List<Order> findByOrderStatus(String orderStatus) {
        return orderRepository.findByOrderStatus(orderStatus);
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
