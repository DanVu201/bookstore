package com.bookstore.userportal.service;


import com.bookstore.userportal.domain.UserShipping;

public interface UserShippingService {
	UserShipping findById(Long id);
	
	void deleteById(Long id);
}
