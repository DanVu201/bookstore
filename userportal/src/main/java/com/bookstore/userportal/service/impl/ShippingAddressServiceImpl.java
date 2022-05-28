package com.bookstore.userportal.service.impl;

import com.bookstore.userportal.domain.ShippingAddress;
import com.bookstore.userportal.domain.UserShipping;
import com.bookstore.userportal.service.ShippingAddressService;
import org.springframework.stereotype.Service;


@Service
public class ShippingAddressServiceImpl implements ShippingAddressService {
	
	public ShippingAddress setByUserShipping(UserShipping userShipping, ShippingAddress shippingAddress) {
		
		shippingAddress.setShippingAddressName(userShipping.getUserShippingName());
		shippingAddress.setShippingAddressStreet1(userShipping.getUserShippingStreet1());
		shippingAddress.setShippingAddressStreet2(userShipping.getUserShippingStreet2());
		shippingAddress.setShippingAddressCity(userShipping.getUserShippingCity());
		shippingAddress.setShippingAddressState(userShipping.getUserShippingState());
		shippingAddress.setShippingAddressCountry(userShipping.getUserShippingCountry());
		shippingAddress.setShippingAddressZipcode(userShipping.getUserShippingZipcode());
		
		return shippingAddress;
	}
}
