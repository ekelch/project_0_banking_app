package com.revature.controller;

import java.util.List;
import java.util.Scanner;

import com.revature.repository.AccountDaoInterface;
import com.revature.repository.UavDao;
import com.revature.repository.UavDaoInterface;
import com.revature.services.models.Account;
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
		
		while (!input.equals("back") && !input.equals("logout") && !logout) {			//Entering specific account or going back
			
			System.out.print("You have access to the following accounts: "); 	//Printing account numbers for user
			for (int i = 0; i < accountList.size(); i++)
				System.out.print(accountList.get(i) + " ");
			System.out.println();
			System.out.println("Enter the account you would like to enter, or type \"back\": ");
			
			input = sc.nextLine();
			if (input.matches("\\d{1,}")) {
				logout = enterAccount(Integer.parseInt(input));
			}
			if (input.equals("logout"))
				logout = true;
		}
		return logout;
	}
	
	public Boolean enterAccount(int accountId) {
		Boolean logout = false;
		String input = "notnull"; // For access to String.equals !=null
		Account account = aDao.getAccount(accountId);
		System.out.println("Current balance in account \"" + account.getAccountId() + "\" is: $" + account.getBalance());
		
		while (!input.equals("back") && !input.equals("logout")) {
			System.out.println("Would you like to \"withdrawal\", \"deposit\", or \"transfer\" funds? You may also go \"back\" or \"logout\"");
			input = sc.nextLine();
			if (input.equals("deposit")) {
				System.out.println("now depositing funds");
			}
			if (input.equals("withdrawal")) {
				System.out.println("now withdrawaling funds");
			}
			if (input.equals("transfer")) {
				System.out.println("now transferring funds");
			}
			if (input.equals("logout")) {
				logout = true;
			}
		}
		return logout;
	}
	
}
