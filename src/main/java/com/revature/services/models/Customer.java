package com.revature.services.models;

public class Customer extends User{

	public Customer(String username, String password, String email) {
		super(username, password, email);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "Customer [username=" + username + ", password=" + password + ", email=" + email + "]";
	}

	@Override
	public int hashCode() {
		return super.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		return true;
	}

	
}
