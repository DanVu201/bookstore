package com.bookstore.adminportal.repository;

import com.bookstore.adminportal.domain.Employee;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface EmployeeRepository extends CrudRepository<Employee, Long> {

    List<Employee> findAllByActive(Boolean active);
}
