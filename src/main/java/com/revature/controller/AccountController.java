package com.revature.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.revature.repository.AccountDaoInterface;
import com.revature.repository.UavDao;
import com.revature.repository.UavDaoInterface;
import com.revature.services.models.Account;
import com.revature.services.models.Uav;
import com.revature.services.models.User;

public class AccountController {

	Scanner sc;
	AccountDaoInterface aDao;
	UavDaoInterface uavDao = new UavDao();
	
	public AccountController(Scanner sc, AccountDaoInterface aDao) {
		this.sc = sc;
		this.aDao = aDao;
	}
	
	
	public Boolean viewAccounts(User user) {
		Boolean logout = false;
		String input = "notnull"; // For access to String.equals !=null
		List<Integer> accountList = uavDao.getAccountIdbyUser(user); 	//Getting account number list for user
		
		System.out.print("You have access to the following accounts: "); 	//Printing account numbers for user
		for (int i = 0; i < accountList.size(); i++)
			System.out.print(accountList.get(i) + " ");
		System.out.println();
		System.out.println("Enter the account you would like to enter. Or, go \"back\" or \"logout\".");
		
		while (!input.equals("back") && !logout) {			//Entering specific account or going back
			input = sc.nextLine();
			if (input.equals("logout")) {
				logout = true;
				break;
			} 
			if (input.equals("back"))
				break;
			
			if (input.matches("\\d+")) {
				if (accountList.contains(Integer.parseInt(input))) {
					logout = enterAccount(Integer.parseInt(input));
					break;
				} else
					System.out.println("That account does not belong to you.");
			}
			
		}
		return logout;
	}
	
	public Boolean enterAccount(int accountId) {
		Boolean logout = false;
		String input = "notnull"; // For access to String.equals !=null
		Account account = aDao.getAccount(accountId);
		System.out.println("Current balance in account \"" + account.getAccountId() + "\" is: $" + account.getBalance());
		
		while (!input.equals("back") && !logout) {
			System.out.println("Would you like to \"withdrawal\", \"deposit\", or \"transfer\" funds? You may also go \"back\" or \"logout\"");
			input = sc.nextLine();
			if (input.equals("deposit")) {
				deposit(account);
			}
			if (input.equals("withdrawal")) {
				withdrawal(account);
			}
			if (input.equals("transfer")) {
				List<Uav> uavMaster = uavDao.getUavMaster();
				List<Integer> accountIdList = new ArrayList<Integer>();
				for (int i = 0; i < uavMaster.size(); i++) {
					accountIdList.add(uavMaster.get(i).getAccountId());
				}
				
				System.out.print("Enter an accountId: ");
				input = sc.nextLine();
				while (!input.matches("\\d+")) {
					if (!input.matches("\\d{1,}")) {
						System.out.print("Please enter a valid accountId: ");
						input = sc.nextLine();
						continue;
					}
					while (!accountIdList.contains(Integer.parseInt(input))) {
						System.out.print("Please try another accountId: ");
						input = sc.nextLine();
					}
				}
				System.out.println("Input Accepted.");
				transfer(account, aDao.getAccount(Integer.parseInt(input)));
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
	
	public boolean deposit(Account account) {
		boolean logout = false;
		System.out.print("Enter the amount you would like to deposit in format: DOLLAR:CENTS");
		String input = "toaster";
		while (!logout && !input.equals("back")) {
			input = sc.nextLine();
			if (input.equals("logout")) {
				logout = true;
				break;
			}
			if (input.equals("back")) {
				break;
			}
			if (input.matches("\\d+[.]\\d{1,2}")) {
				BigDecimal addBalance = new BigDecimal(input);
				account.setBalance(account.getBalance().add(addBalance));
				System.out.println(("Your new balance for account #" + account.getAccountId() + " is: $" + account.getBalance()));
				aDao.updateAccount(account);
				break;
			} else {
				System.out.print("Please enter a valid amount: ");
			}
		}
		return logout;
	}
	
	
	
	public boolean withdrawal(Account account) {
		boolean logout = false;
		System.out.println("Enter the amount you would like to withdraw in format: DOLLAR:CENTS");
		String input = "toaster";
		while (!logout && !input.equals("back")) {
			input = sc.nextLine();
			if (input.equals("logout")) {
				logout = true;
				break;
			}
			if (input.equals("back")) {
				break;
			}
			if (input.matches("\\d+[.]\\d{1,2}")) {
				BigDecimal subtractBalance = new BigDecimal(input);
				account.setBalance(account.getBalance().subtract(subtractBalance));
				System.out.println(("Your new balance for account #" + account.getAccountId() + " is: $" + account.getBalance()));
				aDao.updateAccount(account);
				break;
			} else {
				System.out.print("Please enter a valid amount: ");
			}
		}
		return logout;
	}
	
	public boolean transfer(Account account1, Account account2) {
		String input = "toaster";
		System.out.print("Enter the amount you would like to transfer to account #" + account2.getAccountId() + " in format: DOLLAR:CENTS: ");
		boolean logout = false;
		while(!logout && !input.equals("back")) {
			input = sc.nextLine();
			if (input.equals("logout")) {
				logout = true;
				break;
			}
			if (input.equals("back")) {
				break;
			}
			if (input.matches("\\d+[.]\\d{1,2}")) {
				BigDecimal difference = new BigDecimal(input);
				account1.setBalance(account1.getBalance().subtract(difference));
				account2.setBalance(account2.getBalance().add(difference));
				aDao.updateAccount(account1);
				aDao.updateAccount(account2);
				System.out.println(("Transfer successful. Your new balance for account #" + account1.getAccountId() + " is: $" + account1.getBalance()));
				break;
			} else {
				System.out.print("Please enter a valid amount: ");
			}
		}	
			return logout;
		}
	}

