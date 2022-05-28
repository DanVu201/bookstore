package com.bookstore.userportal.service;


import com.bookstore.userportal.domain.UserPayment;

public interface UserPaymentService {
	UserPayment findById(Long id);
	
	void deteteById(Long id);

	void deleteById(Long id);
}
