package com.revature.services.models;

import java.math.BigDecimal;
import java.util.Objects;

public class Uav {

	protected int userId;
	protected String username;
	protected int accountId;
	protected BigDecimal balance;
	
	public Uav(int userId, String username, int accountId, BigDecimal balance) {
		super();
		this.userId = userId;
		this.username = username;
		this.accountId = accountId;
		this.balance = balance;
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
	public int getAccountId() {
		return accountId;
	}
	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}
	public BigDecimal getBalance() {
		return balance;
	}
	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}
	@Override
	public String toString() {
		return "UserAccountView [userId=" + userId + ", username=" + username + ", accountId=" + accountId
				+ ", balance=" + balance + "]";
	}
	@Override
	public int hashCode() {
		return Objects.hash(accountId, balance, userId, username);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Uav other = (Uav) obj;
		return accountId == other.accountId && Objects.equals(balance, other.balance) && userId == other.userId
				&& Objects.equals(username, other.username);
	}
	
}
