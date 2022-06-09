package com.bookstore.adminportal.repository;

import com.bookstore.adminportal.domain.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;


public interface UserRepository extends CrudRepository<User, Long> {
	User findByUsername(String username);

	Optional<User> findById(Long id);
}
