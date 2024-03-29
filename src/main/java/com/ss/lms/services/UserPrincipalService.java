package com.ss.lms.services;

import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


import com.ss.lms.dao.*;
import com.ss.lms.model.*;

@Service
public class UserPrincipalService implements UserDetailsService {

	@Autowired
	private UserDao userDao;
	
	@Transactional
	public UserPrincipal findById(Integer userId) {
		return userDao.findById(userId)
				.map(this::toPrincipal)
				.orElseThrow(() -> new UsernameNotFoundException("user not found"));
	}
	

    @Override
    @Transactional
    public UserPrincipal loadUserByUsername(String username)
            throws UsernameNotFoundException {
        
        User user = userDao.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(
                		"user not found")
        );
      
        return toPrincipal(user);
    }
    
    
    private UserPrincipal toPrincipal(User user) {
    	
    	List<GrantedAuthority> authorities = user.getRoles().stream()
		.map(Role::getRoleName)
        .map(SimpleGrantedAuthority::new)
        .collect(Collectors.toList());
    	
    	return new UserPrincipal(user, authorities);
    }
}
