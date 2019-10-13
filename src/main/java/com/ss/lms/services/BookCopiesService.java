package com.ss.lms.services;

import java.sql.SQLException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ss.lms.dao.*;
import com.ss.lms.model.*;

@Service
public class BookCopiesService {

	@Autowired
	private BookCopiesDao copiesDao;


	public Optional<BookCopies> get(LibraryBranch branch, Book book) throws SQLException {
		
		return copiesDao.get(branch, book);
	}
	
	public void update(BookCopies copies) throws SQLException {
		
		copiesDao.update(copies);
	}
}
