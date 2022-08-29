package com.revature.services.models;

public class Admin extends User{

	public Admin(int userId, String username, String password, int accessType, String firstName, String lastName,
			String email, String status) {
		super(userId, username, password, accessType, firstName, lastName, email, status);
		accessType = 3;
	}

	

}
