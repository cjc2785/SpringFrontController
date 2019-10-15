package com.ss.lms.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ss.lms.dao.LibrarianDAO;
import com.ss.lms.model.BranchPOJO;
import com.ss.lms.model.LibraryPOJO;


@Service
public class LibrarianService {
	
	@Autowired 
	private LibrarianDAO library;
	
	public BranchPOJO[] getBranches() {
		return library.getBranches();
	}
	
	public BranchPOJO getBranchInfo(int branchId) {
		return library.getBranchInfo(branchId);
	}
	
	public void save(BranchPOJO updateBranch) {
		library.save(updateBranch);
	}
	
	public LibraryPOJO[] getBooks(int branchId) {
		
		return library.getBooks(branchId);
	}
	
	public LibraryPOJO getBookInfo(int bookId, int branchId) {
		
		return library.getBookInfo(branchId, bookId);
	}
	
	public void addCopies(int branchId, int bookId, LibraryPOJO newCopies) {	
		library.addCopies(branchId, bookId, newCopies);
	}
	
}
