package com.ss.lms;

import com.ss.lms.model.*;
import com.ss.lms.secret.GenerateID;
import com.ss.lms.services.AdminServices;
import com.ss.lms.services.ListService;
import com.ss.lms.services.ValidationService;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class AdminMenu {
    private AdminView view = new AdminView();
    private AdminServices admin = new AdminServices();
    private GenerateID genId = new GenerateID();
    private Scanner scan = new Scanner(System.in);

    ListService list = new ListService();
    ValidationService valid = new ValidationService();


    public void runMainMenu(Connection connection) throws SQLException,InputMismatchException {
        view.printMainMenu();
        int choice = getInput();
        switch (choice)
        {
            case 0:
                connection.close();
                break;
            case 1:
                runBookMenu(connection);
                runMainMenu(connection);
                break;
            case 2:
                runAuthMenu(connection);
                runMainMenu(connection);
                break;
            case 3:
                runPublisherMenu(connection);
                runMainMenu(connection);
                break;
            case 4:
                runBorrowerMenu(connection);
                runMainMenu(connection);
                break;
            case 5:
                runLibBranchMenu(connection);
                runMainMenu(connection);
                break;
        }
    }
    public void runBookMenu(Connection connection) throws SQLException,InputMismatchException {
        Scanner scan = new Scanner(System.in);
        Book book = new Book();

        view.printBookMenu();
        int choice = getInput();
        switch (choice)
        {
            case 0:
                break;
            case 1:
                runBookSubMenu(connection);
                break;
            case 2:

                System.out.println("Enter the ID of the book you want to edit");
                getValidInput();
                book.setBookId(scan.nextInt());
                admin.updateBook(book,connection);
                runBookMenu(connection);
                scan.close();
                break;
            case 3:

                System.out.println(("Enter the ID of the book you want to delete"));
                getValidInput();
                book.setBookId(scan.nextInt());
                admin.deleteBook(book,connection);
                runBookMenu(connection);
                scan.close();
                break;
            case 4:
                list.listBooks(connection);
                System.out.println("-------------------------");
                System.out.println("press [Enter] to continue");
                scan.nextLine();
                runBookMenu(connection);
                scan.close();
                break;
        }
    }

    public void runBookSubMenu(Connection connection) throws SQLException
    {
        Book book = new Book();
        view.printBookSubMenu();
        int choice = getInput();
        switch (choice)
        {
            case 0:
                runBookMenu(connection);
            case 1:
                System.out.println("----------you must first Add Author/Pub----------");
                System.out.println("---------------- [Press Enter] -----------------");
                scan.nextLine();
                admin.addBook(book,connection);
                runBookMenu(connection);
                break;
            case 2:
                authorDialog();
                runBookSubMenu(connection);
            case 3:
                publisherDialog();
                runBookSubMenu(connection);
                break;
            case 4:
                admin.addBook(book,connection);
                break;
            case 5:
                list.listAuthors(connection);
                System.out.println("Press [ ENTER ] to continue");
                scan.nextLine();
                runBookSubMenu(connection);
                break;
            case 6:
                list.listPublisher(connection);
                System.out.println("Press [ ENTER ] to continue");
                scan.nextLine();
                runBookSubMenu(connection);
                break;
        }

    }

    public void runAuthMenu(Connection connection) throws SQLException {
        view.printAuthorMenu();
        Author author = new Author();
        int choice = getInput();
        switch (choice)
        {
            case 0:
                break;
            case 1:
                author = authorDialog();
                admin.addAuthor(author,connection);
                System.out.println("Author has been Created [ " + author.getAuthorName() + " ]" +
                        "with ID of [ " + author.getAuthorId() + " ]");
                scan.nextLine();
                break;
            case 2:
                admin.updateAuthor(author,connection);
                break;
            case 3:
                admin.deleteAuthor(author,connection);
                break;
            case 4:
                list.listAuthors(connection);
                System.out.println("-------------------PRESS ENTER TO CONTINUE--------------------");
                scan.nextLine();
                runAuthMenu(connection);
                break;
        }
    }
    public void runPublisherMenu(Connection connection) throws SQLException
    {
        Publisher publisher = new Publisher();
        view.printPublisherMenu();
        int choice = getInput();
        switch (choice)
        {
            case 0:
                break;
            case 1:
                publisher = publisherDialog();
                admin.addPublisher(publisher,connection);
                break;
            case 2:
                admin.updatePublisher(publisher,connection);
                break;
            case 3:
                admin.deletePublisher(publisher,connection);
                break;
            case 4:
                list.listPublisher(connection);
                break;
        }
    }
    public void runBorrowerMenu(Connection connection) throws SQLException {
        Borrower borrower = new Borrower();
        view.printBorrowerMenu();
        int choice = getInput();
        switch (choice)
        {
            case 0:
                break;
            case 1:
                borrower = borrowerDialog();
                admin.addBorrower(borrower,connection);
                break;
            case 2:
                admin.updateBorrower(borrower,connection);
                runBorrowerMenu(connection);
                break;
            case 3:
                admin.deleteBorrower(borrower, connection);
                runBorrowerMenu(connection);
                break;
            case 4:
                admin.setDueDate(connection);
                runBorrowerMenu(connection);
                break;
            case 5:
                list.listBorrower(connection);
                System.out.println("----------[Press enter to continue]-----------");
                scan.nextLine();
                runBorrowerMenu(connection);
                break;
            case 6:
                System.out.println("CARD NO| NAME | BOOK ID |   LOAN DATE  |  DATE DUE\n");
                list.listBookLoans(connection);
                System.out.println("----------[Press ENTER to continue]------------");
                scan.nextLine();
                runBorrowerMenu(connection);
                break;

        }
    }
    public void runLibBranchMenu(Connection connection) throws SQLException
    {
        LibraryPOJO libBranch = new LibraryPOJO();
        view.printLibBranchMenu();
        int choice = getInput();
        switch (choice)
        {
            case 0:
                break;
            case 1:
                admin.addLibBranch(libBranch,connection);
                break;
            case 2:
                admin.updateLibBranch(libBranch,connection);
                break;
            case 3:
                admin.deleteLibBranch(libBranch,connection);
                break;
            case 4:
                list.listLibraryBranch(connection);
                System.out.println("--------PRESS ENTER TO CONTINUE---------");
                scan.nextLine();
                runLibBranchMenu(connection);
                break;
        }
    }
    public Author authorDialog( )
    {
        Author author = new Author();
        System.out.println("enter the Name of the Author you want to add");
        author.setAuthorName(scan.nextLine());
        author.setAuthorId(genId.randomID());
        return author;
    }

    public Publisher publisherDialog()
    {
        Publisher publisher = new Publisher();
        System.out.println("Enter the Name of the Publisher you want to add");
        publisher.setPublisherName(scan.nextLine());
        System.out.println("enter the Address of this Publisher");
        publisher.setPublisherAddress(scan.nextLine());
        System.out.println("enter the Phone number of this Publisher");
        publisher.setPublisherPhoneNumber(scan.nextLine());
        publisher.setPublisherId(genId.randomID());
        return publisher;
    }
    public Borrower borrowerDialog( )
    {
        Borrower borrower = new Borrower();
        System.out.println("Enter the Name of the new User");
        borrower.setBorrowerName(scan.nextLine());
        System.out.println("Enter the Address of the Borrower");
        borrower.setBorrowerAddress(scan.nextLine());
        System.out.println("Enter the phone Number of this Borrower");
        borrower.setBorrowerPhoneNumber(scan.nextLine());
        borrower.setBorrowerCardNumber(genId.randomID());
        return borrower;
    }
    public void getValidInput()
    {
    	   while(!scan.hasNextInt()) {
           	
           	System.out.println("Not a Valid Number");
           	scan.next();
           }
    		System.out.print("Re-Enter a valid input-> ");
    }
    private int getInput()
    {
        Scanner kbInput = new Scanner(System.in);

        int choice = -1;
        while( choice < 0 || choice > 6)
        {
            try {

                System.out.println("\nEnter Your Selection Here: ");
                choice = Integer.parseInt(kbInput.nextLine());
            } catch (NumberFormatException e)
            {
                System.out.println("Not a valid selection. Please try again.");
            }
        }
        kbInput.close();
        return choice;
    }
}
