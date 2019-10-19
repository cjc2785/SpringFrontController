package com.ss.lms.dao;


import com.ss.lms.model.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class AdminDao {

    RestTemplate template = new RestTemplate();

    private static final String BaseRoute = "http://localhost:8087/lms/admin/";

    public Author[] getAuthors()
    {
        String url = BaseRoute + "authors";
        return  template.getForObject(url , Author[].class);
    }
    public void put(Author author, Integer a_id)
    {
        String url = BaseRoute + "/authors/{a_id}";
        template.put(url,author,a_id);
    }
    public void save(Author author)
    {
        String url = BaseRoute + "/authors";
        template.postForObject(url, author, Author.class);
    }
    public void deleteAuthor( Integer a_id)
    {
        String url = BaseRoute + "/authors/{a_id}";
        template.delete(url ,a_id);
    }
    public Author getAuthor(Integer authorId)
    {
        String url = BaseRoute + "authors/{a_id}";
        return template.getForObject(url, Author.class, authorId);
    }
    public Book[] getBooksAuthorId(Integer authorId)
    {
        String url = BaseRoute + "authors/{a_id}/books";
        return template.getForObject(url, Book[].class, authorId);
    }






    public BookLoans[] getBookLoans()
    {
        String url = BaseRoute + "loans";
        return template.getForObject(url, BookLoans[].class);
    }
    public BookLoans getBookLoan(Integer cardNo, Integer bookId)
    {
        String url = BaseRoute + "loans/borrowers/{c_n}/books/{bk_id}";
        return template.getForObject(url, BookLoans.class, cardNo,bookId);
    }
    public void put(BookLoans bookLoans,Integer cardNo, Integer bookId)
    {
        String url = BaseRoute + "loans/borrowers/{c_n}/books/{bk_id}";
         template.put(url, bookLoans, cardNo,bookId);
    }


    public Borrower[] getBorrowers()
    {
        String url = BaseRoute + "borrowers";
        return template.getForObject(url, Borrower[].class);
    }
    public Borrower getBorrower(Integer cardNo)
    {
        String url = BaseRoute + "borrowers/{c_n}";
        return template.getForObject(url, Borrower.class, cardNo);
    }
    public void save(Borrower borrower)
    {
        String url = BaseRoute + "/borrowers/";
        template.postForObject(url, borrower, Borrower.class);
    }
    public void put(Borrower borrower, Integer c_n)
    {
        String url = BaseRoute + "/borrowers/{c_n}";
        template.put(url,borrower,c_n);
    }
    public void deleteBorrower(Integer c_n)
    {
        String url = BaseRoute + "/borrowers/{c_n}";
        template.delete(url,c_n);
    }



    public void save(Publisher publisher)
    {
        String url = BaseRoute + "/publishers/";
        template.postForObject(url,publisher, Borrower.class);
    }
    public void put(Publisher publisher, Integer p_id)
    {
        String url = BaseRoute + "/publishers/{p_id}";
        template.put(url,publisher,p_id);
    }
    public void deletePublisher( Integer p_id)
    {
        String url = BaseRoute + "/publishers/{p_id}";
        template.delete(url,p_id);
    }

    public Publisher[] getPublishers()
    {
        String url = BaseRoute + "publishers";
        return template.getForObject(url, Publisher[].class);
    }
    public Publisher getPublisher(Integer publisherId)
    {
        String url = BaseRoute + "publishers/{p_id}";
        return template.getForObject(url, Publisher.class, publisherId);
    }





    public void save(Book book)
    {
        String url = BaseRoute + "/books/";
        template.postForObject(url,book, Book.class);
    }
    public void put(Book book, Integer bk_id)
    {
        String url = BaseRoute + "/books/{bk_id}";
        template.put(url,book,bk_id);
    }
    public void deleteBook(Integer bk_id)
    {
        String url = BaseRoute + "/books/{bk_id}";
        template.delete(url,bk_id);
    }

    public Book[] getBooks()
    {
        String url = BaseRoute + "books";
        return  template.getForObject(url, Book[].class);
    }
    public Book getBook(Integer bookId)
    {
        String url = BaseRoute + "books/{bk_id}";
        return  template.getForObject(url, Book.class, bookId);
    }







    public void save(LibraryBranch libraryBranch)
    {
        String url = BaseRoute + "/branches/";
        template.postForObject(url,libraryBranch,LibraryBranch.class);
    }
    public void put(LibraryBranch libraryBranch,Integer b_id)
    {
        String url = BaseRoute + "/branches/{b_id}";
        template.put(url,libraryBranch,b_id);
    }
    public void deleteLibraryBranch( Integer b_id)
    {
        String url = BaseRoute + "/branches/{b_id}";
        template.delete(url, b_id);
    }
    public LibraryBranch[] getBranches()
    {
        String url = BaseRoute + "branches";
        return template.getForObject(url, LibraryBranch[].class);
    }
    public LibraryBranch getBranch(Integer branchId)
    {
        String url = BaseRoute + "branches/{b_id}";
        return template.getForObject(url, LibraryBranch.class, branchId);
    }
}
