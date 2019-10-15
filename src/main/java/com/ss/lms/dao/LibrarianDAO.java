package com.ss.lms.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.ss.lms.model.BranchPOJO;
import com.ss.lms.model.LibraryPOJO;

@Component
public class LibrarianDAO {

	@Autowired
	RestTemplate template;
	
	static final String BASE = "http://localhost:8082/lms/librarian/";
	
	public BranchPOJO[] getBranches() {
		String url = BASE + "branches";
		return template.getForObject(url, BranchPOJO[].class);
	}
	
	public BranchPOJO getBranchInfo(int branchId) {		
		String url = BASE + "branch/{branchId}";
		return template.getForObject(url, BranchPOJO.class);
	}
	
	public void save(BranchPOJO updateBranch) {
		String url = BASE + "branch/{branchId}";
		template.put(url, updateBranch);
	}
	
	public LibraryPOJO[] getBooks(int branchId) {
		String url = BASE + "branch/{branchId}/books";
		return template.getForObject(url, LibraryPOJO[].class);
	}
	
	public LibraryPOJO getBookInfo(int branchId, int bookId) {
		String url = BASE + "branch/{branchId}/book/{bookId}";
		return template.getForObject(url, LibraryPOJO.class);
	}
	
	public void addCopies(int branchId, int bookId, LibraryPOJO newCopies) {
		String url = BASE + "branch/{branchId}/book/{bookId}";
		template.put(url, newCopies);
	}
	
}
