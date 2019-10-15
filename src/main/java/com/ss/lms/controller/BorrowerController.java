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
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.server.ResponseStatusException;


@RestController
@RequestMapping("/lms/borrower/")
public class BorrowerController {

	@Autowired
	private BorrowerService borrowerService;

	//Get all branches
	@GetMapping("branches")
	public LibraryBranch[] getBranches() {
		try {
			return borrowerService.getBranches();
		} catch (HttpClientErrorException e) {
			throw new ResponseStatusException(
					HttpStatus.INTERNAL_SERVER_ERROR, 
					"borrower service is down");
		}
	}

	//Get books with at least 1 copy available at branch
	@GetMapping("branches/{branchId}/books")
	public Book[] getBooks(@PathVariable("branchId") int branchId) {
		try {
			return borrowerService.getBooks(branchId);
		} catch(HttpClientErrorException e) {
			switch(e.getStatusCode()) {
			case NOT_FOUND :
				throw new ResponseStatusException(HttpStatus.NOT_FOUND);
			default:
				throw new ResponseStatusException(
						HttpStatus.INTERNAL_SERVER_ERROR, 
						"borrower service is down");
			}
		}
	}

	//Get branches where borrower has at least 1 book checked out
	@GetMapping("borrowers/{cardNo}/branches")
	public LibraryBranch[] getBranches(@PathVariable("cardNo") int cardNo) {
		try {
			return borrowerService.getBranches(cardNo);
		} catch(HttpClientErrorException e) {
			switch(e.getStatusCode()) {
			case NOT_FOUND :
				throw new ResponseStatusException(HttpStatus.NOT_FOUND);
			default:
				throw new ResponseStatusException(
						HttpStatus.INTERNAL_SERVER_ERROR, 
						"borrower service is down");
			}
		}
	}



	//Get all books checked out by borrower at library
	@GetMapping("borrowers/{cardNo}/branches/{branchId}/books")
	public Book[] getBooks(
			@PathVariable("cardNo") int cardNo,
			@PathVariable("branchId") int branchId) {
		try {
			return borrowerService.getBooks(cardNo, branchId);
		} catch(HttpClientErrorException e) {
			switch(e.getStatusCode()) {
			case NOT_FOUND :
				throw new ResponseStatusException(
						HttpStatus.NOT_FOUND, e.getMessage()
						);
			default:
				throw new ResponseStatusException(
						HttpStatus.INTERNAL_SERVER_ERROR, 
						"borrower service is down");
			}
		}
	} 

	//Check out a book
	//Creates the outDate & dueDate
	@PostMapping("borrowers/{cardNo}/branches/{branchId}/books/{bookId}")
	@ResponseStatus(HttpStatus.CREATED)
	public void insertLoan(
			@PathVariable("cardNo") int cardNo,
			@PathVariable("branchId") int branchId,
			@PathVariable("bookId") int bookId) {

		try {
			borrowerService.insertLoan(cardNo, branchId, bookId);
		} catch(HttpClientErrorException e) {
			
			switch(e.getStatusCode()) {
			case NOT_FOUND :
				throw new ResponseStatusException(
						HttpStatus.NOT_FOUND, e.getMessage()
						);
			default:
				throw new ResponseStatusException(
						HttpStatus.INTERNAL_SERVER_ERROR, 
						"borrower service is down");
			}
		}
	} 


	//Return a book
	//Sends a 404 if the loan does not exist
	@DeleteMapping("borrowers/{cardNo}/branches/{branchId}/books/{bookId}")
	public void deleteLoan(
			@PathVariable("cardNo") int cardNo,
			@PathVariable("branchId") int branchId,
			@PathVariable("bookId") int bookId) {

		try {
			borrowerService.deleteLoan(cardNo, branchId, bookId);
		} catch(HttpClientErrorException e) {
			switch(e.getStatusCode()) {
			case NOT_FOUND :
				throw new ResponseStatusException(
						HttpStatus.NOT_FOUND, e.getMessage()
						);
			default:
				throw new ResponseStatusException(
						HttpStatus.INTERNAL_SERVER_ERROR, 
						"borrower service is down");
			}
		}
	} 
}