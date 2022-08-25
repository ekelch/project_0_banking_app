package com.revature.repository;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.revature.services.models.Customer;
import com.revature.services.models.User;
import com.revature.util.ConnectionFactory;

public class UserDao implements UserDaoInterface{
	
	Logger consoleLogger;
	Logger fileLogger;
	
	

	public UserDao() {
		this.consoleLogger = LoggerFactory.getLogger("consoleLogger");
		this.fileLogger = LoggerFactory.getLogger("fileLogger");
	}

	@Override
	public User createUser(User newUser) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User getUser(String username, String password) {
		
		consoleLogger.debug("Getting username with username and password");
		
		User user = null;
		
		final String sql = "SELECT * FROM users WHERE username = '" + username + "';";
		
		try (	Connection connection = ConnectionFactory.getConnection();
				Statement statement = connection.createStatement();) 
		{
			ResultSet set = statement.executeQuery(sql);
			if (set.next()) {
				user = new Customer(
						set.getString("username"),
						set.getString("password"),
						set.getString("email")
						);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			consoleLogger.error(e.getMessage());
		}
		
		return user;
	}

	@Override
	public User updateUser(User newUser) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean deleteUser(User user) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String toString() {
		return "UserDao []";
	}

	
	
}
