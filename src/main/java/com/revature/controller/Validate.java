package com.revature.controller;

import java.util.List;
import java.util.Scanner;

import com.revature.repository.UserDaoInterface;

public class Validate extends UserController{
	
	public Validate(Scanner sc, UserDaoInterface userDao) {
		super(sc, userDao);
		// TODO Auto-generated constructor stub
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

}
