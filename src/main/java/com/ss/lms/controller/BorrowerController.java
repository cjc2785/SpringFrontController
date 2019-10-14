package com.ss.lms.controller;

import com.ss.lms.services.*;

import com.ss.lms.model.*;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/lms/borrower/")
public class BorrowerController {
	
    @Autowired
    private BorrowerService borrowerService;

    
    //Get all branches
    @GetMapping("branches")
    public LibraryBranch[] getBranches() {
    	return borrowerService.getBranches();
    }
    
    //Get books with at least 1 copy available at branch
    @GetMapping("branches/{branchId}/books")
    public Book[] getBooks(@PathVariable("branchId") int branchId) {
    	return borrowerService.getBooks(branchId);
    }
    
    //Get branches where borrower has at least 1 book checked out
    @GetMapping("borrowers/{cardNo}/branches")
    public LibraryBranch[] getBranches(@PathVariable("cardNo") int cardNo) {
    	return borrowerService.getBranches(cardNo);
    }
    
    
    
    //Get all books checked out by borrower at library
    @GetMapping("borrowers/{cardNo}/branches/{branchId}/books")
    public Book[] getBooks(
    		@PathVariable("cardNo") int cardNo,
    		@PathVariable("branchId") int branchId) {
    		
    	return borrowerService.getBooks(cardNo, branchId);
    } 
    
    //Check out a book
    //Creates the outDate & dueDate
    @PostMapping("borrowers/{cardNo}/branches/{branchId}/books/{bookId}")
    @ResponseStatus(HttpStatus.CREATED)
    public void insertLoan(
    		@PathVariable("cardNo") int cardNo,
    		@PathVariable("branchId") int branchId,
    		@PathVariable("bookId") int bookId) {
    	
    	
    } 
    
    
    //Return a book
    //Sends a 404 if the loan does not exist
    @DeleteMapping("borrowers/{cardNo}/branches/{branchId}/books/{bookId}")
    public void deleteLoan(
    		@PathVariable("cardNo") int cardNo,
    		@PathVariable("branchId") int branchId,
    		@PathVariable("bookId") int bookId) {
    	
    	
    } 
}