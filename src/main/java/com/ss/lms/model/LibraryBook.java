package com.ss.lms.model;


public class LibraryBook {
	private int bookId;
	private int branchId;
	private Integer noOfCopies;
		
	public LibraryBook() {}
	
	public LibraryBook(int book, int branch, int copies) {
		this.bookId = book;
		this.branchId = branch;
		noOfCopies = copies;
	}
	
	public int getBookId() {
		return bookId;
	}

	public void setBookId(int bookId) {
		this.bookId = bookId;
	}

	public int getBranchId() {
		return branchId;
	}

	public void setBranchId(int branchId) {
		this.branchId = branchId;
	}

	public Integer getNoOfCopies() {
		return noOfCopies;
	}

	public void setNoOfCopies(Integer noOfCopies) {
		this.noOfCopies = noOfCopies;
	}

	public void addCopies(int newCopies) {
		this.noOfCopies += newCopies;
	}
	
}
