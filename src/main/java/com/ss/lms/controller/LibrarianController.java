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
import org.springframework.web.server.ResponseStatusException;

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
	@ResponseBody public BranchPOJO getBranchInfo(@PathVariable int branchId){
		try {
			return library.getBranchInfo(branchId);
		}catch(HttpClientErrorException e){
			switch(e.getStatusCode()) {
			case NOT_FOUND:
				throw new ResponseStatusException(HttpStatus.NOT_FOUND);
			default : 
				throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}	
	}
	
	@PutMapping("branch/{branchId}")
	@ResponseBody public void updateBranch(@PathVariable int branchId, @RequestBody BranchPOJO updateBranch){
		try {
			library.save(updateBranch, branchId);
		}catch(HttpClientErrorException e){
			switch(e.getStatusCode()) {
			case NOT_FOUND:
				throw new ResponseStatusException(HttpStatus.NOT_FOUND);
			default : 
				throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}		
	}
	
	@GetMapping("branch/{branchId}/books")
	@ResponseBody public LibraryPOJO[] getBooks(@PathVariable int branchId) {
		try {
			return library.getBooks(branchId);
		}catch(HttpClientErrorException e){
			switch(e.getStatusCode()) {
			case NOT_FOUND:
				throw new ResponseStatusException(HttpStatus.NOT_FOUND);
			default : 
				throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}	
	}
	
	@GetMapping("branch/{branchId}/book/{bookId}")
	@ResponseBody public LibraryPOJO getBookInfo(@PathVariable int branchId, @PathVariable int bookId) {
		
		try {
			return library.getBookInfo(bookId, branchId);
		}catch(HttpClientErrorException e){
			switch(e.getStatusCode()) {
			case NOT_FOUND:
				throw new ResponseStatusException(HttpStatus.NOT_FOUND);
			default : 
				throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}
	}
	
	@PutMapping("branch/{branchId}/book/{bookId}")
	@ResponseBody public void updateNoofCopies(@PathVariable int branchId, @PathVariable int bookId, @RequestBody LibraryPOJO newCopies) {
		try {
			library.addCopies(bookId, branchId, newCopies);
		}catch(HttpClientErrorException e){
			switch(e.getStatusCode()) {
			case NOT_FOUND:
				throw new ResponseStatusException(HttpStatus.NOT_FOUND);
			default : 
				throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}
	}
}
