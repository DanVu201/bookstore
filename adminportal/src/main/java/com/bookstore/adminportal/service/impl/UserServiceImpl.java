package com.bookstore.adminportal.service.impl;

import java.util.Objects;
import java.util.Set;

import com.bookstore.adminportal.domain.security.Role;
import com.bookstore.adminportal.repository.UserRoleRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bookstore.adminportal.domain.User;
import com.bookstore.adminportal.domain.security.UserRole;
import com.bookstore.adminportal.repository.RoleRepository;
import com.bookstore.adminportal.repository.UserRepository;
import com.bookstore.adminportal.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	private static final Logger LOG = LoggerFactory.getLogger(UserService.class);
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	UserRoleRepository userRoleRepository;

	@Override
	public User createUser(User user, Role role) {
		User localUser = userRepository.findByUsername(user.getUsername());
		if (localUser != null) {
			LOG.info("user {} already exists. Nothing will be done.", user.getUsername());
		} else {
			userRepository.save(user);
			Role role1 = roleRepository.findByname(role.getName());
			UserRole userRole = new UserRole();
			if (Objects.nonNull(role1)) {
				userRole = new UserRole(user, role1);

			} else {
				roleRepository.save(role);
				userRole = new UserRole(user, role);
			}
			userRoleRepository.save(userRole);
		}
		return localUser;
	}

	@Override
	public User save(User user) {
		return userRepository.save(user);
	}

	@Override
	public User findByUsername(String username) {
		return userRepository.findByUsername(username);
	}

}
