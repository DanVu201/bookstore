package com.bookstore.userportal.service;


import com.bookstore.userportal.domain.Payment;
import com.bookstore.userportal.domain.UserPayment;

public interface PaymentService {
	Payment setByUserPayment(UserPayment userPayment, Payment payment);
}
