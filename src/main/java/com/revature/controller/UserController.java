package com.revature.controller;

import java.util.List;
import java.util.Scanner;

import com.revature.repository.UserDaoInterface;
import com.revature.services.models.Customer;
import com.revature.services.models.User;

public class UserController implements UserInputInterface, LoginInterface{

	private Scanner sc;
	private UserDaoInterface userDao;
	
	public UserController(Scanner sc, UserDaoInterface userDao) {
		super();
		this.sc = sc;
		this.userDao = userDao;
	}

	@Override
	public boolean login() {
		System.out.println("Please login by typing your username and password.");
		String username = getUserInput("username");
		String password = getUserInput("password");
		System.out.println("Attempting to log you in...");
		User user = validateLogin(username, password);
		if (user != null) {
			System.out.println("Login Successful!");
			System.out.println("Welcome, " + username +".");
		} else {
			System.out.println("Failed Login!");
		}
		return user!=null;
	}
	
	public void createUser() {
		String newUsername = validateNewUsername();
		String newPassword = getUserInput("password");
		String newFirstName = getUserInput("FirstName");
		String newLastName= getUserInput("LastName");
		String newEmail = getUserInput("Email");
		User newUser = new Customer(newUsername, newPassword, newFirstName, newLastName, newEmail);
		System.out.println(newUser);
		
	}
	
	private String validateNewUsername() {
		String newUsername = null;
		Boolean validUsername = false;
		List<String> userList = userDao.getAllUsernames();
		
		System.out.print("Please choose your username: ");
		
		while (!validUsername) {
		
			newUsername = sc.nextLine();
			
			if (newUsername.isBlank() || newUsername.equals(null)) {
				System.out.println("Please type a username including at least one character.");
				continue;
			}
			if (userList.contains(newUsername)) {
				System.out.println("Sorry, this username is unavailable. Please try again.");
				continue;
			}
			validUsername = true;
		}
		return newUsername;
	}

	@Override
	public String getUserInput(String input) {
		System.out.print("Please enter your " + input + ": ");
		return sc.nextLine();
	}
	
	@Override
	public User validateLogin(String username, String password) {
		
		User user = userDao.getUser(username, password);
		if (user.getUsername().equals(username) && user.getPassword().equals(password))
			return user;
		else
			return null;
		
	}

	public Scanner getSc() {
		return sc;
	}

}
