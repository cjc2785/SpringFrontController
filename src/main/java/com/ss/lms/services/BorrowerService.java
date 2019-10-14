package com.ss.lms.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.ss.lms.dao.*;
import com.ss.lms.model.*;

@Service
public class BorrowerService {

	@Autowired
	private BorrowerDao borrowerDao;
	
	
	public LibraryBranch[] getBranches() {

		return borrowerDao.getBranches();
	}
	
	public LibraryBranch[] getBranches(int cardNo) {
	
		return borrowerDao.getBranches(cardNo);
	}
	
	public Book[] getBooks(int branchId) {
		
		return borrowerDao.getBooks(branchId);
	}
	
	public Book[] getBooks(int cardNo, int branchId) {
		
		return borrowerDao.getBooks(cardNo, branchId);
	}
}
