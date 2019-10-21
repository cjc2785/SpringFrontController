package com.ss.lms.services;

import com.ss.lms.dao.AdminDao;
import com.ss.lms.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminService {
    @Autowired
    private AdminDao adminDao;

    public LibraryBranch[] getBranches ()
    {
       return adminDao.getBranches();
    }
    public LibraryBranch getBranch (Integer branchId)
    {
        return adminDao.getBranch(branchId);
    }
    public  void newBranch(LibraryBranch libraryBranch)
    {
        adminDao.save(libraryBranch);
    }
    public void editBranch(LibraryBranch libraryBranch, Integer b_id)
    {
        adminDao.put(libraryBranch, b_id);
    }
    public void deleteBranch( Integer b_id)
    {
        adminDao.deleteLibraryBranch(b_id);
    }






    public Author[] getAuthors()
    {
        return  adminDao.getAuthors();
    }
    public Author getAuthor(Integer authorId)
    {
        return adminDao.getAuthor(authorId);
    }
    public void newAuthor(Author author)
    {
        adminDao.save(author);
    }
    public void editAuthor(Author author, Integer authorId)
    {
        adminDao.put(author,authorId);
    }
    public void deleteAuthor( Integer a_id)
    {
        adminDao.deleteAuthor(a_id);
    }
    public Book[] getBooksAuthId(Integer authorId) { return adminDao.getBooksByAuthor(authorId);}



    public Publisher[] getPublishers()
    {
        return adminDao.getPublishers();
    }
    public Publisher getPublisher(Integer publisherId)
    {
        return adminDao.getPublisher(publisherId);
    }
    public void editPublisher(Publisher publisher, Integer p_id)
    {
        adminDao.put(publisher,p_id);
    }
    public void newPublisher(Publisher publisher)
    {
        adminDao.save(publisher);
    }
    public void deletePublisher( Integer p_id)
    {
        adminDao.deletePublisher(p_id);
    }




    public Borrower[] getBorrowers()
    {
        return adminDao.getBorrowers();
    }
    public Borrower getBorrower(Integer cardNo)
    {
        return adminDao.getBorrower(cardNo);
    }
    public void editBorrower(Borrower borrower, Integer cardNo) { adminDao.put(borrower,cardNo); }
    public void newBorrower(Borrower borrower) { adminDao.save(borrower);}
    public void deleteBorrower( Integer c_n)
    {
        adminDao.deleteBorrower(c_n);
    }




    public Book[] getBooks()
    {
        return adminDao.getBooks();
    }
    public Book getBook(Integer bookId)
    {
        return adminDao.getBook(bookId);
    }
    public void editBook(Book book, Integer bk_id )
    {
        adminDao.put(book,bk_id);
    }
    public void newBook(Book book) {adminDao.save(book);}
    public void deleteBook( Integer bk_id)
    {
        adminDao.deleteBook(bk_id);
    }
    public BookLoans[] getBookLoans()
    {
        return adminDao.getBookLoans();
    }
    public BookLoans getBookLoan(Integer cardNo, Integer bookId)
    {
        return adminDao.getBookLoan(cardNo, bookId);
    }
    public void editBookLoan(BookLoans bookLoans, Integer c_n,Integer bk_id)
    {
        adminDao.put(bookLoans,c_n,bk_id);
    }
}
