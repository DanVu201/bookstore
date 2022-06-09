package com.bookstore.adminportal.controller;

import com.bookstore.adminportal.domain.Order;
import com.bookstore.adminportal.domain.User;
import com.bookstore.adminportal.domain.UserShipping;
import com.bookstore.adminportal.dto.OrderListDTO;
import com.bookstore.adminportal.repository.OrderRepository;
import com.bookstore.adminportal.service.OrderService;
import com.bookstore.adminportal.service.UserService;
import com.bookstore.adminportal.utility.USConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private UserService userService;

    @RequestMapping("/orderList")
    public String getOrderList(Model model) {

        List<Order> orderList = orderService.findByOrderStatus("Đã tạo");
        List<OrderListDTO> orderListDTO = new ArrayList<>();
        for (Order order: orderList) {
            OrderListDTO orderDTO = OrderListDTO.builder()
                    .userId(order.getUser().getId())
                    .name(String.format("%s %s", order.getUser().getFirstName(), order.getUser().getLastName()))
                    .orderDate(order.getOrderDate())
                    .orderTotal(order.getOrderTotal())
                    .orderId(order.getId())
                    .orderStatus(order.getOrderStatus())
                    .build();
            orderListDTO.add(orderDTO);
        }
        model.addAttribute("orderList", orderListDTO);
        return "orderlist";
    }

    @RequestMapping("/confirm")
    public String confirmOrder(@RequestParam("id") Long id, Principal principal) {
        User user = userService.findByUsername(principal.getName());
        Order order = orderService.findById(id).get();
        order.setOrderStatus("Đã xác nhận");
        order.setConfirmBy(user.getEmployee());
        orderService.save(order);
        return "redirect:/order/orderList";
    }

    @RequestMapping("/cancel")
    public String cancelOrder(@RequestParam("id") Long id, Principal principal) {
        User user = userService.findByUsername(principal.getName());
        Order order = orderService.findById(id).get();
        order.setOrderStatus("Đã hủy");
        orderService.save(order);
        return "redirect:/order/orderList";
    }
}
