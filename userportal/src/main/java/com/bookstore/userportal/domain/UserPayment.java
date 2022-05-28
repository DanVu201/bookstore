package com.bookstore.userportal.domain;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class UserPayment {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@Column(columnDefinition = "nvarchar(255)")
	private String type;
	@Column(columnDefinition = "nvarchar(255)")
	private String cardName;
	@Column(columnDefinition = "nvarchar(255)")
	private String cardNumber;
	private int expiryMonth;
	private int expiryYear;
	private int cvc;
	@Column(columnDefinition = "nvarchar(255)")
	private String holderName;
	private boolean defaultPayment;
	
	@ManyToOne
	@JoinColumn(name="user_id")
	private User user;

	
	
}
