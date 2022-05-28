package com.bookstore.userportal.repository;

import com.bookstore.userportal.domain.security.Role;
import org.springframework.data.repository.CrudRepository;


public interface RoleRepository extends CrudRepository<Role, Long> {
	Role findByname(String name);
}
