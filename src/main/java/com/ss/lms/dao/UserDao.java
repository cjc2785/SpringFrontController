package com.ss.lms.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ss.lms.model.*;

public interface UserDao extends JpaRepository<User, Integer> {

}
