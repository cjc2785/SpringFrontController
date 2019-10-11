package com.ss.lms.model;

import java.util.ArrayList;
import java.util.List;
import com.ss.lms.model.LibraryBook;

public class LibraryPOJO {

	private int branchId;
	private String branchName;
	private String branchAddress;
	private List<LibraryBook> books;
	//books and noOfCopies will be added at the same time to keep the indexes the same
	
	public LibraryPOJO() {
		branchId = 0;
		branchName = "";
		branchAddress = "";
		books = new ArrayList<>();
	}
	
	public LibraryPOJO(int id, String name, String address, List<LibraryBook> book) {
		branchId = id;
		branchName = name;
		branchAddress = address;
		books = book;
	}
	
	public int getBranchId() {
		return branchId;
	}
	public void setBranchId(int branchId) {
		this.branchId = branchId;
	}
	public String getBranchName() {
		return branchName;
	}
	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}
	public String getBranchAddress() {
		return branchAddress;
	}
	public void setBranchAddress(String branchAddress) {
		this.branchAddress = branchAddress;
	}	
	public List<LibraryBook> getBooks() {
		return books;
	}
	public void setBooks(List<LibraryBook> book) {
		this.books = book;
	}	
}
