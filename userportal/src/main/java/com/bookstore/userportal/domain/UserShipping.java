package com.bookstore.userportal.domain;

import javax.persistence.*;

@Entity
public class UserShipping {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@Column(columnDefinition = "nvarchar(255)")
	private String userShippingName;
	@Column(columnDefinition = "nvarchar(255)")
	private String userShippingStreet1;
	@Column(columnDefinition = "nvarchar(255)")
	private String userShippingStreet2;
	@Column(columnDefinition = "nvarchar(255)")
	private String userShippingCity;
	@Column(columnDefinition = "nvarchar(255)")
	private String userShippingState;
	@Column(columnDefinition = "nvarchar(255)")
	private String userShippingCountry;
	@Column(columnDefinition = "nvarchar(255)")
	private String userShippingZipcode;
	private boolean userShippingDefault;
	
	
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getUserShippingName() {
		return userShippingName;
	}


	public void setUserShippingName(String userShippingName) {
		this.userShippingName = userShippingName;
	}


	public String getUserShippingStreet1() {
		return userShippingStreet1;
	}


	public void setUserShippingStreet1(String userShippingStreet1) {
		this.userShippingStreet1 = userShippingStreet1;
	}


	public String getUserShippingStreet2() {
		return userShippingStreet2;
	}


	public void setUserShippingStreet2(String userShippingStreet2) {
		this.userShippingStreet2 = userShippingStreet2;
	}


	public String getUserShippingCity() {
		return userShippingCity;
	}


	public void setUserShippingCity(String userShippingCity) {
		this.userShippingCity = userShippingCity;
	}


	public String getUserShippingState() {
		return userShippingState;
	}


	public void setUserShippingState(String userShippingState) {
		this.userShippingState = userShippingState;
	}


	public String getUserShippingCountry() {
		return userShippingCountry;
	}


	public void setUserShippingCountry(String userShippingCountry) {
		this.userShippingCountry = userShippingCountry;
	}


	public String getUserShippingZipcode() {
		return userShippingZipcode;
	}


	public void setUserShippingZipcode(String userShippingZipcode) {
		this.userShippingZipcode = userShippingZipcode;
	}


	public User getUser() {
		return user;
	}


	public void setUser(User user) {
		this.user = user;
	}


	public boolean isUserShippingDefault() {
		return userShippingDefault;
	}


	public void setUserShippingDefault(boolean userShippingDefault) {
		this.userShippingDefault = userShippingDefault;
	}
	
	
	
	
}
