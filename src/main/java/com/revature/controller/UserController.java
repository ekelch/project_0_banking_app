package com.revature.controller;

import java.util.Scanner;

import com.revature.repository.UserDaoInterface;
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
			System.out.println(user);
		} else {
			System.out.println("Failed Login!");
		}
		return user!=null;
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

}
