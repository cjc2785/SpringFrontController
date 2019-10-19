package com.ss.lms.model;

import java.io.Serializable;
import java.util.Objects;
import java.util.Set;

public class User implements Serializable {
	
	static final long serialVersionUID = 1L;

	
	private Integer userId;
	
	private String username;
	
	private String password;
	
	private Set<String> roles;
	
	public User(User user) {
	        userId = user.userId;
	        username = user.username;
	        password = user.password;
	        roles = user.roles;
	    }

	public User(Integer userId, String username, String password, Set<String> roles) {
		this.userId = userId;
		this.username = username;
		this.password = password;
		this.roles = roles;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}	
	
	public Set<String> getRoles() {
		return roles;
	}
	
	public void setRoles(Set<String> roles) {
		this.roles = roles;
	}
	
    @Override
    public boolean equals(Object o) {
        if(o == null || !(o instanceof User)) {
        	return false;
        }
        User u = (User)o;
        
        return Objects.equals(userId, u.getUserId());
    }
	
	@Override
    public int hashCode() {
        return Objects.hash(userId);
    }
}