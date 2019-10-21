package com.ss.lms.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ss.lms.model.*;

@Repository
public interface UserDao extends JpaRepository<User, Integer> {
	public Optional<User> findByUsername(String username);
}
