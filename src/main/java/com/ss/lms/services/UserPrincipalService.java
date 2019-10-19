package com.ss.lms.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


import com.ss.lms.dao.*;
import com.ss.lms.model.*;

@Service
public class UserPrincipalService {

	@Autowired
	private UserDao userDao;
	
	
	public UserPrincipal findById(Integer userId) {
		return userDao.findById(userId)
				.map(UserPrincipal::new)
				.orElseThrow(() -> new UsernameNotFoundException("user not found"));
	}
}
