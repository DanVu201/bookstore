package com.bookstore.adminportal.controller;

import com.bookstore.adminportal.domain.Book;
import com.bookstore.adminportal.domain.Employee;
import com.bookstore.adminportal.domain.User;
import com.bookstore.adminportal.domain.security.Role;
import com.bookstore.adminportal.dto.EmployeeRequest;
import com.bookstore.adminportal.service.EmployeeService;
import com.bookstore.adminportal.service.UserService;
import com.bookstore.adminportal.utility.SecurityUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private UserService userService;

    @GetMapping("/employeeList")
    public String getEmployeeList(Model model) {
        List<Employee> employees = employeeService.findAllEmployeeActive();
        List<EmployeeRequest> employeeRequests = new ArrayList<>();
        for (Employee employee : employees) {
            EmployeeRequest employeeRequest = EmployeeRequest.builder()
                    .id(employee.getId())
                    .cartICNumber(employee.getCartICNumber())
                    .startDate(employee.getStartDate())
                    .employeeName(employee.getEmployeeName())
                    .address(employee.getAddress())
                    .sex(employee.getSex())
                    .phoneNumber(employee.getPhoneNumber())
                    .salary(employee.getSalary())
                    .email(employee.getUser().getEmail())
                    .build();
            employeeRequests.add(employeeRequest);
        }
        model.addAttribute("employees", employeeRequests);
        return "employeeList";
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String addNew(Model model) {
        EmployeeRequest employeeRequest = new EmployeeRequest();
        model.addAttribute("employee", employeeRequest);
        return "addEmployee";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String addNewPost(@ModelAttribute("employee") EmployeeRequest employeeRequest,
                             Model model) throws Exception {
        User user = new User();
        user.setUsername(employeeRequest.getUsername());
        user.setPassword(SecurityUtility.passwordEncoder().encode(employeeRequest.getPassword()));
        user.setEmail(employeeRequest.getEmail());
        Role role = new Role();
        role.setName("ROLE_EMPLOYEE");
        userService.createUser(user, role);

        Employee employee = Employee.builder()
                .cartICNumber(employeeRequest.getCartICNumber())
                .startDate(employeeRequest.getStartDate())
                .employeeName(employeeRequest.getEmployeeName())
                .phoneNumber(employeeRequest.getPhoneNumber())
                .address(employeeRequest.getAddress())
                .salary(employeeRequest.getSalary())
                .sex(employeeRequest.getSex())
                .active(employeeRequest.getActive())
                .orderList(new ArrayList<>())
                .user(user)
                .build();
        employeeService.save(employee);
        return "redirect:/employee/employeeList";
    }

    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public String editEmplouee(@RequestParam("id") Long id, Model model) {
        Employee employee = employeeService.findById(id).get();
        EmployeeRequest employeeRequest = EmployeeRequest.builder()
                .id(employee.getId())
                .cartICNumber(employee.getCartICNumber())
                .startDate(employee.getStartDate())
                .employeeName(employee.getStartDate())
                .address(employee.getAddress())
                .phoneNumber(employee.getPhoneNumber())
                .salary(employee.getSalary())
                .sex(employee.getSex())
                .userId(employee.getUser().getId())
                .username(employee.getUser().getUsername())
                .password(employee.getUser().getPassword())
                .email(employee.getUser().getEmail())
                .build();
        model.addAttribute("employee", employeeRequest);
        return "editEmployee";
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public String editEmployee(@ModelAttribute("employee") EmployeeRequest employeeRequest)  {
        User user = userService.findById(employeeRequest.getUserId()).get();
        user.setUsername(employeeRequest.getUsername());
        user.setPassword(SecurityUtility.passwordEncoder().encode(employeeRequest.getPassword()));
        user.setEmail(employeeRequest.getEmail());
        userService.save(user);

        Employee employee = employeeService.findById(employeeRequest.getId()).get();
        employee.setCartICNumber(employeeRequest.getCartICNumber());
        employee.setStartDate(employeeRequest.getStartDate());
        employee.setEmployeeName(employeeRequest.getEmployeeName());
        employee.setAddress(employeeRequest.getAddress());
        employee.setPhoneNumber(employeeRequest.getPhoneNumber());
        employee.setSalary(employeeRequest.getSalary());
        employee.setSex(employeeRequest.getSex());

        employeeService.save(employee);
        return "redirect:/employee/employeeList";
    }

    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public String remove(@RequestParam(value = "id") Long id) {
        Employee employee = employeeService.findById(id).get();
        employee.setActive(false);
        employeeService.save(employee);

        return "redirect:/employee/employeeList";
    }

}


