package com.ss.lms.dao;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.ss.lms.model.BookCopies;
import com.ss.lms.model.LibraryBranch;

@Component
public class LibrarianDAO {

	RestTemplate template = new RestTemplate();
	
	static final String BASE = "http://localhost:8082/lms/librarian/";
	
	public LibraryBranch[] getBranches() {
		String url = BASE + "branches";
		return template.getForObject(url, LibraryBranch[].class);
	}
	
	public LibraryBranch getBranchInfo(int branchId) {		
		String url = BASE + "branch/{branchId}";
		return template.getForObject(url, LibraryBranch.class, branchId);
	}
	
	public void save(LibraryBranch updateBranch, int branchId) {
		String url = BASE + "branch/{branchId}";
		template.put(url, updateBranch, branchId);
	}
	
	public BookCopies[] getBooks(int branchId) {
		String url = BASE + "branch/{branchId}/books";
		return template.getForObject(url, BookCopies[].class, branchId);
	}
	
	public BookCopies getBookInfo(int branchId, int bookId) {
		String url = BASE + "branch/{branchId}/book/{bookId}";
		return template.getForObject(url, BookCopies.class, branchId, bookId);
	}
	
	public void addCopies(int branchId, int bookId, BookCopies newCopies) {
		String url = BASE + "branch/{branchId}/book/{bookId}";
		template.put(url, newCopies, branchId, bookId);
	}
	
}
