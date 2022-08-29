package com.revature.controller;

import java.util.List;
import java.util.Scanner;

import com.revature.repository.AccountDaoInterface;
import com.revature.repository.BridgeDao;
import com.revature.services.models.Account;
import com.revature.services.models.User;

public class AccountController {

	BridgeDao bridgeDao = new BridgeDao();
	Scanner sc;
	AccountDaoInterface aDao;
	
	public AccountController(Scanner sc, AccountDaoInterface aDao) {
		this.sc = sc;
		this.aDao = aDao;
	}
	
	
	public void viewAccounts(User user) {
		String input = "notnull"; // For access to String.equals !=null
		List<Integer> accountList = bridgeDao.getAccountIdFromUser(user); 	//Getting account number list for user
		
		System.out.print("You have access to the following accounts: "); 	//Printing account numbers for user
		for (int i = 0; i < accountList.size(); i++)
			System.out.print(accountList.get(i) + " ");
		System.out.println();
		
		while (!input.equals("logout") && !input.equals("back")) {			//Entering specific account or going back
			//System.out.println("Enter the account you would like to enter, or type \"back\": ");
			input = sc.nextLine();
			if (accountList.contains(Integer.parseInt(input))) {			//If user entry matches account on record...
				enterAccount(Integer.parseInt(input));
			}
			if (input.equals("back")) {	
				return;
			}
			
		}
	}
	
	public void enterAccount(int accountId) {
		String input = "notnull"; // For access to String.equals !=null
		Account account = aDao.getAccount(accountId);
		System.out.println("Current balance in account \"" + account.getAccountId() + "\" is: " + account.getBalance() + ".");
		
		while (!input.equals("withdrawal") && !input.equals("deposit")) {
			input = sc.nextLine();
			System.out.println("Would you like to \"withdrawal\", \"deposit\", or \"transfer\" funds to another account?");
			if (input.equals("deposit")) {
				System.out.println("now depositing funds");
			}
			if (input.equals("withdrawal")) {
				System.out.println("now withdrawaling funds");
			}
			if (input.equals("transfer")) {
				System.out.println("now transferring funds");
			}
			if (input.equals("back")) {	
				return;
			}
			
		}
	}
	
}
