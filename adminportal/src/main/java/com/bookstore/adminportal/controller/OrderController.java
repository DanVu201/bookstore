package com.bookstore.adminportal.controller;

import com.bookstore.adminportal.domain.*;
import com.bookstore.adminportal.dto.OrderListDTO;
import com.bookstore.adminportal.repository.OrderRepository;
import com.bookstore.adminportal.service.BookService;
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

    @Autowired
    private BookService bookService;

    @RequestMapping("/orderList")
    public String getOrderList(Model model) {
        List<Order> orderList = orderService.findByOrderStatus();
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

    @RequestMapping("/orderShippingList")
    public String getOrderShippingList(Model model) {
        List<Order> orderList = orderService.findByOrderStatusShipping();
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
        return "orderShippingList";
    }

    @RequestMapping("/orderCompleteList")
    public String getOrderCompleteList(Model model) {
        List<Order> orderList = orderService.findByOrderStatusComplete();
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
        return "orderCompleteList";
    }

    @RequestMapping("/orderCancelList")
    public String getOrderCancelList(Model model) {
        List<Order> orderList = orderService.findByOrderStatusCancel();
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
        return "orderCancelList";
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

    @RequestMapping("/ship")
    public String confirmShipOrder(@RequestParam("id") Long id, Principal principal, Model model) {
        User user = userService.findByUsername(principal.getName());
        Order order = orderService.findById(id).get();
        order.setOrderStatus("Đang giao");
        order.setConfirmBy(user.getEmployee());
        orderService.save(order);
        List<Order> orderList = orderService.findByOrderStatusShipping();
        List<OrderListDTO> orderListDTO = new ArrayList<>();
        for (Order item: orderList) {
            OrderListDTO orderDTO = OrderListDTO.builder()
                    .userId(item.getUser().getId())
                    .name(String.format("%s %s", item.getUser().getFirstName(), item.getUser().getLastName()))
                    .orderDate(item.getOrderDate())
                    .orderTotal(item.getOrderTotal())
                    .orderId(item.getId())
                    .orderStatus(item.getOrderStatus())
                    .build();
            orderListDTO.add(orderDTO);
        }
        model.addAttribute("orderList", orderListDTO);
        return "orderShippingList";
    }

    @RequestMapping("/cancel")
    public String cancelOrder(@RequestParam("id") Long id, Principal principal) {
        User user = userService.findByUsername(principal.getName());
        Order order = orderService.findById(id).get();
        order.setOrderStatus("Đã hủy");
        orderService.save(order);
        List<CartItem> cartItemList = order.getCartItemList();
        for (CartItem cartItem : cartItemList) {
            Book book = bookService.findById(cartItem.getBook().getId());
            book.setInStockNumber(book.getInStockNumber() + cartItem.getQty());
            bookService.save(book);
        }
        return "redirect:/order/orderList";
    }
}
