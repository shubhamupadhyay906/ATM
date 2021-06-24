package com.mindtree.ATMDao;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import java.util.Random;
import java.util.Scanner;

import com.mindtree.ATM.Account;
import com.mindtree.ATM.User;
import com.mindtree.ATMUtility.MyConnection;

public class ATMDaoImp implements ATMDao {

	PreparedStatement preparedStatement;
	Connection connection;
	ResultSet resultSet;
	Properties dataProperty;
	ArrayList<Account> data;

	int accountNo = 0;
	double balanceOfCurrentAccount = 0;

	static Scanner sc = new Scanner(System.in);
	ArrayList<User> user = new ArrayList<User>();

	// ArrayList<Account> miniStatement = new ArrayList<Account>();
	@Override
	public int validateCardNumber(int cardNo) {
		int result = 0;
		try {
			data = new ArrayList<>();

			dataProperty = new Properties();
			dataProperty.load(new FileInputStream(new File(
					"F:\\\\javaeeworkspace\\\\ATM\\\\src\\\\com\\\\mindtree\\\\ATMUtility\\\\sourdeFile.Properties")));
			String validate = dataProperty.getProperty("validateCard");
			connection = MyConnection.getMyConnection();
			preparedStatement = connection.prepareStatement(validate);
			preparedStatement.setInt(1, cardNo);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				result = 1;
				data.add(new Account(resultSet.getInt(1), resultSet.getInt(2), resultSet.getInt(3),
						resultSet.getDouble(4), resultSet.getString(5), resultSet.getString(6)));
			}

		} catch (SQLException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public int withdrawMoney(double amount) {
		System.out.println("Enter 4 digit pin");
		int pin = sc.nextInt();
		if (pin != data.get(0).getPin()) {
			System.out.println("you have entered wrong pin");
			System.exit(0);
		}

		double balance;
		int result = 0;
		if (data.get(0).getBalance() < amount) {
			System.out.println("Insufficient Balance");
			return result;
		} else {
			balance = data.get(0).getBalance();
			balance = balance - amount;
			data.get(0).setBalance(balance);
			System.out.println("Successfully withdrawn : " + amount);
			System.out.println("Current Balance : " + balance);
			// miniStatement.add(new Account(data.get(0).getAccno(),balance,"withdraw"));
			update(data.get(0).getAccno(), balance, "withdraw");
			try {
				preparedStatement = connection.prepareStatement("update account set balance=? where cardNo=?");

				preparedStatement.setDouble(1, balance);
				preparedStatement.setInt(2, data.get(0).getCardNo());
				result = preparedStatement.executeUpdate();
				return result;
			} catch (SQLException e) {
				// TODO Auto-generated catch bloc
				e.printStackTrace();
			}
		}
		return 0;
	}

	@Override
	public int depositMoney(double amountDeposit) {
		System.out.println("Enter the 4 digit pin Pin");
		int pin = sc.nextInt();
		if (pin != data.get(0).getPin()) {
			System.out.println("Entered Wrong pin");
			System.exit(0);
		}

		double balance;
		int result = 0;
		if (amountDeposit < 0) {
			System.out.println("Enter valid amount");
			return result;
		} else {
			balance = data.get(0).getBalance();
			balance = balance + amountDeposit;
			data.get(0).setBalance(balance);
			System.out.println("Deposited amount : " + amountDeposit);
			System.out.println("current balance : " + balance);
			// miniStatement.add(new Account(data.get(0).getAccno(),balance,"withdraw"));
			update(data.get(0).getAccno(), balance, "deposit");
			try {
				preparedStatement = connection.prepareStatement("update account set balance=? where cardNo=?");
				preparedStatement.setDouble(1, balance);
				preparedStatement.setInt(2, data.get(0).getCardNo());
				result = preparedStatement.executeUpdate();
				return result;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return 0;
	}

	@Override
	public int validateAccountNumber(int accno) {
		int result = 0;
		String validate = dataProperty.getProperty("validateAccount");

		try {
			preparedStatement = connection.prepareStatement(validate);
			preparedStatement.setInt(1, accno);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				result = 1;
				accountNo = accno;
				balanceOfCurrentAccount = resultSet.getDouble(5);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public void moneyTransfer() {
		validateAccount();
	}

	public void transferMoney() {
		System.out.println("Enter the money to be transfered");
		double money = sc.nextInt();
		double balance = data.get(0).getBalance();
		balance = balance - money;
		data.get(0).setBalance(balance);
		int acc = data.get(0).getAccno();

		update(acc, balance);

		balanceOfCurrentAccount = balanceOfCurrentAccount + money;
		System.out.println("Successfully " + money + " transfered ");
		// miniStatement.add(new Account(data.get(0).getAccno(),balance,"transfer"));
		update(data.get(0).getAccno(), balance, "transfer");
		update(accountNo, balanceOfCurrentAccount);
	}

	public void update(int accNo, double balance) {
		try {
			preparedStatement = connection.prepareStatement("update account set balance=? where accountNumber=?");
			preparedStatement.setDouble(1, balance);
			preparedStatement.setInt(2, accNo);
			int update = preparedStatement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void validateAccount() {
		System.out.println("Enter the Account Number");
		int accno = sc.nextInt();
		int resultAccno = validateAccountNumber(accno);
		if (resultAccno > 0) {
			transferMoney();
		} else {
			System.out.println("Please enter valid account number");
			validateAccount();
		}

	}

	@Override
	public void changePin() {
		// TODO Auto-generated method stub
		System.out.println("Enter account number");
		int accountNumber = sc.nextInt();
		System.out.println("Enter new pin");
		int newPin = sc.nextInt();
		if (accountNumber == data.get(0).getAccno()) {
			data.get(0).setPin(newPin);
			update(data.get(0).getAccno(), 0.0, "pin change");

		}
		try {
			preparedStatement = connection.prepareStatement("update account set pin=? where accountNumber=?");
			preparedStatement.setInt(1, newPin);
			preparedStatement.setInt(2, accountNumber);
			preparedStatement.executeUpdate();
			System.out.println("New pin updated successfully");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void update(int accNo, double balance, String transType) {
		try {
			preparedStatement = connection.prepareStatement("insert into miniStatement values(?,?,?)");
			preparedStatement.setInt(1, accNo);
			preparedStatement.setDouble(2, balance);
			preparedStatement.setString(3, transType);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void miniStatement(int accountNumber) {
		// TODO Auto-generated method stub

		if (accountNumber == data.get(0).getAccno()) {
			try {
				String miniState = dataProperty.getProperty("miniStatement");
				preparedStatement = connection.prepareStatement(miniState);
				preparedStatement.setInt(1, accountNumber);
				resultSet = preparedStatement.executeQuery();
				System.out.println("----------statement-------------");
				System.out.println("Accno \t" + "Balance  " + "type \t");
				while (resultSet.next()) {

					System.out.println(
							resultSet.getInt(1) + " " + resultSet.getDouble(2) + "   " + resultSet.getString(3));
				}
				System.out.println("---------------------------------");
				System.exit(0);

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	@Override
	public void userDetails(String firstName, String lastName, String dob, String phoneNumber, String aadharNumber,
			String address) {
		try {
			dataProperty = new Properties();
			dataProperty.load(new FileInputStream(new File(
					"F:\\\\javaeeworkspace\\\\ATM\\\\src\\\\com\\\\mindtree\\\\ATMUtility\\\\sourdeFile.Properties")));
			String details = dataProperty.getProperty("newUser");
			connection = MyConnection.getMyConnection();
			preparedStatement = connection.prepareStatement(details);
			preparedStatement.setString(1, firstName);
			preparedStatement.setString(2, lastName);
			preparedStatement.setString(3, dob);
			preparedStatement.setString(4, phoneNumber);
			preparedStatement.setString(5, aadharNumber);
			preparedStatement.setString(6, address);
			preparedStatement.executeUpdate();
			System.out.println("Account created successfully");
			System.out.println("choose 1 for account detail");
			System.out.println("choose 2 to exit");
			int num = sc.nextInt();
			if (num == 1) {
				System.out.println("Enter phone number");
				String phone = sc.next();
				int card = generateCardNumber();
				int accountNo = generateAccountNumber();
				System.out.println("Generated card and account number......");
				System.out.println("Card No\t\t" + "Account No\t\t");
				System.out.print(card + "\t\t");
				System.out.print(accountNo + "\t\t");
				System.out.println();
				System.out.println("Enter new pin for card");
				int pin = sc.nextInt();
				storeAccountDetails(card, accountNo, pin, 0.0, phoneNumber, aadharNumber);
				System.exit(0);
			} else {
				System.exit(0);
			}
		} catch (SQLException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void deleteAccount(String aadharNumber) {
		// TODO Auto-generated method stub
		try {
			dataProperty = new Properties();
			dataProperty.load(new FileInputStream(new File(
					"F:\\\\javaeeworkspace\\\\ATM\\\\src\\\\com\\\\mindtree\\\\ATMUtility\\\\sourdeFile.Properties")));
			String delAccount = dataProperty.getProperty("deleteAccount");
			connection = MyConnection.getMyConnection();
			preparedStatement = connection.prepareStatement(delAccount);
			preparedStatement.setString(1, aadharNumber);
			preparedStatement.executeUpdate();
			System.out.println("Account Number deleted successfully");
			connection.close();
		} catch (SQLException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void updateAddress(String address, String phoneNumber) {
		try {
			dataProperty = new Properties();
			dataProperty.load(new FileInputStream(new File(
					"F:\\\\javaeeworkspace\\\\ATM\\\\src\\\\com\\\\mindtree\\\\ATMUtility\\\\sourdeFile.Properties")));
			String updateAdd = dataProperty.getProperty("updateAddress");
			connection = MyConnection.getMyConnection();
			preparedStatement = connection.prepareStatement(updateAdd);
			preparedStatement.setString(1, address);
			preparedStatement.setString(2, phoneNumber);
			preparedStatement.executeUpdate();
			System.out.println("New Address updated");
			connection.close();
			System.exit(0);
		} catch (SQLException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private int generateCardNumber() {
		int cardNumber = 0;
		Random random = new Random();
		cardNumber = random.nextInt(1000000);
		if (cardNumber < 0) {
			cardNumber = cardNumber * -1;
		}
		if (cardNumber < 100000) {
			cardNumber = cardNumber + 100000;
		}
		return cardNumber;
	}

	private int generateAccountNumber() {
		int accountNumber = 0;
		Random random = new Random();
		accountNumber = random.nextInt(10000000);
		if (accountNumber < 0) {
			accountNumber = accountNumber * -1;
		}
		if (accountNumber < 100000) {
			accountNumber = accountNumber + 1000000;
		}
		return accountNumber;
	}

	public void storeAccountDetails(int cardNumber, int accountNumber, int pin, double bal, String phoneNumber,
			String aadharNumber) {

		try {
			dataProperty = new Properties();
			dataProperty.load(new FileInputStream(new File(
					"F:\\\\javaeeworkspace\\\\ATM\\\\src\\\\com\\\\mindtree\\\\ATMUtility\\\\sourdeFile.Properties")));
			String accountDetails = dataProperty.getProperty("accountDetails");
			connection = MyConnection.getMyConnection();
			preparedStatement = connection.prepareStatement(accountDetails);
			preparedStatement.setInt(1, cardNumber);
			preparedStatement.setInt(2, accountNumber);
			preparedStatement.setInt(3, pin);
			preparedStatement.setDouble(4, bal);
			preparedStatement.setString(5, phoneNumber);
			preparedStatement.setString(6, aadharNumber);
			preparedStatement.executeUpdate();
			System.out.println("------Thank you---------");
			connection.close();
		} catch (IOException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
