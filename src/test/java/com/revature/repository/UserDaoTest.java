package com.revature.repository;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.revature.services.models.Customer;
import com.revature.services.models.User;
/*
// Do not need to instantiate class in order to run tests; tests classes static
@TestInstance(Lifecycle.PER_CLASS)
public class UserDaoTest {

	@Mock
	private UserDao uDao;
	private User validCustomer;
	
	@BeforeAll
	void setup() {
		MockitoAnnotations.openMocks(this);
		
		validCustomer = new Customer(1, user1, pass1, email1@email1.com);
	}
	
	@Test
	public void getUserWithCorrectUsernameTest() {
		Mockito.when(uDao.getUser(null, null)).thenReturn();
	}
	
}
*/