package com.mindtree.ATMApp;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.mindtree.Services.ATMServicesImplementation;

public class MainMenu {
	static Scanner scan = new Scanner(System.in);
	static ATMServicesImplementation service = new ATMServicesImplementation();
	static DisplayMenu displayMenu = new DisplayMenu();

	public static void caseMethod() {
		// TODO Auto-generated method stub
		boolean flag = true;

		do {
			
			displayMenu.displayMainMenu();
			System.out.println("Enter your choice");
			byte choice = scan.nextByte();
			switch (choice) {
			case 1:
				System.out.println("Enter the Amount to withdraw");
				double amount = scan.nextDouble();
				service.withdrawMoney(amount);
				break;

			case 2:
				service.moneyTransfer();
				break;

			case 3:
				System.out.println("Enter amount to deposit");
				double amountDeposit = scan.nextDouble();
				service.depositMoney(amountDeposit);
				break;

			case 4:
				System.out.println("Enter Account number");
				int accountNumber = scan.nextInt();
				service.miniStatement(accountNumber);
				break;

			case 5:
				service.changePin();
				break;

			case 6:
				System.out.println(".....Thank you....");
				flag = false;
				break;

			default:
				System.out.println("Invalid choice");
				break;

			}
		} while (flag);

	}

	public static void caseNewMethod() {
		// TODO Auto-generated method stub
		boolean flag = true;

		do {
			
			displayMenu.displayUserMenu();
			System.out.println("Enter your choice");
			byte choice = scan.nextByte();
			switch (choice) {
			case 1:
				System.out.println("Enter first name");
				String firstName = scan.next();
				System.out.println("Enter last name");
				String lastName = scan.next();
				System.out.println("Enter date of birth ");
				String dob = scan.next();
				System.out.println("Enter phone number");
				String phoneNumber = scan.next();
				System.out.println("Enter aadhar number");
				String aadharNumber = scan.next();
				System.out.println("Enter address");
				String address = scan.next();
				service.userDetails(firstName, lastName, dob, phoneNumber, aadharNumber,address);
				break;

			case 2:
				System.out.println("Enter phone number to update");
				String phone = scan.next();
				System.out.println("Enter new address");
				String newAddress = scan.next();
				service.updateAddress(newAddress, phone);
				break;

			case 3:
				System.out.println("Enter aadhar number to delete account");
				String aadharNum = scan.next();
				service.deleteAccount(aadharNum);
				break;

			case 4:
				System.out.println(".....Thank you....");
				flag = false;
				break;

			default:
				System.out.println("Invalid choice");
				break;
			}
		} while (flag);

	}

}
