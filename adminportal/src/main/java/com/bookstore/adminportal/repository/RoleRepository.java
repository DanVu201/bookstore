package com.bookstore.adminportal.repository;

import org.springframework.data.repository.CrudRepository;

import com.bookstore.adminportal.domain.security.Role;


public interface RoleRepository extends CrudRepository<Role, Long> {
	Role findByname(String name);
}
