package com.revature.controller;

import java.util.Scanner;

import com.revature.services.models.User;

public class Menu {
	
	private UserController userController;
	private AccountController accountController;
	private Scanner sc;
	String input = "notnull"; // Cannot use input.equals in while loop if input is null
	
	public Menu(UserController userController, AccountController accountController) {
		this.userController = userController;
		this.accountController = accountController;
		this.sc = userController.getSc();
	}

	public void mainMenu() {
		System.out.println("Hello, would you like to login in create a new account?");
		while (!input.equals("login") && !input.equals("create")) {
			System.out.print("Please type \"login\" or \"create\": ");
			input = sc.nextLine();
			if (input.equals("login")) {
				User user = userController.login();
				if (user != null)
					accountMenu(user);
			}
			if (input.equals("create")) {
				userController.createUser();
			}
		}
	}
	
	public void accountMenu(User user) {
		System.out.println("Would you like to view your accounts, apply for a new account, or logout?");
		while (!input.equals("view") && !input.equals("apply") && !input.equals("logout")) {
			System.out.print("Please type \"view\", \"apply\", or \"logout\": ");
			input = sc.nextLine();
			if (input.equals("view")) {
				accountController.viewAccounts(user);
			}
			if (input.equals("apply")) {
				userController.apply();
			}
			if (input.equals("logout")) {
				System.out.println("Goodbye!");
				return;
			}
		}
		
		// Enter account
			// deposit
			// withdrawal
			// transfer funds
			// return to account view
		// Apply for new account
			// return to account view
		// logout
	}
}
