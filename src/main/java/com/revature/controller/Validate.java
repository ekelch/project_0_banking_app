package com.revature.controller;

import java.util.List;
import java.util.Scanner;

import com.revature.repository.UserDaoInterface;
import com.revature.services.models.User;

public class Validate{
	
	private Scanner sc;
	private UserDaoInterface userDao;

	public Validate(Scanner sc, UserDaoInterface userDao) {
		this.sc = sc;
		this.userDao = userDao;
	}

	public String validateUnique(String input) {
		String newInput = null;
		Boolean validInput = false;
		List<String> inputList = userDao.getAllInput(input);
		
		System.out.print("Please choose your username: ");
		
		while (!validInput) {
		
			newInput = sc.nextLine();
			
			if (newInput.isBlank() || newInput.equals(null)) {
				System.out.println("Please type a " + input + " including at least one character.");
				continue;
			}
			if (inputList.contains(newInput)) {
				System.out.println("Sorry, this " + input + " is unavailable. Please try again.");
				continue;
			}
			validInput = true;
		}
		return newInput;
	}
	
	public String getValidateNotNull(String input) {
		System.out.print("Please enter your " + input + ": ");
		return sc.nextLine();
	}
	
	public User validateLogin(String username, String password) {
			User user = userDao.getUser(username, password);
			if (user == null) 
				return null;
			else if (user.getUsername().equals(username) && user.getPassword().equals(password))
				return user;
			else
				return null;
		}

}
