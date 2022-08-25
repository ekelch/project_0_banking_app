package com.revature.controller;

import java.util.Scanner;
import com.revature.services.LoginService;
import com.revature.services.models.User;

public class UserController implements UserInputInterface, LoginInterface{

	private Scanner sc;
	private LoginService loginService;
	
	public UserController(Scanner sc, LoginService loginService) {
		super();
		this.sc = sc;
		this.loginService = loginService;
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
		System.out.println("Please enter your " + input + ": ");
		return sc.nextLine();
	}
	
	@Override
	public User validateLogin(String username, String password) {
		
		if (username == null && password == null) {
			return null;
		}
		User user = loginService.login(username, password);
		return user;
	}

}
