package com.ss.lms.model;

public class BookCopies {
	
	private Book book;
	private LibraryBranch branch;
	private int noOfCopies;
	
	public BookCopies() {}
	
	public BookCopies(Book book, LibraryBranch branch, int copies) {
		this.book = book;
		this.branch = branch;
		this.noOfCopies = copies;
	}

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	public LibraryBranch getBranch() {
		return branch;
	}

	public void setBranch(LibraryBranch branch) {
		this.branch = branch;
	}

	public int getNoOfCopies() {
		return noOfCopies;
	}

	public void setNoOfCopies(int noOfCopies) {
		this.noOfCopies = noOfCopies;
	}

}
