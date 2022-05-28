package com.bookstore.adminportal.repository;

import com.bookstore.adminportal.domain.User;
import org.springframework.data.repository.CrudRepository;


public interface UserRepository extends CrudRepository<User, Long> {
	User findByUsername(String username);
}
