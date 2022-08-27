package com.revature.controller;

import java.util.Scanner;

import com.revature.repository.UserDaoInterface;
import com.revature.services.models.Customer;
import com.revature.services.models.User;

public class UserController implements LoginInterface{

	protected Scanner sc;
	protected UserDaoInterface userDao;
	Validate validate;
	
	public UserController(Scanner sc, UserDaoInterface userDao) {
		super();
		this.sc = sc;
		this.userDao = userDao;
		validate = new Validate(sc, userDao);
	}

	@Override
	public boolean login() {
		System.out.println("Please login by typing your username and password.");
		String username = validate.getValidateNotNull("username");
		String password = validate.getValidateNotNull("password");
		System.out.println("Attempting to log you in...");
		User user = validate.validateLogin(username, password);
		if (user != null) {
			System.out.println("Login Successful!");
			System.out.println("Welcome, " + username +".");
		} else {
			System.out.println("Failed Login!");
		}
		return user!=null;
	}
	
	public void createUser() {
		String newUsername = validate.validateUnique("username");
		String newPassword = validate.getValidateNotNull("password");
		String newFirstName = validate.getValidateNotNull("FirstName");
		String newLastName= validate.getValidateNotNull("LastName");
		String newEmail = validate.validateUnique("Email");
		User newUser = new Customer(newUsername, newPassword, newFirstName, newLastName, newEmail);
		System.out.println(newUser);
	}
	

	public Scanner getSc() {
		return sc;
	}

}
