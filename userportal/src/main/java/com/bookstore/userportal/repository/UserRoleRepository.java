package com.bookstore.userportal.repository;

import com.bookstore.userportal.domain.security.UserRole;
import org.springframework.data.repository.CrudRepository;

public interface UserRoleRepository extends CrudRepository<UserRole, Long> {
}
