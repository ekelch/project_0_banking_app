package com.revature.repository;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.revature.services.models.Uav;
import com.revature.services.models.User;
import com.revature.util.ConnectionFactory;

public class UavDao implements UavDaoInterface{

	@Override
	public List<Uav> getUavByUsername(String username) {
		Uav uav = null;
		List<Uav> uavList = new ArrayList<Uav>();
		final String sql = "SELECT * FROM user_account_balance WHERE username = '" + username + "';";
		
		try (	Connection connection = ConnectionFactory.getConnection();
				Statement statement = connection.createStatement();) 
		{
			ResultSet set = statement.executeQuery(sql);
			
			while (set.next()) {
				uav = new Uav(set.getInt("user_id"), set.getString("username"), set.getInt("account_id"), set.getBigDecimal("balance"));
				uavList.add(uav);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.getMessage();
		}
	return uavList;
	}

	@Override
	public List<Uav> getUavByUserId(int userId) {
		Uav uav = null;
		List<Uav> uavList = new ArrayList<Uav>();
		final String sql = "SELECT * FROM user_account_balance WHERE user_id = '" + userId + "';";
		
		try (	Connection connection = ConnectionFactory.getConnection();
				Statement statement = connection.createStatement();) 
		{
			ResultSet set = statement.executeQuery(sql);
			
			while (set.next()) {
				uav = new Uav(set.getInt("user_id"), set.getString("username"), set.getInt("account_id"), set.getBigDecimal("balance"));
				uavList.add(uav);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.getMessage();
		}
	return uavList;
	}

	@Override
	public List<Uav> getUavByAccountId(int accountId) {
		Uav uav = null;
		List<Uav> uavList = new ArrayList<Uav>();
		final String sql = "SELECT * FROM user_account_balance WHERE account_id = '" + accountId + "';";
		
		try (	Connection connection = ConnectionFactory.getConnection();
				Statement statement = connection.createStatement();) 
		{
			ResultSet set = statement.executeQuery(sql);
			
			while (set.next()) {
				uav = new Uav(set.getInt("user_id"), set.getString("username"), set.getInt("account_id"), set.getBigDecimal("balance"));
				uavList.add(uav);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.getMessage();
		}
	return uavList;
	}

	@Override
	public List<Integer> getAccountIdbyUser(User user) {
		List<Integer> accountList = new ArrayList<Integer>();
		final String sql = "SELECT account_id FROM user_account_balance WHERE username = '" + user.getUsername() + "';";
		
		try (	Connection connection = ConnectionFactory.getConnection();
				Statement statement = connection.createStatement();) 
		{
			ResultSet set = statement.executeQuery(sql);
			
			while (set.next()) {
				accountList.add(set.getInt("account_id"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.getMessage();
		}
	return accountList;
	}

	@Override
	public List<Uav> getUavMaster() {
		Uav uav = null;
		List<Uav> uavList = new ArrayList<Uav>();
		final String sql = "SELECT * FROM user_account_balance;";
		
		try (	Connection connection = ConnectionFactory.getConnection();
				Statement statement = connection.createStatement();) 
		{
			ResultSet set = statement.executeQuery(sql);
			
			while (set.next()) {
				uav = new Uav(set.getInt("user_id"), set.getString("username"), set.getInt("account_id"), set.getBigDecimal("balance"));
				uavList.add(uav);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.getMessage();
		}
	return uavList;
	}

}
