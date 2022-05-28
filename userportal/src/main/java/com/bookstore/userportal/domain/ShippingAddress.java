package com.bookstore.userportal.domain;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class ShippingAddress {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String ShippingAddressName;
	private String ShippingAddressStreet;
	@OneToOne
	private Order order;



}
