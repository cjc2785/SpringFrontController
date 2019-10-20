package com.ss.lms.model;

import java.io.Serializable;
import java.util.Objects;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name="tbl_user")
public class User implements Serializable {
	
	static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer userId;
	
	@Column
	private String username;
	
	@Column
	private String password;
	
	@ManyToMany
	@JoinTable(name = "tbl_user_roles",
			joinColumns = @JoinColumn(name="userId"),
			inverseJoinColumns = @JoinColumn(name="roleId"))
	private Set<Role> roles;
	
	public User() { }
	
	public User(User user) {
	        userId = user.userId;
	        username = user.username;
	        password = user.password;
	        roles = user.roles;
	    }

	public User(Integer userId, String username, String password, Set<Role> roles) {
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
	
	public Set<Role> getRoles() {
		return roles;
	}
	
	public void setRoles(Set<Role> roles) {
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