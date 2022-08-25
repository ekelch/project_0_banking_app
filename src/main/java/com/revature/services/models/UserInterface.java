package com.revature.services.models;

public interface UserInterface{

	User login(String username, String password); // successful or improper login
	
	boolean logout();
	
	boolean deleteUser();
	
	boolean updateUser(User updatedUser);
	
}
