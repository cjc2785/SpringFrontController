package com.ss.lms.services;

import com.ss.lms.AdminMenu;
import com.ss.lms.dao.*;
import com.ss.lms.model.*;
import com.ss.lms.secret.GenerateID;

import java.sql.*;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.SortedMap;


public class AdminServices {
    private AdminDao<Book, Connection> bookDao = new AdminBookDao();
    private AdminDao<Borrower, Connection> borrowerDao = new AdminBorrowerDao();
    private AdminDao<Author, Connection> authorDao = new AdminAuthorDao();
    private AdminDao <Publisher, Connection> publisherDao = new AdminPublisherDao();
    private AdminDao<LibraryPOJO, Connection> libDao = new AdminLibBranchDao();
    private ValidationService valid = new ValidationService();
    private Scanner scan = new Scanner(System.in);
    private GenerateID genID = new GenerateID();
    //////// BOOK SERVICES /////////
    //////// BOOK SERVICES /////////
    //////// BOOK SERVICES /////////
	
    public void deleteBook(Book book, Connection connection)
    {

        try{
            int bookID = book.getBookId();
            getValidInput();
            boolean isFound = valid.validateBook(bookID,connection);
            if(!isFound)
            {
                System.out.println("This is not a valid ID");
            } else
            {
                bookDao.delete(book, connection);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public  void addBook(Book book, Connection connection)
    {
        try{
            Author author = new Author();
            Publisher publisher = new Publisher();
            
            System.out.println("Enter the Author's ID");
          
            author.setAuthorId(scan.nextInt());
            scan.nextLine();
            getValidInput();
            boolean isValid = valid.validateAuthor(author.getAuthorId(),connection);
            if(!isValid)
            {
                System.out.println("Could not Validate the Author");
                scan.nextLine();
            }
            else{
                System.out.println("Enter the Publisher's ID");
                getValidInput();
                publisher.setPublisherId(scan.nextInt());
                isValid = valid.validatePublisher(publisher.getPublisherId(),connection);
                if(!isValid)
                {
                    System.out.println("Could not Validate the Publisher");
                    scan.nextLine();
                }
                else{
                    scan.nextLine();
                    System.out.println("What is the Tile of this Book");
                    book.setBookTitle(scan.nextLine());
                    book.setBookId(genID.randomID());
                    book.setBookAuthor(author);
                    book.setBookPublisher(publisher);

                    bookDao.add(book,connection);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void updateBook(Book book, Connection connection)
    {
        try{
            int bookID = book.getBookId();
            getValidInput();
            boolean isFound = valid.validateBook(bookID,connection);
            if(!isFound)
            {
                System.out.println("This ID is not in the Database");
            }
            else {
                System.out.println("Enter the New Name of this book: ");
                Scanner scanner = new Scanner(System.in);
                book.setBookTitle(scanner.nextLine());
                bookDao.update(book, connection);
            }

        }catch (SQLException e)
        {
            e.printStackTrace();
        }
    }
    //////// BORROWER SERVICES ////////
    //////// BORROWER SERVICES ////////
    //////// BORROWER SERVICES ////////
    public void deleteBorrower(Borrower borrower, Connection connection)
    {
        try{
            System.out.println("What is the Card Number for the Borrower You wish to Edit");
            borrower.setBorrowerCardNumber(scan.nextInt());
            scan.nextLine();
            getValidInput();
            boolean isValid = valid.validateBorrower(borrower.getBorrowerCardNumber(),connection);
            if(!isValid)
            {
                System.out.println("Could not Validate this Card Number");
            }
            else{
                borrowerDao.delete(borrower, connection);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public  void addBorrower(Borrower borrower, Connection connection)
    {
        try{
           borrowerDao.add(borrower, connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void updateBorrower(Borrower borrower, Connection connection)
    {
        try{
            System.out.println("What is the Card Number for the Borrower You wish to Edit");
            borrower.setBorrowerCardNumber(scan.nextInt());
            scan.nextLine();
            getValidInput();
            boolean isValid = valid.validateBorrower(borrower.getBorrowerCardNumber(),connection);
            if(!isValid)
            {
                System.out.println("Could not Validate this Card Number");
            }
            else{
                System.out.println("What is the New Name for this Borrower");
                borrower.setBorrowerName(scan.nextLine());
                System.out.println("What is the New Address for this Borrower");
                borrower.setBorrowerAddress(scan.nextLine());
                System.out.println("what is the new Phone Number for this Borrower");
                borrower.setBorrowerPhoneNumber(scan.nextLine());
                borrowerDao.update(borrower, connection);
            }
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
    }
    public void listBorrower( Connection connection)
    {
        try{
            ListService list = new ListService();
            list.listBorrower(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
    //////// DUE DATE SERVICE /////////
    public void setDueDate(Connection connection)
    {
        try{
            Borrower borrower = new Borrower();
            Book book = new Book();
            Scanner scanner = new Scanner(System.in);
            ValidationService valid = new ValidationService();
            System.out.println("What is the Card Number of the user you wish to extend the due date: ");
            borrower.setBorrowerCardNumber(scanner.nextInt());
            getValidInput();
            boolean isValid = valid.validCardLoan(borrower.getBorrowerCardNumber(),connection);
            if (!isValid)
            {
                System.out.println("-------------------");
                System.out.println("This User does not have a book checked out");
                System.out.println("-------------------");
            }
            else {
                System.out.println("Input the Book ID you wish to update the date for: ");
                book.setBookId(scanner.nextInt());
                getValidInput();
                isValid = valid.validBookLoan(book.getBookId(), connection);
                if (!isValid)
                {
                    System.out.println("---------------------");
                    System.out.println("This Book is not being Checked out by this User");
                    System.out.println("---------------------");
                }
                else
                {
                    System.out.println("Input the Year of the Due Date (YYYY): ");
                    int year = scanner.nextInt();
                    scanner.nextLine();
                    System.out.println("Input the month of the New Due Date (MM): ");
                    String month = scanner.nextLine();
                    System.out.println("Input the Date of the New Due Date (DD):");
                    String day = scanner.nextLine();

                    String date = (year + month + day);
                    AdminDueDate dueDate = new AdminDueDate();
                    dueDate.dueDate(borrower,book,connection,date);
                }

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    //////// AUTHOR SERVICES ////////
    //////// AUTHOR SERVICES ////////
    //////// AUTHOR SERVICES ////////
    public void addAuthor(Author author, Connection connection)
    {
        try{
            authorDao.add(author, connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void deleteAuthor(Author author, Connection connection)
    {
        try{
            System.out.println("What is the ID of the Author You Wish to DELETE");
            author.setAuthorId(scan.nextInt());
            scan.nextLine();
            getValidInput();
            boolean isValid = valid.validateAuthor(author.getAuthorId(),connection);
            if(!isValid)
            {
                System.out.println("Could not Validate this Card Number");
            }
            else{
                System.out.println("Deleting author.....");
                authorDao.delete(author,connection);
            }
            authorDao.delete(author, connection);
            author.setAuthorId(null);
            author.setAuthorName(null);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void updateAuthor(Author author, Connection connection)
    {
        try{
            System.out.println("Enter the ID of the Author You wish to Update");
            author.setAuthorId(scan.nextInt());
            scan.nextLine();
            getValidInput();
            boolean isValid = valid.validateAuthor(author.getAuthorId(),connection);
            if(!isValid)
            {
                System.out.println("Could not Validate this Card Number");
            }
            else{
                System.out.println("What is the New name For this Author");
                author.setAuthorName(scan.nextLine());
                authorDao.update(author, connection);
            }
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
    }
    //////// PUBLISHER SERVICES ////////
    //////// PUBLISHER SERVICES ////////
    //////// PUBLISHER SERVICES ////////
    public void addPublisher(Publisher publisher, Connection connection)
    {
        try{

            publisherDao.add(publisher , connection);
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
    }
    public void deletePublisher(Publisher publisher, Connection connection)
    {
        try{
            System.out.println("What is the ID of the Publisher You Wish to DELETE");
            publisher.setPublisherId(scan.nextInt());
            scan.nextLine();
            getValidInput();
            boolean isValid = valid.validateAuthor(publisher.getPublisherId(),connection);
            if(!isValid)
            {
                System.out.println("Could not Validate this ID.");
            }
            else{
                System.out.println("Deleting publisher.....");
                publisherDao.delete(publisher,connection);
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }
    public void updatePublisher(Publisher publisher, Connection connection)
    {
        try{
            System.out.println("What is the ID of the Publisher You Wish to update");
            publisher.setPublisherId(scan.nextInt());
            scan.nextLine();
            getValidInput();
            boolean isValid = valid.validateAuthor(publisher.getPublisherId(),connection);
            if(!isValid)
            {
                System.out.println("Could not Validate this ID.");
            }
            else{
                System.out.println("What is the New Name of This Publisher");
                publisher.setPublisherName(scan.nextLine());
                System.out.println("what is the New Address of This Publisher");
                publisher.setPublisherAddress(scan.nextLine());
                System.out.println("What is the New Phone Number for this Publisher");
                publisher.setPublisherPhoneNumber(scan.nextLine());
                publisherDao.update(publisher,connection);
            }
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
    }
    //////// LIBRARY BRANCH SERVICES ////////
    //////// LIBRARY BRANCH SERVICES ////////
    //////// LIBRARY BRANCH SERVICES ////////
    public void addLibBranch (LibraryPOJO library, Connection connection)
    {
        try{
            System.out.println("Enter the Name of The Library Branch");
            library.setBranchName(scan.nextLine());
            System.out.println("Enter the Address of the Library Branch");
            library.setBranchAddress(scan.nextLine());
            library.setBranchId(genID.randomID());
            libDao.add(library, connection);
        }catch (SQLException e)
        {
            e.printStackTrace();
        }
    }
    public void deleteLibBranch (LibraryPOJO library, Connection connection)
    {
        try
        {
            System.out.println("What is the ID of the Branch You Wish to DELETE");
            library.setBranchId(scan.nextInt());
            scan.nextLine();
            getValidInput();
            boolean isValid = valid.validateLibBranch(library.getBranchId(),connection);
            if(!isValid)
            {
                System.out.println("Could not Validate this ID ");
            }
            else{
                System.out.println("Deleting library branch.....");
                libDao.delete(library,connection);
            }
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
    }
    public void updateLibBranch (LibraryPOJO library, Connection connection)
    {
        try
        {
            System.out.println("What is the ID of the Branch You Wish to update");
            library.setBranchId(scan.nextInt());
            scan.nextLine();
            getValidInput();
            boolean isValid = valid.validateLibBranch(library.getBranchId(),connection);
            if(!isValid)
            {
                System.out.println("Could not Validate this ID ");
            }
            else{

                System.out.println("What is the New Name of this Library Branch");
                library.setBranchName(scan.nextLine());
                System.out.println("what is the New Address of this Branch");
                library.setBranchAddress(scan.nextLine());
                libDao.update(library,connection);
            }
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
    }
    public void getValidInput()
    {
    	   while(!scan.hasNextInt()) {
           	
           	System.out.println("Not a Valid Number");
           	scan.next();
           }
    		System.out.print("Re-Enter a valid input-> ");
    }
}