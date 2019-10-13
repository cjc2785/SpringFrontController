package com.ss.lms.services;

import java.sql.SQLException;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ss.lms.exceptions.*;
import com.ss.lms.dao.*;
import com.ss.lms.model.*;

@Service
public class BookLoanService {
	
	@Autowired
	private BorrowerDao borrowerDao;
	
	@Autowired
	private LibraryBranchDao branchDao;
	
	@Autowired
	private BookDao bookDao;
	
	@Autowired
	private BookLoanDao loanDao;
	
	@Autowired
	private BookCopiesDao copiesDao;
	

	public List<BookLoans> getAll(Borrower borrower, LibraryBranch branch) throws SQLException {
		return loanDao.getAll(borrower, branch);
	}
	
	public Optional<BookLoans> get(Borrower borrower, LibraryBranch branch, Book book) 
		throws SQLException {
		
		return loanDao.get(borrower, branch, book);
	}

	//Loan Update
	public void updateDueDate(BookLoans loan) throws SQLException {
		loanDao.updateDueDate(loan);
	
	}
	
	//Also decrements the noOfCopies 
	//Throws EntityDoesNotExistException if the 
	//  borrower, branch, or book does not exist
	//Throws DuplicateIdException if the loan exists
	public void insert(BookLoans loan) throws 
		EntityDoesNotExistException, DuplicateIdException, SQLException {
		
		
		
		//Validate the loan
		
		Optional<Borrower> optBorrower = borrowerDao.get(loan.getCardNo());
		
		if(optBorrower.isEmpty()) {
			throw new EntityDoesNotExistException("borrower");
		}
		
		Borrower borrower = optBorrower.get();
		Optional<LibraryBranch> optBranch = branchDao.get(loan.getBranchId());
		
		if(optBranch.isEmpty()) {
			throw new EntityDoesNotExistException("branch");
		}
		
		LibraryBranch branch = optBranch.get();
		
		Optional<Book> optBook = bookDao.get(branch, loan.getBookId());
		
		if(optBook.isEmpty()) {
			throw new EntityDoesNotExistException("book");
		}
		
		Book book = optBook.get();
		Optional<BookLoans> existing = loanDao.get(
				borrower, branch, book
				);
		
		//Throw if the loan exists
		if(existing.isPresent()) {
			throw new DuplicateIdException();
		}
		
	
		
		BookCopies copies = copiesDao.get(branch,  book).get();
		
		//decrement the noOfCopies
		copies.setNoOfCopies(copies.getNoOfCopies() - 1);
		copiesDao.update(copies);
		
		loanDao.insert(loan);
	}
	
	//Also increments the noOfCopies 
	//Throws EntityDoesNotExistException if the loan exists
	public void delete(BookLoans loan) 
			throws EntityDoesNotExistException, SQLException {
		
		Borrower borrower = new Borrower(loan.getCardNo(), null, null, null);
		LibraryBranch branch = new LibraryBranch(loan.getBranchId(), null, null);
		Book book = new Book(loan.getBookId(), null, null, null);
		
		Optional<BookLoans> existing = loanDao.get(
				borrower, branch, book
				);
		
		//throw if the loan does not exist
		if(existing.isEmpty()) {
			throw new EntityDoesNotExistException("loan");
		}
		
		
		
		BookCopies copies = copiesDao.get(branch, book).get();
		
		//increment the noOfCopies
		copies.setNoOfCopies(copies.getNoOfCopies() + 1);
		copiesDao.update(copies);
		
		loanDao.delete(loan);
	}
}
