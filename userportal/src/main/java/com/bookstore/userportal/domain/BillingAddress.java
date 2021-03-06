package com.bookstore.userportal.domain;


import javax.persistence.*;

@Entity
public class BillingAddress {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@Column(columnDefinition = "nvarchar(255)")
	private String BillingAddressName;
	@Column(columnDefinition = "nvarchar(255)")
	private String BillingAddressStreet1;
	@Column(columnDefinition = "nvarchar(255)")
	private String BillingAddressStreet2;
	@Column(columnDefinition = "nvarchar(255)")
	private String BillingAddressCity;
	@Column(columnDefinition = "nvarchar(255)")
	private String BillingAddressState;
	@Column(columnDefinition = "nvarchar(255)")
	private String BillingAddressCountry;
	@Column(columnDefinition = "nvarchar(255)")
	private String BillingAddressZipcode;
	
	@OneToOne
	private Order order;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getBillingAddressName() {
		return BillingAddressName;
	}

	public void setBillingAddressName(String billingAddressName) {
		BillingAddressName = billingAddressName;
	}

	public String getBillingAddressStreet1() {
		return BillingAddressStreet1;
	}

	public void setBillingAddressStreet1(String billingAddressStreet1) {
		BillingAddressStreet1 = billingAddressStreet1;
	}

	public String getBillingAddressStreet2() {
		return BillingAddressStreet2;
	}

	public void setBillingAddressStreet2(String billingAddressStreet2) {
		BillingAddressStreet2 = billingAddressStreet2;
	}

	public String getBillingAddressCity() {
		return BillingAddressCity;
	}

	public void setBillingAddressCity(String billingAddressCity) {
		BillingAddressCity = billingAddressCity;
	}

	public String getBillingAddressState() {
		return BillingAddressState;
	}

	public void setBillingAddressState(String billingAddressState) {
		BillingAddressState = billingAddressState;
	}

	public String getBillingAddressCountry() {
		return BillingAddressCountry;
	}

	public void setBillingAddressCountry(String billingAddressCountry) {
		BillingAddressCountry = billingAddressCountry;
	}

	public String getBillingAddressZipcode() {
		return BillingAddressZipcode;
	}

	public void setBillingAddressZipcode(String billingAddressZipcode) {
		BillingAddressZipcode = billingAddressZipcode;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

}
