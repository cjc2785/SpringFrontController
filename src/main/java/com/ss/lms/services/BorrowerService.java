package com.ss.lms.services;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ss.lms.dao.*;
import com.ss.lms.model.*;

@Service
public class BorrowerService {

	@Autowired
	private BorrowerDao borrowerDao;
	

	public void delete(Borrower borrower) throws SQLException {
		borrowerDao.delete(borrower);
	}
	
	public void insert(Borrower borrower) throws SQLException {
		borrowerDao.insert(borrower);
	}
	
	public void update(Borrower borrower) throws SQLException {
		borrowerDao.update(borrower);
	}

	public Optional<Borrower> get(int cardNo) throws SQLException {
		return borrowerDao.get(cardNo);
	}
	
	public List<Borrower> getAll() throws SQLException {
		return borrowerDao.getAll();
	}
}
