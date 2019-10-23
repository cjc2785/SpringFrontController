package com.ss.lms.services;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import javax.transaction.Transactional;

import java.util.Arrays;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.ss.lms.dao.UserDao;
import com.ss.lms.model.User;
import com.ss.lms.model.UserPrincipal;

@ExtendWith(SpringExtension.class) 
@SpringBootTest
@TestPropertySource("classpath:application-test.properties")
@Transactional
class UserPrincipalServiceTest {
	
	@Autowired
	public UserDao userDao;
	
	@Autowired 
	UserPrincipalService service;
	

	@Test
	void findByIdShouldReturnBob() {
		

		User al = new User();
		al.setUsername("al");
		
		User bob = new User();
		bob.setUsername("bob");
		
		User leo = new User();
		leo.setUsername("leo");

		List<User> users = Arrays.asList(al, bob, leo);
		
		userDao.saveAll(users);
	
		
		assertThrows(UsernameNotFoundException.class, () -> {
			
			UserPrincipal bobPrincipal = service.findById(1);
			
			String actual = bobPrincipal.getUsername();
			
			assertEquals("bob", actual);
		});
	}

	@Test
	void findByIdShouldThrowIfUserNotFound() {
		

		User al = new User();
		al.setUsername("al");
		
		User bob = new User();
		bob.setUsername("bob");
		
		User leo = new User();
		leo.setUsername("leo");

		List<User> users = Arrays.asList(al, bob);
		
		userDao.saveAll(users);
	
		
		assertThrows(UsernameNotFoundException.class, () -> {
			service.findById(5);
		});
	}

}
