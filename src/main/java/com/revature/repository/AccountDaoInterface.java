package com.revature.repository;

import java.math.BigDecimal;

import com.revature.services.models.Account;
import com.revature.services.models.Bridge;
import com.revature.services.models.User;

public interface AccountDaoInterface {

	//create account
	Account createAccount(User user);
	Bridge createBridge(User user, Account account);
	
	//read account
	Account getAccount(int accountId);
	Account getAccountByUserId(int userId);
	BigDecimal getBalance(int accountId);
	
	//update account
	Account updateAccount(Account account);
	
	//delete account
	void deleteAccount(int accountId);
	
}
