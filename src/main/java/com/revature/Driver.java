package com.revature;

import java.util.Scanner;

import com.revature.controller.Menu;
import com.revature.controller.UserController;
import com.revature.repository.UserDao;
import com.revature.repository.UserDaoInterface;


public class Driver {

	public static void main(String[] args) {
		
		
		UserDaoInterface uDao = new UserDao();
		Scanner sc = new Scanner(System.in);
		UserController userController = new UserController(sc, uDao);
		
		Menu menu = new Menu(userController);
		menu.mainMenu();
	}

}
