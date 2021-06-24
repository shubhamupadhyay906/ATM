package com.mindtree.ATM;

public class User {
	private String firstName;
	private String lastName;
	private String dob;
	private String phoneNumber;
	private String address;
	private String aadharNumber;
	
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}

	public User(String firstName, String lastName, String dob, String phoneNumber, String address,
			String aadharNumber) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.dob = dob;
		this.phoneNumber = phoneNumber;
		this.address = address;
		this.aadharNumber = aadharNumber;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getDob() {
		return dob;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getAadharNumber() {
		return aadharNumber;
	}

	public void setAadharNumber(String aadharNumber) {
		this.aadharNumber = aadharNumber;
	}

	
}
