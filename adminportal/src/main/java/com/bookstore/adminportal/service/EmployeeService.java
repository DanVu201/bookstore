package com.bookstore.adminportal.service;

import com.bookstore.adminportal.domain.Employee;

import java.util.List;

public interface EmployeeService {

    List<Employee> findAllEmployeeActive();

    void save(Employee employee);
}
