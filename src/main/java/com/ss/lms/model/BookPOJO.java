package com.ss.lms.model;

public class BookPOJO {

	private int bookId;
	private String title;
	
	public BookPOJO() {}
	
	public BookPOJO(int id, String title) {
		this.bookId = id;
		this.title = title;
		
	}

	public int getBookId() {
		return bookId;
	}

	public void setBookId(int bookId) {
		this.bookId = bookId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String bookTitle) {
		this.title = bookTitle;
	}
}
