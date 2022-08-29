package com.revature.repository;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.revature.services.models.User;
import com.revature.util.ConnectionFactory;

public class BridgeDao {
	
	Logger consoleLogger;
	Logger fileLogger;

	public BridgeDao() {
		this.consoleLogger = LoggerFactory.getLogger("consoleLogger");
		this.fileLogger = LoggerFactory.getLogger("fileLogger");
	}
	
	List<Integer> accountList = new ArrayList<Integer>();

	public List<Integer> getAccountIdFromUser(User user) {
		
		final String sql = "SELECT account_id FROM user_account_bridge WHERE user_id =" + user.getUserId() + ";";
			
		try (Connection connection = ConnectionFactory.getConnection();
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
	
}
