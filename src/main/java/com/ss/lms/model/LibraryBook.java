package com.ss.lms.model;

public class LibraryBook extends Book {

	private int noOfCopies;
	
	
	public LibraryBook(int id, String title, int copies) {
		bookId = id;
		bookTitle = title;
		noOfCopies = copies;
	}

	public int getNoOfCopies() {
		return noOfCopies;
	}

	public void setNoOfCopies(int noOfCopies) {
		this.noOfCopies = noOfCopies;
	}
	
	
}
