package com.ss.lms.controller;

import com.ss.lms.services.*;

import com.ss.lms.model.*;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;


@RestController
@RequestMapping("/lms/borrower/")
public class BorrowerController {

	@Autowired
	private BorrowerService borrowerService;
	
	//Handle all rest template 404s by sending a 404 to 
	//  the client
	@ExceptionHandler(HttpClientErrorException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public @ResponseBody String handleResourceNotFound() { 
		return "Resource not found";
	}

	//Get all branches
	@GetMapping(
			value="branches",
			produces={MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	public LibraryBranch[] getBranches() {
		return borrowerService.getBranches();
	}

	//Get books with at least 1 copy available at branch
	@GetMapping(value="branches/{branchId}/books",
			produces= {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	public Book[] getBooks(@PathVariable("branchId") int branchId) {

			return borrowerService.getBooks(branchId);
	}

	//Get branches where borrower has at least 1 book checked out
	@GetMapping(value="borrowers/{cardNo}/branches",
			produces= {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	public LibraryBranch[] getBranches(@PathVariable("cardNo") int cardNo) {
	
			return borrowerService.getBranches(cardNo);
	}



	//Get all books checked out by borrower at library
	@GetMapping(value="borrowers/{cardNo}/branches/{branchId}/books",
			produces= {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
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

			borrowerService.insertLoan(cardNo, branchId, bookId);	
	} 


	//Return a book
	//Sends a 404 if the loan does not exist
	@DeleteMapping("borrowers/{cardNo}/branches/{branchId}/books/{bookId}")
	public void deleteLoan(
			@PathVariable("cardNo") int cardNo,
			@PathVariable("branchId") int branchId,
			@PathVariable("bookId") int bookId) {

			borrowerService.deleteLoan(cardNo, branchId, bookId);
	} 
}