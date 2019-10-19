package com.ss.lms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;

import com.ss.lms.model.BranchPOJO;
import com.ss.lms.model.LibraryPOJO;
import com.ss.lms.services.LibrarianService;


@RestController
@RequestMapping("/lms/librarian/")
public class LibrarianController {
	
	@Autowired
	private LibrarianService library;

	@GetMapping("branches")
	@ResponseBody public ResponseEntity<?> getBranches(){
		BranchPOJO[] branch = library.getBranches();
		return new ResponseEntity<BranchPOJO[]>(branch, HttpStatus.OK);
	}
	
	@GetMapping("branch/{branchId}")
	@ResponseBody public ResponseEntity<?> getBranchInfo(@PathVariable int branchId){
		try {
			BranchPOJO branch = library.getBranchInfo(branchId);
			return new ResponseEntity<BranchPOJO>(branch, HttpStatus.OK);
		}catch(HttpClientErrorException e){
			switch(e.getStatusCode()) {
			case NOT_FOUND:
				return new ResponseEntity<String>("Branch not found", HttpStatus.NOT_FOUND);
			default : 
				return new ResponseEntity<String>("Internal Server Error", HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}	
	}
	
	@PutMapping("branch/{branchId}")
	@ResponseBody public ResponseEntity<?> updateBranch(@PathVariable int branchId, @RequestBody BranchPOJO updateBranch){
		try {
			library.save(updateBranch, branchId);
			updateBranch.setBranchId(branchId);
			return new ResponseEntity<BranchPOJO>(updateBranch, HttpStatus.OK);
		}catch(HttpClientErrorException e){
			switch(e.getStatusCode()) {
			case NOT_FOUND:
				return new ResponseEntity<String>("Branch not found", HttpStatus.NOT_FOUND);
			default : 
				return new ResponseEntity<String>("Internal Server Error", HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}		
	}
	
	@GetMapping("branch/{branchId}/books")
	@ResponseBody public ResponseEntity<?> getBooks(@PathVariable int branchId) {
		try {
			 LibraryPOJO[] lib = library.getBooks(branchId);
			 return new ResponseEntity<LibraryPOJO[]>(lib, HttpStatus.OK);
		}catch(HttpClientErrorException e){
			switch(e.getStatusCode()) {
			case NOT_FOUND:
				return new ResponseEntity<String>("Branch not found", HttpStatus.NOT_FOUND);
			default : 
				return new ResponseEntity<String>("Internal Server Error", HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}	
	}
	
	@GetMapping("branch/{branchId}/book/{bookId}")
	@ResponseBody public ResponseEntity<?> getBookInfo(@PathVariable int branchId, @PathVariable int bookId) {
		
		try {
			LibraryPOJO lib = library.getBookInfo(bookId, branchId);
			return new ResponseEntity<LibraryPOJO>(lib, HttpStatus.OK);
		}catch(HttpClientErrorException e){
			switch(e.getStatusCode()) {
			case NOT_FOUND:
				return new ResponseEntity<String>("Book not found in this Branch", HttpStatus.NOT_FOUND);
			default : 
				return new ResponseEntity<String>("Internal Server Error", HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}
	}
	
	@PutMapping("branch/{branchId}/book/{bookId}")
	@ResponseBody public ResponseEntity<?> updateNoofCopies(@PathVariable int branchId, @PathVariable int bookId, @RequestBody LibraryPOJO newCopies) {
		try {
			LibraryPOJO book = library.getBookInfo(bookId, branchId);
			library.addCopies(bookId, branchId, newCopies);
			int copies = book.getNoOfCopies() + newCopies.getNoOfCopies();
			book.setNoOfCopies(copies);
			return new ResponseEntity<LibraryPOJO>(book, HttpStatus.OK);
		}catch(HttpClientErrorException e){
			switch(e.getStatusCode()) {
			case NOT_FOUND:
				return new ResponseEntity<String>("Book not found in this Branch", HttpStatus.NOT_FOUND);
			default : 
				return new ResponseEntity<String>("Internal Server Error", HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}
	}
}
