package com.revature;

import java.util.Scanner;

import com.revature.controller.UserController;
import com.revature.repository.UserDao;
import com.revature.repository.UserDaoInterface;

public class Driver {

	public static void main(String[] args) {
		
		UserDaoInterface uDao = new UserDao();
		UserController userController = new UserController(new Scanner(System.in), uDao);
		
		userController.login();
	}

}
