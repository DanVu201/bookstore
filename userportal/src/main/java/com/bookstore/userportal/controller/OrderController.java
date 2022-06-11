package com.bookstore.userportal.controller;

import com.bookstore.userportal.domain.Order;
import com.bookstore.userportal.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;

@Controller
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping(value = "/order/confirm")
    public String confirm(@RequestParam(value = "id") Long id,
                          Principal principal, RedirectAttributes redirectAttributes) {
        Order order = orderService.findById(id);
        order.setOrderStatus("Đã nhận");
        orderService.save(order);
        return "redirect:/orderDetail?id=" + id;
    }

}
