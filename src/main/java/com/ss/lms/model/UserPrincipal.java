package com.ss.lms.model;

import java.util.Collection;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;


public class UserPrincipal implements UserDetails {
	
	private static final long serialVersionUID = 1L;

	private User user;

    public UserPrincipal(User user) {
    	this.user = user;
    }
    
    public User getUser() {
    	return user;
    }
  

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return user.getRoles().stream()
        		.map(Role::getRoleName)
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public boolean equals(Object o) {
    	
        if(o == null || !(o instanceof UserPrincipal)) {
        	return false;
        }
        UserPrincipal principal = (UserPrincipal)o;
        
        return Objects.equals(user, principal.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(user);
    }
}