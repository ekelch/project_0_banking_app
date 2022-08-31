package com.revature.repository;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.revature.services.models.Account;
import com.revature.services.models.Bridge;
import com.revature.services.models.User;
import com.revature.util.ConnectionFactory;

public class AccountDao implements AccountDaoInterface{

	
	
	@Override
	public Account createAccount(User user) {
		Account account = new Account(0, new BigDecimal("0.00"));
		int accountId = 0;
		final String sql = "insert into account_info default values;";
		
		try (	Connection connection = ConnectionFactory.getConnection();
				PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);) 
		{
			int affectedRows = statement.executeUpdate();
			
			if (affectedRows > 0) {
				try (ResultSet set = statement.getGeneratedKeys()){
					if (set.next()) {
						accountId = set.getInt(1);
						account.setAccountId(accountId);
					}
				} catch (SQLException e) {
					e.getMessage();
				}
			}
			
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.getMessage();
		}
	return account;
	}
	

	@Override
	public Bridge createBridge(User user, Account account) {
		Bridge bridge = new Bridge(user.getUserId(), account.getAccountId());
		
		final String sql = "insert into user_account_bridge (user_id, account_id) values (" + user.getUserId() + ", " + account.getAccountId() + ");";
		
		try (	Connection connection = ConnectionFactory.getConnection();
				PreparedStatement statement = connection.prepareStatement(sql);) 
		{
			statement.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.getMessage();
		}
	return bridge;
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
	public Account updateAccount(Account account) {
		
		final String sql = "update account_info set balance = " + account.getBalance() + " where account_id = " + account.getAccountId() + ";";
		
		try (	Connection connection = ConnectionFactory.getConnection();
				Statement statement = connection.createStatement();) 
		{
			statement.executeQuery(sql); // might need to get account to verify changes?
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.getMessage();
		}
	return account;
	}

	@Override
	public Account deleteAccount(int accountId) {
		// TODO Auto-generated method stub
		return null;
	}

	

	

}
