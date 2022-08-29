package com.revature.services.models;

public class Bridge {

	protected int accountId;
	protected int userId;
	
	public int getAccountId() {
		return accountId;
	}
	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	
	public Bridge(int userId, int accountId) {
		super();
		this.accountId = accountId;
		this.userId = userId;
	}
	
}
