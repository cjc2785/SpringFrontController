package com.ss.lms.controller;

import com.ss.lms.services.*;
import com.ss.lms.exceptions.*;
import com.ss.lms.model.*;

import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/lms/borrower/")
public class BorrowerController {
	
	
    @Autowired
    private BookLoanService loanService;
    
    @Autowired
    private BorrowerService borrowerService;
    
    @Autowired
    private BookService bookService;
    
    @Autowired
    private LibraryBranchService branchService;
    
    //Get all branches
    @GetMapping("branches")
    public List<LibraryBranch> getBranches() {
    	try {
    	List<LibraryBranch> branches = branchService.getAll();
    	return branches;
    	} catch (SQLException e) {
    		throw new RuntimeException(e);
    	}
    }
    
    //Get books with at least 1 copy available at branch
    @GetMapping("branches/{branchId}/books")
    public List<Book> getBooks(@PathVariable("branchId") int branchId) {
    	try {
    		Optional<LibraryBranch> optBranch = branchService.get(branchId);
    		
    		if(optBranch.isEmpty()) {
    			
    			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    		}
    		
    		LibraryBranch branch = optBranch.get();
    		
    		List<Book> books = bookService.getAll(branch);
    		return books;
    	} catch (SQLException e) {
    		throw new RuntimeException(e);
    	}
    }
    
    //Get branches where borrower has at least 1 book checked out
    @GetMapping("borrowers/{cardNo}/branches")
    public List<LibraryBranch> getBranches(@PathVariable("cardNo") int cardNo) {
    	try {
    		Optional<Borrower> optBorrower = borrowerService.get(cardNo);
    		
    		if(optBorrower.isEmpty()) {
    			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    		}
    		
    		Borrower borrower = optBorrower.get();
    		
    		List<LibraryBranch> branches = branchService.getAll(borrower);
    		
    		return branches;
   
    	} catch (SQLException e) {
    		throw new RuntimeException(e);
    	}
    }
    
    
    
    //Get all books checked out by borrower at library
    @GetMapping("borrowers/{cardNo}/branches/{branchId}/books")
    public List<Book> getBooks(
    		@PathVariable("cardNo") int cardNo,
    		@PathVariable("branchId") int branchId) {
    	
    	try {
    		Optional<Borrower> optBorrower = borrowerService.get(cardNo);
    		
    		if(optBorrower.isEmpty()) {
    			throw new ResponseStatusException(
    					HttpStatus.NOT_FOUND, 
    					"borrower does not exist"
    					);
    		}
    		
    		Optional<LibraryBranch> optBranch = branchService.get(branchId);
    		
    		if(optBranch.isEmpty()) {
    			throw new ResponseStatusException(
    					HttpStatus.NOT_FOUND, 
    					"branch does not exist"
    					);
    		}
    		
    		Borrower borrower = optBorrower.get();
    		LibraryBranch branch = optBranch.get();
    		
    		List<Book> books = bookService.getAll(borrower, branch);
    		return books;
    		
    	} catch (SQLException e) {
    		throw new RuntimeException(e);
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
    	
    		//Create the loan
    		LocalDate outDate = LocalDate.now();
    		LocalDate dueDate = outDate.plusWeeks(1);
    		
    		BookLoans loan = new BookLoans(
    				bookId, branchId, cardNo,
    				Date.valueOf(outDate), 
    				Date.valueOf(dueDate)
    				);
    		
    		
    		
    		loanService.insert(loan);
    		
    	} catch (EntityDoesNotExistException e) {
    		String entity = e.getEntity();
    		throw new ResponseStatusException(
					HttpStatus.NOT_FOUND, 
					entity + " does not exist"
					);
    	} catch (DuplicateIdException e) {
       		throw new ResponseStatusException(
    					HttpStatus.NOT_FOUND, 
    					"loan exists"
    					);
    	}
    	catch (SQLException e) {
    		throw new RuntimeException(e);
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
    	
    		BookLoans loan = new BookLoans(
    				bookId, branchId, cardNo,
    				null, 
    				null
    				);
    		
    		
    		loanService.delete(loan);
    		
    	} catch (EntityDoesNotExistException e) {
    		//The loan does not exist
    		
    		throw new ResponseStatusException(
					HttpStatus.NOT_FOUND, 
					"loan does not exist"
					);
    	} 
    	catch (SQLException e) {
    		throw new RuntimeException(e);
    	}
    } 
}