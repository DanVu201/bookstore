package com.bookstore.userportal.service;

import com.bookstore.userportal.domain.User;
import com.bookstore.userportal.domain.UserPayment;
import com.bookstore.userportal.domain.security.PasswordResetToken;
import com.bookstore.userportal.domain.security.UserRole;

import java.util.Set;

public interface UserService {
	PasswordResetToken getPasswordResetToken(final String token);
	
	void createPasswordResetTokenForUser(final User user, final String token);
	
	User findByUsername(String username);
	
	User findByEmail (String email);
	
	User findById(Long id);
	
	User createUser(User user, Set<UserRole> userRoles) throws Exception;
	
	User save(User user);

	
	void setUserDefaultPayment(Long userPaymentId, User user);
	

}
