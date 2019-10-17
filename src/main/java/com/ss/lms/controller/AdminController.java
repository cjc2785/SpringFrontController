package com.ss.lms.controller;



import com.ss.lms.model.*;
import com.ss.lms.services.AdminService;
import org.dom4j.Branch;
import org.springframework.http.HttpStatus;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;

import javax.validation.Valid;

@RestController
@RequestMapping("/lms/admin/")
public class AdminController {

    private final AdminService adminService;

    public AdminController( AdminService adminService) {
        this.adminService = adminService;
    }

    @ExceptionHandler(HttpClientErrorException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public @ResponseBody String handleResourceNotFound( ) {return "Could not find resource";}
    //AUTHOR//
    //AUTHOR//
    @PostMapping("/author/")
    public void addAuthor(@RequestBody Author authorDetails) {
        adminService.newAuthor(authorDetails);
    }
    @PutMapping("/author/{a_id}")

    public void  updateAuthorById( @PathVariable Integer a_id ,@Valid @RequestBody Author authorDetails)
    {
         adminService.editAuthor(authorDetails,a_id);
         Author author = adminService.getAuthor(a_id);
//         if (author == null) throw new BadRequestException();

    }


    @GetMapping("/authors")
    public Author[] getAllAuthors() {
        return adminService.getAuthors();
    }

    @GetMapping("/author/{a_id}")
    public Author getAuthorById(
            @PathVariable Integer a_id) {
       Author author = adminService.getAuthor(a_id);
//        if (author == null) throw new BadRequestException();
        return author;
    }
    @DeleteMapping("/author/{a_id}")
    public void deleteAuthorById(@PathVariable Integer a_id){
        adminService.deleteAuthor(a_id);
    }

    //Publisher//
    //Publisher//
    @PostMapping("/publisher/")
    public @Valid @ResponseBody
    void addPublisher(@RequestBody Publisher publisherDetails)
    {
        adminService.newPublisher(publisherDetails);
    }
    @GetMapping("/publisher/{p_id}")
    public Publisher getPublisherById(@PathVariable Integer p_id)
    {
        return adminService.getPublisher(p_id);
    }
    @PutMapping("/publisher/{p_id}")
    public void updatePublisherById( @PathVariable Integer p_id ,@Valid @RequestBody Publisher publisherDetails)
    {
        adminService.editPublisher(publisherDetails,p_id);
    }
    @DeleteMapping("/publisher/{p_id}")
    public void deletePublisherById(@PathVariable Integer p_id)
    {
        adminService.deletePublisher(p_id);
    }
    @GetMapping("/publishers")
    public Publisher[] getAllPublishers()
    {
        return adminService.getPublishers();
    }

    ///LIBRARY BRANCH///
    ///LIBRARY BRANCH///
    ///LIBRARY BRANCH///
    ///LIBRARY BRANCH///
    @PostMapping("/branch/")
    public @Valid @ResponseBody
    void addLibraryBranch(@RequestBody LibraryBranch libraryBranchDetails)
    {
        adminService.newBranch(libraryBranchDetails);
    }

    @GetMapping("/branch/{b_id}")
    public ResponseEntity<?> getLibraryBranchById(@PathVariable Integer b_id)
    {
        LibraryBranch branch = adminService.getBranch(b_id);
        if (branch == null)
        {
            return new ResponseEntity<>("not valid", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<LibraryBranch>(branch, HttpStatus.OK);

    }
    @PutMapping("/branch/{b_id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void updateLibraryBranchById( @PathVariable Integer b_id, @RequestBody LibraryBranch libraryBranch)
    {
        adminService.editBranch(libraryBranch,b_id);
    }
    @DeleteMapping("/branch/{b_id}")
    public void deleteLibraryBranchById(@PathVariable Integer b_id)
    {
         adminService.deleteBranch(b_id);
    }
    @GetMapping("/branches")
    public LibraryBranch[] getAllLibraryBranch()
    {
        return adminService.getBranches();
    }

   ///Borrower///
   ///Borrower///
   ///Borrower///
   ///Borrower///
    @PostMapping("/borrower/")
    @ResponseStatus(HttpStatus.CREATED)
    public @Valid @ResponseBody
    void addLibraryBranch(@RequestBody Borrower borrowerDetails)
    {
        adminService.newBorrower(borrowerDetails);
    }
    @GetMapping("/borrower/{c_n}")
    @ResponseStatus(HttpStatus.OK)
    public Borrower getBorrowerByCardNo(@PathVariable Integer br_id)
    {
      return adminService.getBorrower(br_id);
    }
    @PutMapping("/borrower/{c_n}")
    public void updateBorrowerByCardNo( @PathVariable Integer c_n ,@Valid @RequestBody Borrower borrowerDetails)
    {
        adminService.editBorrower(borrowerDetails,c_n);
    }
    @DeleteMapping("/borrower/{c_n}")
    public void deleteBorrowerByCardNo(@PathVariable Integer c_n)
    {
         adminService.deleteBorrower(c_n);
    }
    @GetMapping("/borrowers")
    public Borrower[] getAllBorrowers()
    {
        return adminService.getBorrowers();
    }

    ///Book///
    ///Book///
    ///Book///
    @PostMapping("/book/")
    @ResponseStatus(HttpStatus.CREATED)
    public void addBook(@PathVariable Integer bk_id, @RequestBody Book book) { }

    @GetMapping("/book/{bk_id}")
    public Book getBookById(@PathVariable Integer bk_id)
    {
        return adminService.getBook(bk_id);
    }
    @PutMapping("/book/{bk_id}")
    public void updateBookById( @PathVariable Integer bk_id ,@Valid @RequestBody Book bookDetails)
    {
        adminService.editBook(bookDetails,bk_id);
    }
    @DeleteMapping("/book/{bk_id}")
    public void deleteBookById(@PathVariable Integer bk_id)
    {
         adminService.deleteBook(bk_id);
    }
    @GetMapping("/books")
    public Book[] getAllBooks()
    {
        return adminService.getBooks();
    }

    ///BOOK LOANS///
    ///BOOK LOANS///
    ///BOOK LOANS///
    @GetMapping("/loans")
    public BookLoans[] getAllBookLoans() {return adminService.getBookLoans();}
    @PutMapping("/loan/borrower/{c_n}/book/{bk_id}")
    public void updateDueDate(@PathVariable Integer c_n, @PathVariable Integer bk_id,@RequestBody BookLoans bookLoansDetail)
    {
        adminService.editBookLoan(bookLoansDetail,c_n,bk_id);
    }

}
