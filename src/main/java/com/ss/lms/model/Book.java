package com.ss.lms.model;

public class Book {
    protected Integer bookId;
    protected String bookTitle;
    private Author bookAuthor;
    private Publisher bookPublisher;
    
    public Book() {};
    public Book(Integer Id, String title, Author author, Publisher publisher) {
    	bookId = Id;
    	bookTitle = title;
    	bookAuthor = author;
    	bookPublisher = publisher;
    	
    }

    public Integer getBookId() {
        return bookId;
    }

    public void setBookId(Integer bookId) {
        this.bookId = bookId;
    }

    public String getBookTitle() {
        return bookTitle;
    }

    public void setBookTitle(String bookTitle) {
        this.bookTitle = bookTitle;
    }

    public Author getBookAuthor() {
        return bookAuthor;
    }

    public void setBookAuthor(Author bookAuthor) {
        this.bookAuthor = bookAuthor;
    }

    public Publisher getBookPublisher() {
        return bookPublisher;
    }

    public void setBookPublisher(Publisher bookPublisher) {
        this.bookPublisher = bookPublisher;
    }
}
