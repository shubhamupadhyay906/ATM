package com.mindtree.Services;

public interface ATMService {
	
	public int validateCardNumber(int cardNumber);
	
	public int withdrawMoney(double amount);
	
	public int depositMoney(double amountDeposit);
	
	public int validateAccountNumber(int accountNumber);
	
	public void moneyTransfer();
	
	public void changePin();
	
	public void miniStatement(int accountNumber);
	
	public void userDetails(String firstName, String lastName, String dob, 
			String phoneNumber,String aadharNumber, String address);
	
	public void deleteAccount(String aadharNumber);

	public void updateAddress(String address, String phoneNumber);
}
