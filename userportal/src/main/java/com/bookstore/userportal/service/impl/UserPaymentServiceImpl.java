package com.bookstore.userportal.service.impl;

import com.bookstore.userportal.domain.UserPayment;
import com.bookstore.userportal.repository.UserPaymentRepository;
import com.bookstore.userportal.service.UserPaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
public class UserPaymentServiceImpl implements UserPaymentService {

	@Autowired
	private UserPaymentRepository userPaymentRepository;
		
	public UserPayment findById(Long id) {
		return userPaymentRepository.findById(id).orElse(null);
	}

	@Override
	public void deteteById(Long id) {
		userPaymentRepository.deleteById(id);
	}



	@Override
	public void deleteById(Long id) {
		userPaymentRepository.deleteById(id);
	}
} 
