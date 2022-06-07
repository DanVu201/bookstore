package com.bookstore.adminportal;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.bookstore.adminportal.domain.User;
import com.bookstore.adminportal.domain.security.Role;
import com.bookstore.adminportal.domain.security.UserRole;
import com.bookstore.adminportal.service.UserService;
import com.bookstore.adminportal.utility.SecurityUtility;

@SpringBootApplication
public class AdminportalApplication implements CommandLineRunner{
	
	@Autowired
	private UserService userService;

	public static void main(String[] args) {
		SpringApplication.run(AdminportalApplication.class, args);
	}
	
	@Override
	public void run(String... args) throws Exception {
		User user = new User();
		user.setUsername("admin");
		user.setPassword(SecurityUtility.passwordEncoder().encode("admin"));
		user.setEmail("admin@gmail.com");
		Role role= new Role();
		role.setName("ROLE_ADMIN");
		userService.createUser(user, role);
	}
}
