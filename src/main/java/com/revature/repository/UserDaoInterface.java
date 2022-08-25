package com.revature.repository;

import com.revature.services.models.User;

public interface UserDaoInterface {

	//CREATE
	User createUser(User newUser);
	
	//READ
	User getUser(String username, String password);
	
	//UPDATE
	User updateUser(User newUser);
	
	//DELETE 
	boolean deleteUser(User user);

	static User get(String username, String password) {
		// TODO Auto-generated method stub
		return null;
	}

}
