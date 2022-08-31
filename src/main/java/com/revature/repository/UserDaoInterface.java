package com.revature.repository;

import java.util.List;

import com.revature.services.models.User;

public interface UserDaoInterface {

	//CREATE
	User createUser(User newUser);
	
	//READ
	User getUser(String username, String password);
	User getUserById(int userId);
	User getUserByUsername(String username);
	
	List<User> getAllUsers();
	List<String> getUsersColumnString(String input);
	List<Integer> getUsersColumnInt(String input);
	
	//UPDATE
	User setPending(User user);
	User setDefault(User user);
	User setAccess(User user, int accessLevel);
	
	//DELETE 
	boolean deleteUser(User user);

	
	
	
	
	static User get(String username, String password) {
		// TODO Auto-generated method stub
		return null;
	}

}
