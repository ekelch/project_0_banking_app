package com.revature.repository;

import com.revature.services.models.Account;
import com.revature.services.models.User;

public interface AccountDaoInterface {

	//create account
	Account createAccount(User user);
	
	//read account
	Account getAccount(int accountId);
	
	//update account
	Account updateAccount(int accountId);
	
	//delete account
	Account deleteAccount(int accountId);
	
}
