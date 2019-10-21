package com.ss.lms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;

import com.ss.lms.model.LibraryBranch;
import com.ss.lms.model.BookCopies;
import com.ss.lms.services.LibrarianService;


@RestController
@RequestMapping("/lms/librarian/")
public class LibrarianController {
	
	@Autowired
	private LibrarianService library;

	
	@ExceptionHandler(HttpClientErrorException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public @ResponseBody String handleResourceNotFound() { 
		return "Resource not found";
	}
	
	
	@GetMapping("branches")
	@ResponseBody public LibraryBranch[] getBranches(){
		return library.getBranches();
	}
	
	@GetMapping("branch/{branchId}")
	@ResponseBody public LibraryBranch getBranchInfo(@PathVariable int branchId){
		return library.getBranchInfo(branchId);	
	}
	
	@PutMapping("branch/{branchId}")
	@ResponseBody public LibraryBranch updateBranch(@PathVariable int branchId, @RequestBody LibraryBranch updateBranch){
		library.save(updateBranch, branchId);
		updateBranch.setBranchId(branchId);	
		return updateBranch;
	}
	
	@GetMapping("branch/{branchId}/books")
	@ResponseBody public BookCopies[] getBooks(@PathVariable int branchId) {
		return library.getBooks(branchId);
	}
	
	@GetMapping("branch/{branchId}/book/{bookId}")

	@ResponseBody public BookCopies getBookInfo(@PathVariable int branchId, @PathVariable int bookId) {
			return library.getBookInfo(bookId, branchId);
	}
	
	@PutMapping("branch/{branchId}/book/{bookId}")
	@ResponseBody public BookCopies updateNoofCopies(@PathVariable int branchId, @PathVariable int bookId, @RequestBody BookCopies newCopies) {
		BookCopies book = library.getBookInfo(bookId, branchId);
		library.addCopies(bookId, branchId, newCopies);
		int copies = book.getNoOfCopies() + newCopies.getNoOfCopies();
		book.setNoOfCopies(copies);
		return book;
	}
}
