package com.bookstore.adminportal.service;

import java.util.Set;

import com.bookstore.adminportal.domain.security.UserRole;
import com.bookstore.adminportal.domain.User;

public interface UserService {
	User createUser(User user, Set<UserRole> userRoles) throws Exception;
	
	User save(User user);
}
