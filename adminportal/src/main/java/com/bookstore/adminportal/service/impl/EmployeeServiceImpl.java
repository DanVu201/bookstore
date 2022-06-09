package com.bookstore.adminportal.service.impl;

import com.bookstore.adminportal.domain.Employee;
import com.bookstore.adminportal.repository.EmployeeRepository;
import com.bookstore.adminportal.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public List<Employee> findAllEmployeeActive() {
        return employeeRepository.findAllByActive(true);
    }

    @Override
    public void save(Employee employee) {
        employeeRepository.save(employee);
    }
}
