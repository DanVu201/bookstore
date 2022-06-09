package com.bookstore.adminportal.controller;

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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.List;

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
        for (Employee employee: employees) {
            EmployeeRequest employeeRequest = EmployeeRequest.builder()
                    .id(employee.getId())
                    .cartICNumber(employee.getCartICNumber())
                    .startDate(employee.getStartDate())
                    .employeeName(employee.getEmployeeName())
                    .address(employee.getAddress())
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
        Role role= new Role();
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

}
