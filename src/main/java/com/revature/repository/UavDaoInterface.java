package com.revature.repository;

import java.util.List;

import com.revature.services.models.Uav;
import com.revature.services.models.User;

public interface UavDaoInterface {
	
	List<Uav> getUavMaster();
	List<Uav> getUavByUsername(String username);
	List<Uav> getUavByUserId(int userId);
	List<Uav> getUavByAccountId(int accountId);
	
	List<Integer> getAccountIdbyUser(User user);
	
}
