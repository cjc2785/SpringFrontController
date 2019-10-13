package com.ss.lms.services;

import java.sql.SQLException;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ss.lms.dao.*;
import com.ss.lms.model.*;

@Service
public class BookService {

	@Autowired
	private BookDao bookDao;
	
	
	public void delete(Book book) throws SQLException {
		bookDao.delete(book);
	}
	
	public void insert(Book book) throws SQLException {
		bookDao.insert(book);
	}
	
	public void update(Book book) throws SQLException {
		bookDao.update(book);
	}
	
	public Optional<Book> get(int bookId) throws SQLException {
		return bookDao.get(bookId);
	}
	
	public List<Book> getAll() throws SQLException {
		return bookDao.getAll();
	}
	
	public List<Book> getAll(LibraryBranch branch) throws SQLException {
		return bookDao.getAll(branch);
	}
	
	public List<Book> getAll(Borrower borrower, LibraryBranch branch) throws SQLException {
		return bookDao.getAll(borrower, branch);
	}
}
