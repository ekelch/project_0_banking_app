package com.revature.services.models;

import java.util.Objects;

public abstract class User {

	protected int userId;
	
	protected String username;
	
	protected String password;
	
	protected int AccessType;
	
	protected String FirstName;
	
	protected String LastName;
	
	protected String email;
	
	protected String status;

	

	

	public User(int userId, String username, String password, int accessType, String firstName, String lastName,
			String email, String status) {
		super();
		this.userId = userId;
		this.username = username;
		this.password = password;
		AccessType = accessType;
		FirstName = firstName;
		LastName = lastName;
		this.email = email;
		this.status = status;
	}



	public int getAccessType() {
		return AccessType;
	}


	public void setAccessType(int accessType) {
		AccessType = accessType;
	}


	@Override
	public String toString() {
		return "User [userId=" + userId + ", username=" + username + ", password=" + password + ", FirstName="
				+ FirstName + ", LastName=" + LastName + ", email=" + email + "]";
	}

	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}



	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirstName() {
		return FirstName;
	}

	public void setFirstName(String firstName) {
		FirstName = firstName;
	}

	public String getLastName() {
		return LastName;
	}

	public void setLastName(String lastName) {
		LastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}



	@Override
	public int hashCode() {
		return Objects.hash(FirstName, LastName, email, password, userId, username);
	}



	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		return Objects.equals(FirstName, other.FirstName) && Objects.equals(LastName, other.LastName)
				&& Objects.equals(email, other.email) && Objects.equals(password, other.password)
				&& Objects.equals(userId, other.userId) && Objects.equals(username, other.username);
	}
	
}
