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
						set.getInt("access_type"),
						set.getString("first_name"),
						set.getString("last_name"),
						set.getString("email"),
						set.getString("status")
						);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			consoleLogger.error(e.getMessage());
		}
		return user;
	}
	
	@Override
	public User getUserById(int userId) {
		
		User user = null;
		
		final String sql = "SELECT * FROM users WHERE user_id = " + userId + ";";
		
		try (	Connection connection = ConnectionFactory.getConnection();
				Statement statement = connection.createStatement();) 
		{
			ResultSet set = statement.executeQuery(sql);
			if (set.next()) {
				user = new Customer(
						set.getInt("user_id"),
						set.getString("username"),
						set.getString("password"),
						set.getInt("access_type"),
						set.getString("first_name"),
						set.getString("last_name"),
						set.getString("email"),
						set.getString("status")
						);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			consoleLogger.error(e.getMessage());
		}
		return user;
	}
	
	@Override
	public User getUserByUsername(String username) {
		
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
						set.getInt("access_type"),
						set.getString("first_name"),
						set.getString("last_name"),
						set.getString("email"),
						set.getString("status")
						);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			consoleLogger.error(e.getMessage());
		}
		return user;
	}
	
	// Retrieve full array list of any column from users table
	public List<String> getUsersColumnString(String input) {
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
	
	public List<Integer> getUsersColumnInt(String input) {
		List<Integer> inputList = new ArrayList<Integer>();
		
		final String sql = "SELECT " + input + " FROM users";
		
		try (	Connection connection = ConnectionFactory.getConnection();
				Statement statement = connection.createStatement();) 
		{
			ResultSet set = statement.executeQuery(sql);
			while (set.next()) {
				inputList.add(set.getInt(input));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			consoleLogger.error(e.getMessage());
		}
		
		//consoleLogger.debug("Getting userList from users(username)");
		return inputList;
	}

	@Override
	public User setPending(User user) {
		
	final String sql = "update users set status = 'pending' where user_id =" + user.getUserId() + ";";
			
			try (	Connection connection = ConnectionFactory.getConnection();
					Statement statement = connection.createStatement();) 
			{
				statement.executeQuery(sql);
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.getMessage();
			}
		user = getUser(user.getUsername(), user.getPassword());
		return user;
	}
	
	@Override
	public User setDefault(User user) {
		
		final String sql = "update users set status = 'default' where user_id =" + user.getUserId() + ";";
				
				try (	Connection connection = ConnectionFactory.getConnection();
						Statement statement = connection.createStatement();) 
				{
					statement.executeQuery(sql);
					
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.getMessage();
				}
			return user;
		}

	@Override
	public User setAccess(User user, int accessLevel) {
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

	@Override
	public List<User> getAllUsers() {
		
		List<User> userList = new ArrayList<User>();
		final String sql = "SELECT * FROM users";
		
		try (	Connection connection = ConnectionFactory.getConnection();
				Statement statement = connection.createStatement();) 
		{
			ResultSet set = statement.executeQuery(sql);
			while (set.next()) {
				userList.add(new Customer(
						set.getInt("user_id"),
						set.getString("username"),
						set.getString("password"),
						set.getInt("access_type"),
						set.getString("first_name"),
						set.getString("last_name"),
						set.getString("email"),
						set.getString("status"))
				);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.getMessage();
		}

	return userList;
	}

	
	
}
