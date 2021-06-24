package com.mindtree.ATMDao;

public interface ATMDao {

	public int validateCardNumber(int cardNO);

	public int withdrawMoney(double amount);

	public int depositMoney(double amountDeposit);

	public int validateAccountNumber(int accno);
	
	public void moneyTransfer();
	
	public void changePin();
	
	public void miniStatement(int accountNumber);
	
	public void userDetails(String firstName, String lastName, String dob, 
			String phoneNumber, String aadharNumber,String address);

	
	public void deleteAccount(String aadharNumber);
	
	public void updateAddress(String address, String phoneNumber);
	
}
