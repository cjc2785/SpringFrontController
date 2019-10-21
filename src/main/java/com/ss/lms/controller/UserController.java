package com.ss.lms.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ss.lms.model.LoginRequest;
import com.ss.lms.model.LoginResponse;
import com.ss.lms.model.UserPrincipal;
import com.ss.lms.security.JwtTokenProvider;

@RestController
@RequestMapping("/lms/user")
public class UserController {
	

    @Autowired
    AuthenticationManager authenticationManager;


    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    JwtTokenProvider tokenProvider;

    @PostMapping("/login")
    public LoginResponse authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
    	
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsername(),
                        loginRequest.getPassword()
                )
        );
      

        SecurityContextHolder.getContext().setAuthentication(authentication);
        
        UserPrincipal principal = (UserPrincipal) authentication.getPrincipal();

        String jwt = tokenProvider.generateToken(principal);
        return new LoginResponse(jwt);
    }
}
