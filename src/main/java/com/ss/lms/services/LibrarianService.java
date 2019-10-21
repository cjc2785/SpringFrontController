package com.ss.lms.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ss.lms.dao.LibrarianDAO;
import com.ss.lms.model.LibraryBranch;
import com.ss.lms.model.BookCopies;


@Service
public class LibrarianService {
	
	@Autowired 
	private LibrarianDAO library;
	
	public LibraryBranch[] getBranches() {
		return library.getBranches();
	}
	
	public LibraryBranch getBranchInfo(int branchId) {
		return library.getBranchInfo(branchId);
	}
	
	public void save(LibraryBranch updateBranch, int branchId) {
		library.save(updateBranch, branchId);
	}
	
	public BookCopies[] getBooks(int branchId) {
		
		return library.getBooks(branchId);
	}
	
	public BookCopies getBookInfo(int bookId, int branchId) {
		
		return library.getBookInfo(branchId, bookId);
	}
	
	public void addCopies(int branchId, int bookId, BookCopies newCopies) {	
		library.addCopies(branchId, bookId, newCopies);
	}
	
}
