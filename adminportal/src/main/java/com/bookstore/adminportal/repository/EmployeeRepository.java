package com.bookstore.adminportal.repository;

import com.bookstore.adminportal.domain.Employee;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface EmployeeRepository extends CrudRepository<Employee, Long> {

    List<Employee> findAllByActive(Boolean active);

    Optional<Employee> findById(Long id);
}
