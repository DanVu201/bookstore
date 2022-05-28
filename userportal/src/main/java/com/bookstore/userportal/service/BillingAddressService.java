package com.bookstore.userportal.service;


import com.bookstore.userportal.domain.BillingAddress;
import com.bookstore.userportal.domain.UserBilling;

public interface BillingAddressService {
	BillingAddress setByUserBilling(UserBilling userBilling, BillingAddress billingAddress);
}
