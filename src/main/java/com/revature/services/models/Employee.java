package com.revature.services.models;

public class Employee extends User{

	public Employee(int userId, String username, String password, int accessType, String firstName, String lastName,
			String email, String status) {
		super(userId, username, password, accessType, firstName, lastName, email, status);

		accessType = 2;
	}
	

}
