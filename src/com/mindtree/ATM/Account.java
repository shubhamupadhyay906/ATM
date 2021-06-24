package com.mindtree.ATM;

public class Account {

	private int cardNo;
	private int accno;
	private int pin;
	private double balance;
	private String phoneNumber;
	private String aadharNumber;
	private String transType;

	public Account() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Account(int cardNo, int accno, int pin, double balance, String phoneNumber, String aadharNumber) {
		super();
		this.cardNo = cardNo;
		this.accno = accno;
		this.pin = pin;
		this.balance = balance;
		this.phoneNumber = phoneNumber;
		this.aadharNumber = aadharNumber;
	}

	public int getCardNo() {
		return cardNo;
	}

	public void setCardNo(int cardNo) {
		this.cardNo = cardNo;
	}

	public int getAccno() {
		return accno;
	}

	public void setAccno(int accno) {
		this.accno = accno;
	}

	public int getPin() {
		return pin;
	}

	public void setPin(int pin) {
		this.pin = pin;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getAadharNumber() {
		return aadharNumber;
	}

	public void setAadharNumber(String aadharNumber) {
		this.aadharNumber = aadharNumber;
	}

	public String getTransType() {
		return transType;
	}

	public void setTransType(String transType) {
		this.transType = transType;
	}
	

}