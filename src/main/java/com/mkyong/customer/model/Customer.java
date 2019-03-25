package com.mkyong.customer.model;

import java.util.Date;

public class Customer implements java.io.Serializable {

	private Long customerId;
	private String 姓名;
	private String address;
	private Date createdDate;

	public Customer() {
	}

	public Customer(String name, String address, Date createdDate) {
		this.姓名 = name;
		this.address = address;
		this.createdDate = createdDate;
	}

	public Long getCustomerId() {
		return this.customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	public String get姓名() {
		return this.姓名;
	}

	public void set姓名(String name) {
		this.姓名 = name;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Date getCreatedDate() {
		return this.createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

}
