package com.revature.repository;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

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
	// This returns user to allow autogeneration of serial followed by rewrite rather than inserting own serial.
	public User createUser(User newUser) {
		// Default access type 1 = customer; May be elevated by admin later
		final String sql = "INSERT INTO users (username, password, access_type, first_name, last_name, email) "
				+ "VALUES ('" + newUser.getUsername() + "', '" + newUser.getPassword() + "', 1, '" + newUser.getFirstName() + "', '" + newUser.getLastName() + "', '" + newUser.getEmail() + "');";
		
		try (Connection connection = ConnectionFactory.getConnection();
			 Statement statement = connection.createStatement();)
		{
			statement.executeQuery(sql);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.getMessage();
		}
		
		return getUser(newUser.getUsername(), newUser.getPassword());
		
	}

	@Override
	public User getUser(String username, String password) {
		
		User user = null;
		
		final String sql = "SELECT * FROM users WHERE username = '" + username + "';";
		
		try (	Connection connection = ConnectionFactory.getConnection();
				Statement statement = connection.createStatement();) 
		{
			ResultSet set = statement.executeQuery(sql);
			if (set.next()) {
				user = new Customer(
						set.getInt("user_id"),
						set.getString("username"),
						set.getString("password"),
						set.getString("first_name"),
						set.getString("last_name"),
						set.getString("email")
						);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			consoleLogger.error(e.getMessage());
		}
		return user;
	}
	
	// Retrieve full array list of any column from users table
	public List<String> getAllInput(String input) {
		List<String> inputList = new ArrayList<String>();
		
		final String sql = "SELECT " + input + " FROM users";
		
		try (	Connection connection = ConnectionFactory.getConnection();
				Statement statement = connection.createStatement();) 
		{
			ResultSet set = statement.executeQuery(sql);
			while (set.next()) {
				inputList.add(set.getString(input));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			consoleLogger.error(e.getMessage());
		}
		
		//consoleLogger.debug("Getting userList from users(username)");
		return inputList;
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
