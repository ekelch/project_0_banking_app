package com.revature;

import java.util.Scanner;

import com.revature.controller.AccountController;
import com.revature.controller.Menu;
import com.revature.controller.UserController;
import com.revature.repository.AccountDao;
import com.revature.repository.AccountDaoInterface;
import com.revature.repository.UserDao;
import com.revature.repository.UserDaoInterface;


public class Driver {

	public static void main(String[] args) {
		
		
		UserDaoInterface uDao = new UserDao();
		AccountDaoInterface aDao = new AccountDao();
		Scanner sc = new Scanner(System.in);
		UserController userController = new UserController(sc, uDao);
		AccountController accountController = new AccountController(sc, aDao);
		
		Menu menu = new Menu(userController, accountController);
		menu.mainMenu();
	}

}
