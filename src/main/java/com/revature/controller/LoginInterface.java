package com.revature.controller;

import com.revature.services.models.User;

public interface LoginInterface {

	// Validate user input
	
	boolean login();
	
	User validateLogin(String username, String password);
	
}