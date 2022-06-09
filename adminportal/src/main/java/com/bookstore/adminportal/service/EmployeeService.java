package com.bookstore.adminportal.service;

import com.bookstore.adminportal.domain.Employee;

import java.util.List;
import java.util.Optional;

public interface EmployeeService {

    List<Employee> findAllEmployeeActive();

    void save(Employee employee);

    Optional<Employee> findById(Long id);
}
