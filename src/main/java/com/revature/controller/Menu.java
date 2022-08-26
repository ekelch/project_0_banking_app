package com.revature.controller;

import java.util.Scanner;

public class Menu {
	
	private UserController userController;
	private Scanner sc;
	
	public Menu(UserController userController) {
		this.userController = userController;
		this.sc = userController.getSc();
	}

	public void enterMenu() {
		String input = "notnullvalue"; // Cannot use input.equals in while loop if input is null
		System.out.println("Hello, would you like to login in create a new account?");
		while (!input.equals("input") || !input.equals("create")) {
			System.out.print("Please type \"login\" or \"create\": ");
			input = sc.nextLine();
			if (input.equals("login")) {
				userController.login();
				break;
			}
			if (input.equals("create")) {
				userController.createUser();
				break;
			}
		}
	}
}
