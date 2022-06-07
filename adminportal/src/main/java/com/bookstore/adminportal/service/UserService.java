package com.bookstore.adminportal.service;

import java.util.Set;

import com.bookstore.adminportal.domain.security.Role;
import com.bookstore.adminportal.domain.security.UserRole;
import com.bookstore.adminportal.domain.User;

public interface UserService {
	User createUser(User user, Role role) throws Exception;
	
	User save(User user);

	User findByUsername(String username);
}
