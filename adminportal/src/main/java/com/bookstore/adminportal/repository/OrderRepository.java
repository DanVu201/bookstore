package com.bookstore.adminportal.repository;

import com.bookstore.adminportal.domain.Order;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface OrderRepository extends CrudRepository<Order, Long> {

    @Query(value = "SELECT uo.* FROM user_order uo WHERE uo.order_status = 'Đã tạo' OR uo.order_status='Đã xác nhận'", nativeQuery = true)
    List<Order> findByOrderStatus();

    @Query(value = "SELECT uo.* FROM user_order uo WHERE uo.order_status = 'Đang giao'", nativeQuery = true)
    List<Order> findByOrderStatusShipping();

    @Query(value = "SELECT uo.* FROM user_order uo WHERE uo.order_status = 'Đã nhận'", nativeQuery = true)
    List<Order> findByOrderStatusComplete();

    @Query(value = "SELECT uo.* FROM user_order uo WHERE uo.order_status = 'Đã hủy'", nativeQuery = true)
    List<Order> findByOrderStatusCancel();


    @Query(value = "SELECT COUNT(o.id) FROM Order o WHERE o.orderStatus = :orderStatus AND MONTH(o.orderDate) = :month AND YEAR(o.orderDate) = :year")
    Integer getNumberOfOrder(String orderStatus, Integer month, Integer year);

    @Query(value = "SELECT o FROM Order o WHERE o.orderStatus = :orderStatus AND MONTH(o.orderDate) = :month AND YEAR(o.orderDate) = :year")
    List<Order> findByOrderStatusAndOrderDate(String orderStatus, Integer month, Integer year);


    Optional<Order> findById(Long id);
}
