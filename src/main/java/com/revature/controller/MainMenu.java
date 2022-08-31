package com.revature.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.revature.repository.AccountDao;
import com.revature.repository.AccountDaoInterface;
import com.revature.repository.UavDao;
import com.revature.repository.UavDaoInterface;
import com.revature.services.models.Account;
import com.revature.services.models.Bridge;
import com.revature.services.models.Uav;
import com.revature.services.models.User;

public class MainMenu {
	
	private UserController userController;
	private AccountController accountController;
	UavDaoInterface uavDao = new UavDao();
	protected AccountDaoInterface accountDao = new AccountDao();
	protected Uav uav;
	private Scanner sc;
	String input = "notnull"; // Cannot use input.equals in while loop if input is null
	
	public MainMenu(UserController userController, AccountController accountController) {
		this.userController = userController;
		this.accountController = accountController;
		this.sc = userController.getSc();
	}

	public void mainMenu() {
		System.out.println("Hello, would you like to login in create a new account?");
		Boolean logout = false;
		
		while (!logout) {
			System.out.print("Please type \"login\" or \"create\": ");
			input = sc.nextLine();
			if (input.equals("login")) {
				User user = null;
				while(user == null)
					user = userController.login();
					if (user != null) {
						if (user.getAccessType() == 1)
							customerMenu(user);
						if (user.getAccessType() == 2)
							employeeMenu();
						if (user.getAccessType() == 3)
							adminMenu();
				}
				
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
	
	public boolean employeeMenu() {
		Boolean logout = false;
		List<Uav> uavMaster = uavDao.getUavMaster();
		
		
		while (!logout) {
			System.out.print("Search users by \"username\", \"userId\", \"accountId\". Or, you may \"logout\": ");
			input = sc.nextLine();
			
			
			if (input.equals("username")) {
				List<String> userList = new ArrayList<String>();
				for (int i = 0; i < uavMaster.size(); i++) {
					userList.add(uavMaster.get(i).getUsername());
				}
				System.out.print("Enter a username: ");
				input = sc.nextLine();
				while (!userList.contains(input)) {
					System.out.println("Please try another username: ");
					input = sc.nextLine();
				}
				//User customer = userController.userDao.getUserByUsername(input);
				List<Uav> uavList = uavDao.getUavByUsername(input);
				if (uavList.isEmpty()) {
					System.out.println("This user does not have any open accounts.");
				}
				else{
					System.out.println("\nUser: " + uavList.get(0).getUsername() + " UserId: " + uavList.get(0).getUserId() + ". Accounts and balances: ");
					for (int i = 0; i < uavList.size(); i++) {
						System.out.println("Account #" + uavList.get(i).getAccountId() + " Balance: $" + uavList.get(i).getBalance());
					}	
				}
			}
			else if (input.equals("userId")) {
				List<Integer> userIdList = new ArrayList<Integer>();
				for (int i = 0; i < uavMaster.size(); i++) {
					userIdList.add(uavMaster.get(i).getUserId());
				}
				System.out.print("Enter a userId: ");
				
				input = sc.nextLine();
				while (!input.matches("\\d{1,}")) {
					while (!userIdList.contains(Integer.parseInt(input))) {
						System.out.println("Please try another userId: ");
						input = sc.nextLine();
					}
				}
				int inputInt = Integer.parseInt(input);
				List<Uav> uavList = uavDao.getUavByUserId(inputInt);
				if (uavList.isEmpty()) {
					System.out.println("This user does not have any open accounts.");
				}
				else{
					System.out.println("\nUser: " + uavList.get(0).getUsername() + " UserId: " + uavList.get(0).getUserId() + ". Accounts and balances: ");
					for (int i = 0; i < uavList.size(); i++) {
						System.out.println("Account #" + uavList.get(i).getAccountId() + " Balance: $" + uavList.get(i).getBalance());
					}	
				}
				
			}
			else if (input.equals("accountId")) {
				List<Integer> accountIdList = new ArrayList<Integer>();
				for (int i = 0; i < uavMaster.size(); i++) {
					accountIdList.add(uavMaster.get(i).getAccountId());
				}
				System.out.print("Enter an accountId: ");
				
				input = sc.nextLine();
				while (!input.matches("\\d{1,}")) {
					if (!input.matches("\\d{1,}")) {
						System.out.println("Please enter a valid accountId");
						input = sc.nextLine();
						continue;
					}
					while (!accountIdList.contains(Integer.parseInt(input))) {
						System.out.println("Please try another accountId: ");
						input = sc.nextLine();
					}
				}
				int inputInt = Integer.parseInt(input);
				List<Uav> uavList = uavDao.getUavByAccountId(inputInt);
				if (uavList.isEmpty()) {
					System.out.println("This user does not have any open accounts.");
				}
				else{
					System.out.println("\nAccount #" + uavList.get(0).getAccountId() + " Balance: $" + uavList.get(0).getBalance() + " This account is accessed by: ");
					for (int i = 0; i < uavList.size(); i++) {
						System.out.println("UserId:" + uavList.get(i).getUserId() + " Username: " + uavList.get(i).getUsername());
					}	
				}
			}
			else if (input.equals("logout")) {
				logout = true;
			}
		}
		return logout;
	}
	
	public boolean adminMenu() {
		Boolean logout = false;
		List<Uav> uavMaster = uavDao.getUavMaster();
		List<Integer> accountIdList = new ArrayList<Integer>();
		
		
		
		while (!logout) {
			System.out.print("Would you like to \"view\", \"edit\", or \"review\" new applicants? Or, you may \"logout\": ");
			input = sc.nextLine();
			if (input.equals("view")) {
				logout = employeeMenu();
			}
			if (input.equals("edit")) {
				for (int i = 0; i < uavMaster.size(); i++)
					accountIdList.add(uavMaster.get(i).getAccountId());
				System.out.println("Enter the account you would like to enter. Or, go \"back\" or \"logout\".");
				
				while (!input.equals("back") && !logout) {
					input = sc.nextLine();
					if (input.equals("logout")) {
						logout = true;
						break;
					} 
					if (input.equals("back"))
						break;
					
					if (input.matches("\\d+")) {
						if (accountIdList.contains(Integer.parseInt(input))) {
							logout = accountController.enterAccount(Integer.parseInt(input));
							break;
						}
					}
				}
			}
			
			if (input.equals("review")) {
				List<User> userList = userController.userDao.getAllUsers();
				List<Integer> userIdList = new ArrayList<Integer>();
				int userIndex = 0;
				User user = null;
				System.out.println("Accounts with pending application status, userId's:");
				for (int i = 0; i < userList.size(); i++) {
					userIdList.add(userList.get(i).getUserId());
					if (userList.get(i).getStatus().equals("pending"))
						System.out.print(userIdList.get(i) + " ");
				}
				System.out.println("\nEnter the userId you would like to approve. Or, go \"back\" or \"logout\".");
				while (!input.equals("back") && !logout) {
					input = sc.nextLine();
					if (input.equals("logout")) {
						logout = true;
						break;
					} 
					if (input.equals("back"))
						break;
					if (input.matches("\\d+")) {
						
						for (int i = 0; i<userList.size(); i++) {
							if (Integer.parseInt(input) == userList.get(i).getUserId()) {
								userIndex = i;
								break;
							}
						}
						if (userList.get(userIndex).getStatus().equals("pending")) {
							user = userController.userDao.getUserById(Integer.parseInt(input));
							Account newAccount = accountDao.createAccount(user);
							Bridge newBridge = accountDao.createBridge(user, newAccount);
							System.out.println("Account #" + newBridge.getAccountId() + " created for user " + newBridge.getUserId());
							userController.userDao.setDefault(user);
							break;
						} else
							System.out.println("Account number not pending. Try again: ");
						
					}
				}
			}

			if (input.equals("logout")) {
				logout = true;
			}
			if (input.equals("back")) {
				break;
		}
	}
	
		return logout;
		
	}
	
	
	
}
