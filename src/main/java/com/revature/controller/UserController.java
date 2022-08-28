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
	public User login() {
		System.out.println("Please login by typing your username and password.");
		String username = validate.getValidateNotNull("username");
		String password = validate.getValidateNotNull("password");
		System.out.println("Attempting to log you in...");
		User user = validate.validateLogin(username, password);
		if (user != null) {
			System.out.println("Login Successful! Welcome, " + username + ".");
		} else {
			System.out.println("Failed Login!");
		}
		return user;
	}
	
	public void createUser() {
		String newUsername = validate.validateUnique("username");
		String newPassword = validate.getValidateNotNull("password");
		String newFirstName = validate.getValidateNotNull("FirstName");
		String newLastName= validate.getValidateNotNull("LastName");
		String newEmail = validate.validateUnique("Email");
		User newUser = new Customer(newUsername, newPassword, newFirstName, newLastName, newEmail);
		userDao.createUser(newUser);
		System.out.println("User successfully created! \nReturning you to main menu.");
	}
	

	public Scanner getSc() {
		return sc;
	}

	public void apply() {
		
		
	}

}
