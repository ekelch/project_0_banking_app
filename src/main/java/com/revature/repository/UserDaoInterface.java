package com.revature.repository;

import java.util.List;

import com.revature.services.models.User;

public interface UserDaoInterface {

	//CREATE
	void createUser(User newUser);
	
	//READ
	User getUser(String username, String password);
	
	List<String> getAllInput(String input);
	
	//UPDATE
	User updateUser(User newUser);
	
	//DELETE 
	boolean deleteUser(User user);

	static User get(String username, String password) {
		// TODO Auto-generated method stub
		return null;
	}

}
