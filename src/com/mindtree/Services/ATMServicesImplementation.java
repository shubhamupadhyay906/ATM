package com.mindtree.Services;

import com.mindtree.ATMDao.ATMDaoImp;

public class ATMServicesImplementation implements ATMService {

	static ATMDaoImp dao = new ATMDaoImp();

	@Override
	public int validateCardNumber(int cardNumber) {
		int result = dao.validateCardNumber(cardNumber);
		return result;
	}

	@Override
	public int withdrawMoney(double amount) {
		int result = dao.withdrawMoney(amount);
		return result;
	}

	@Override
	public int depositMoney(double amountDeposit) {
		int result = dao.depositMoney(amountDeposit);
		return result;
	}

	@Override
	public int validateAccountNumber(int accountNumber) {
		int result = dao.validateAccountNumber(accountNumber);
		return result;
	}

	@Override
	public void moneyTransfer() {
		dao.moneyTransfer();
	}

	@Override
	public void changePin() {
		// TODO Auto-generated method stub
		dao.changePin();

	}



	@Override
	public void miniStatement(int accountNumber) {
		// TODO Auto-generated method stub
		dao.miniStatement(accountNumber);
		
	}

	@Override
	public void userDetails(String firstName, String lastName, String dob, String phoneNumber, String aadharNumber, String address) {
		// TODO Auto-generated method stub
		dao.userDetails(firstName,lastName,dob,phoneNumber,aadharNumber,address);
	}

	@Override
	public void deleteAccount(String aadharNumber) {
		// TODO Auto-generated method stub
		dao.deleteAccount(aadharNumber);
		
	}

	@Override
	public void updateAddress(String address, String phoneNumber) {
		// TODO Auto-generated method stub
		dao.updateAddress(address, phoneNumber);
	}

}
