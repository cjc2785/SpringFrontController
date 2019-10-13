package com.ss.lms.services;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ss.lms.dao.*;
import com.ss.lms.model.*;

@Service
public class LibraryBranchService {

	@Autowired
	private LibraryBranchDao branchDao;
	
	
	public List<LibraryBranch> getAll() throws SQLException {
		return branchDao.getAll();
	}
	
	public Optional<LibraryBranch> get(int branchId) throws SQLException {
		return branchDao.get(branchId);
	}
	
	public void delete(LibraryBranch branch) throws SQLException {
		branchDao.delete(branch);
	}
	
	public void insert(LibraryBranch branch) throws SQLException {
		branchDao.insert(branch);
	}
	
	public void update(LibraryBranch branch) throws SQLException {
		branchDao.update(branch);
	}
	
	//Returns all libraries the borrower has at least 1 book checked out at
	public List<LibraryBranch> getAll(Borrower borrower) 
			throws SQLException {
		
		return branchDao.getAll(borrower);
	}
}
