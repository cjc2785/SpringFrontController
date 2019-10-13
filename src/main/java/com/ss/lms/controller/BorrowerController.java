package com.ss.lms.controller;

import com.ss.lms.services.*;
import com.ss.lms.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/lms/borrower/")
public class BorrowerController {
	
	
    @Autowired
    private BookLoanService loanService;
    
    @Autowired
    private BorrowerService borrowerService;
    
    
    @GetMapping("branches")
    public LibraryBranch getBranches() {
    	return new LibraryBranch(1, null, "1 chi street");
    }

}