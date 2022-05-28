package com.bookstore.userportal.service.impl;

import com.bookstore.userportal.domain.UserShipping;
import com.bookstore.userportal.repository.UserShippingRepository;
import com.bookstore.userportal.service.UserShippingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserShippingServiceImpl implements UserShippingService {
	
	@Autowired
	private UserShippingRepository userShippingRepository;
	
	
	public UserShipping findById(Long id) {
		return userShippingRepository.findById(id).orElse(null);
	}
	
	public void deleteById(Long id) {
		userShippingRepository.deleteById(id);
	}

}
