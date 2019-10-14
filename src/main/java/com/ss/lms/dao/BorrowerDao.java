package com.ss.lms.dao;

import org.springframework.web.client.RestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ss.lms.model.*;

@Component
public class BorrowerDao {
	
	@Autowired
	RestTemplate template;
	
	static final String BASE = "http://localhost:8080/lms/borrower/";

	public LibraryBranch[] getBranches() {
		
		String url = BASE + "branches";
		return template.getForObject(
				url, LibraryBranch[].class);
	}
	
	public LibraryBranch[] getBranches(int cardNo) {
		
		String url = BASE + "branches/{cardNo}/branches";
		
		return template.getForObject(
				url, LibraryBranch[].class, cardNo);
	}
	
	public Book[] getBooks(int branchId) {
		
		String url = BASE + "branches/{branchId}/books";
		
		return template.getForObject(
				url, Book[].class, branchId);
	}
	
	public Book[] getBooks(int cardNo, int branchId) {
		
		String url = BASE 
				+ "borrowers/{cardNo}/branches/{branchId}/books";
		
		return template.getForObject(
				url, Book[].class, cardNo, branchId);
	}
}
