package com.bookstore.adminportal.controller;

import com.bookstore.adminportal.domain.User;
import com.bookstore.adminportal.domain.security.Role;
import com.bookstore.adminportal.domain.security.UserRole;
import com.bookstore.adminportal.service.SalesService;
import com.bookstore.adminportal.service.UserService;
import com.bookstore.adminportal.utility.SecurityUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.*;

@Controller
public class HomeController {
    @Autowired
    private UserService userService;

    @Autowired
    private SalesService salesService;

    @RequestMapping("/")
    public String index() {
        return "redirect:/home";
    }


    @RequestMapping("/home")
    public String home(Model model){
        List<String[]> listCategory = salesService.listAllSales(2022);
        List<String> category = new ArrayList<>();
        List<Integer> quantityCategory = new ArrayList<>();
        for (int i = 0; i < listCategory.size(); i++) {
            category.add(listCategory.get(i)[1]);
            quantityCategory.add(Integer.valueOf(listCategory.get(i)[0]));
        }
        List<int[]> list = salesService.listSales(2022);
        List<Integer> quantity =new ArrayList<>();
        List<Integer> month = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            month.add(list.get(i)[0]);
            quantity.add(list.get(i)[1]);
        }
        int maxY = Collections.max(quantity);
        model.addAttribute("month", month);
        model.addAttribute("quantity", quantity);
        model.addAttribute("maxQuantity", maxY);
        model.addAttribute("category", category);
        model.addAttribute("quantityCategory", quantityCategory);
        return "home";
    }

    @RequestMapping("/login")
    public String login() throws Exception {
        return "login";
    }
}
