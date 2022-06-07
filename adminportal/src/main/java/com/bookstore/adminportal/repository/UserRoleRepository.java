package com.bookstore.adminportal.repository;

import com.bookstore.adminportal.domain.security.UserRole;
import org.springframework.data.repository.CrudRepository;

public interface UserRoleRepository extends CrudRepository<UserRole, Long> {
}
