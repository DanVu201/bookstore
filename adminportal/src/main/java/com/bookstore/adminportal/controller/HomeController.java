package com.bookstore.adminportal.controller;

import com.bookstore.adminportal.domain.CartItem;
import com.bookstore.adminportal.domain.Order;
import com.bookstore.adminportal.service.CouponImportService;
import com.bookstore.adminportal.service.OrderService;
import com.bookstore.adminportal.service.SalesService;
import com.bookstore.adminportal.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;

@Controller
public class HomeController {
    @Autowired
    private UserService userService;

    @Autowired
    private SalesService salesService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private CouponImportService couponImportService;

    @RequestMapping("/")
    public String index() {
        return "redirect:/home";
    }


    @RequestMapping("/home")
    public String home(Model model) {
        List<String[]> listCategory = salesService.listAllSales(LocalDate.now().getYear());
        List<String> category = new ArrayList<>();
        List<Integer> quantityCategory = new ArrayList<>();
        for (int i = 0; i < listCategory.size(); i++) {
            category.add(listCategory.get(i)[1]);
            quantityCategory.add(Integer.valueOf(listCategory.get(i)[0]));
        }
        List<int[]> list = salesService.listSales(LocalDate.now().getYear());
        List<Integer> quantity = new ArrayList<>();
        List<Integer> month = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            month.add(list.get(i)[0]);
            quantity.add(list.get(i)[1]);
        }
        int maxY = Collections.max(quantity);
        Integer numberOfOrderComplete = orderService.getNumberOfOrder("Đã nhận",
                LocalDate.now().getMonthValue(), LocalDate.now().getYear());
        Integer numberOfOrderCancel = orderService.getNumberOfOrder("Đã hủy",
                LocalDate.now().getMonthValue(), LocalDate.now().getYear());
        Integer totalPriceCouponImport = couponImportService.getTotalPriceCouponImport(LocalDate.now().getMonthValue(),
                LocalDate.now().getYear());

        List<Order> orders = orderService.findByOrderStatusAndOrderDate("Đã nhận", LocalDate.now().getMonthValue(),
                LocalDate.now().getYear());
        BigDecimal orderTotal = new BigDecimal(0);
        for (Order order : orders) {
            for (CartItem cartItem : order.getCartItemList()) {
                orderTotal = orderTotal.add(cartItem.getSubtotal());
            }
        }


        model.addAttribute("month", month);
        model.addAttribute("quantity", quantity);
        model.addAttribute("maxQuantity", maxY);
        model.addAttribute("category", category);
        model.addAttribute("quantityCategory", quantityCategory);
        model.addAttribute("inputMonth", LocalDate.now().getMonthValue());
        model.addAttribute("inputYear", LocalDate.now().getYear());
        model.addAttribute("numberOfOrderComplete", numberOfOrderComplete);
        model.addAttribute("numberOfOrderCancel", numberOfOrderCancel);
        model.addAttribute("revenue", orderTotal.subtract(new BigDecimal(totalPriceCouponImport)).intValue());
        return "home";
    }

    @RequestMapping("/statistics")
    public String reportCategorySales(@RequestParam("year") int year,
                                      @RequestParam("month") int month,
                                      Model model) {
        List<String[]> listCategory = salesService.listAllSales(year);
        List<String> category = new ArrayList<>();
        List<Integer> quantityCategory = new ArrayList<>();
        for (int i = 0; i < listCategory.size(); i++) {
            category.add(listCategory.get(i)[1]);
            quantityCategory.add(Integer.valueOf(listCategory.get(i)[0]));
        }
        List<int[]> list = salesService.listSales(year);
        List<Integer> quantity = new ArrayList<>();
        List<Integer> monthList = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            monthList.add(list.get(i)[0]);
            quantity.add(list.get(i)[1]);
        }
        int maxY = Collections.max(quantity);

        Integer numberOfOrderComplete = orderService.getNumberOfOrder("Đã nhận", month, year);
        Integer numberOfOrderCancel = orderService.getNumberOfOrder("Đã hủy", month, year);
        model.addAttribute("month", monthList);
        model.addAttribute("quantity", quantity);
        model.addAttribute("maxQuantity", maxY);
        model.addAttribute("category", category);
        model.addAttribute("quantityCategory", quantityCategory);
        model.addAttribute("inputMonth", month);
        model.addAttribute("inputYear", year);
        model.addAttribute("numberOfOrderComplete", numberOfOrderComplete);
        model.addAttribute("numberOfOrderCancel", numberOfOrderCancel);
        return "home";
    }

    @RequestMapping("/login")
    public String login() throws Exception {
        return "login";
    }
}
