package com.revature.repository;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.revature.services.models.Account;
import com.revature.services.models.User;
import com.revature.util.ConnectionFactory;

public class AccountDao implements AccountDaoInterface{

	
	
	@Override
	public Account createAccount(User user) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public Account getAccount(int accountId) {
		Account account = null;
				
				final String sql = "SELECT * FROM account_info WHERE account_id = " + accountId + ";";
				
				try (	Connection connection = ConnectionFactory.getConnection();
						Statement statement = connection.createStatement();) 
				{
					ResultSet set = statement.executeQuery(sql);
					
					if (set.next()) {
						int accountInt = set.getInt("account_id");
						BigDecimal accountDec = set.getBigDecimal("balance");
						account = new Account(accountInt, accountDec);
					}
					
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.getMessage();
				}
			return account;
		}
	
	@Override
	public Account getAccountByUserId(int userId) {
		Account account = null;
				
				final String sql = "SELECT * FROM account_info WHERE user_id = " + userId + ";";
				
				try (	Connection connection = ConnectionFactory.getConnection();
						Statement statement = connection.createStatement();) 
				{
					ResultSet set = statement.executeQuery(sql);
					
					if (set.next()) {
						int accountInt = set.getInt("account_id");
						BigDecimal accountDec = set.getBigDecimal("balance");
						account = new Account(accountInt, accountDec);
					}
					
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.getMessage();
				}
			return account;
		}
	
	@Override
	public BigDecimal getBalance(int accountId) {
		BigDecimal balance = null;
		
		final String sql = "SELECT * FROM account_info WHERE account_id = " + accountId + ";";
		
		try (	Connection connection = ConnectionFactory.getConnection();
				Statement statement = connection.createStatement();) 
		{
			ResultSet set = statement.executeQuery(sql);
			
			if (set.next()) {
				balance = set.getBigDecimal("balance");
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.getMessage();
		}
	return balance;
	}
	
	

	@Override
	public Account updateAccount(int accountId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Account deleteAccount(int accountId) {
		// TODO Auto-generated method stub
		return null;
	}

	

}
