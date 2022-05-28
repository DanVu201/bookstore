package com.bookstore.userportal.service;


import com.bookstore.userportal.domain.ShippingAddress;
import com.bookstore.userportal.domain.UserShipping;

public interface ShippingAddressService {
	ShippingAddress setByUserShipping(UserShipping userShipping, ShippingAddress shippingAddress);
}
