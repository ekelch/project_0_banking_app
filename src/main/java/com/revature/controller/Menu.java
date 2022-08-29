package com.revature.controller;

import java.util.List;
import java.util.Scanner;

import com.revature.repository.AccountDao;
import com.revature.repository.AccountDaoInterface;
import com.revature.repository.UavDao;
import com.revature.repository.UavDaoInterface;
import com.revature.services.models.Uav;
import com.revature.services.models.User;

public class Menu {
	
	private UserController userController;
	private AccountController accountController;
	UavDaoInterface uavDao = new UavDao();
	protected AccountDaoInterface accountDao = new AccountDao();
	protected Uav uav;
	private Scanner sc;
	String input = "notnull"; // Cannot use input.equals in while loop if input is null
	
	public Menu(UserController userController, AccountController accountController) {
		this.userController = userController;
		this.accountController = accountController;
		this.sc = userController.getSc();
	}

	public void mainMenu() {
		System.out.println("Hello, would you like to login in create a new account?");
		while (!input.equals("logout")) {
			System.out.print("Please type \"login\" or \"create\": ");
			input = sc.nextLine();
			if (input.equals("login")) {
				User user = userController.login();
				if (user.getAccessType() == 1)
					customerMenu(user);
				if (user.getAccessType() == 2)
					employeeMenu(user);
				if (user.getAccessType() == 3)
					adminMenu(user);
			}
			if (input.equals("create")) {
				userController.createUser();
			}
		}
	}
	
	public boolean customerMenu(User user) {
		Boolean logout = false;
		while (!logout) {
			System.out.print("Would you like to \"view\" your accounts, \"apply\" for a new account, or \"logout\": ");
			input = sc.nextLine();
			if (input.equals("view")) {
				logout = accountController.viewAccounts(user);
			}
			if (input.equals("apply")) {
				user = userController.userDao.setPending(user);
				System.out.println("Your new application status is: " + user.getStatus());
			}
			if (input.equals("logout")) {
				logout = true;
			}
		}
		return logout;
	}
	
	public boolean employeeMenu(User user) {
		Boolean logout = false;
		while (!logout) {
			System.out.print("Search users by \"username\", \"userId\", \"accountId\". Or, you may \"logout\": ");
			input = sc.nextLine();
			if (input.equals("username")) {
				List<String> userList = userController.userDao.getUsersColumn("username");
				System.out.print("Enter a username: ");
				input = sc.nextLine();
				while (!userList.contains(input) && user.getStatus().equals("pending")) { // type evan, pending account fails
					System.out.println("Please try another username: ");
					input = sc.nextLine();
				}
				//User customer = userController.userDao.getUserByUsername(input);
				List<Uav> uavList = uavDao.getUavByUsername(input);
				System.out.println("User: " + uavList.get(0).getUsername() + " UserId: " + uavList.get(0).getUserId() + ". Accounts and balances: ");
				for (int i = 0; i < uavList.size(); i++) {
					System.out.println("Account #" + uavList.get(i).getAccountId() + " Balance: $" + uavList.get(i).getBalance());
				}
				
				
			}
			if (input.equals("userId")) {
				System.out.print("Enter a userId: ");
			}
			if (input.equals("accountId")) {
				System.out.print("Enter a accountId: ");
			}
			if (input.equals("logout")) {
				logout = true;
			}
		}
		return logout;
	}
	
	public boolean adminMenu(User user) {
		return false;
	}
	
	
}
