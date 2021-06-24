package com.mindtree.ATMApp;

import java.util.Scanner;

import com.mindtree.Services.ATMServicesImplementation;

public class ATMApllication {

	static ATMServicesImplementation service = new ATMServicesImplementation();
	static Scanner scan = new Scanner(System.in);
	//static MainMenu menu  = new MainMenu();

	public static void main(String[] args) {
		
		boolean flag = true;
		
		do {
			DisplayMenu displayMenu = new DisplayMenu();
			displayMenu.display();
			System.out.println("Enter your choice");
			byte choice = scan.nextByte();
			switch (choice) {
			case 1:
				MainMenu.caseNewMethod();
				break;

			case 2:
				if (validateCard() > 0) {
					MainMenu.caseMethod();
				}
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
	
	private static int validateCard() {
		System.out.println("Enter card no");
		int cardNo = scan.nextInt();
		int result = service.validateCardNumber(cardNo);
		if (result > 0) {
			return result;
		} else {
			System.out.println("Invalid card");
			result = validateCard();
			return result;
		}
	}
}
