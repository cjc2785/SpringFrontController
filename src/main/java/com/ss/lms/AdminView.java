package com.ss.lms;

import com.ss.lms.dao.DataConnector;
import com.ss.lms.services.AdminServices;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

public class AdminView {
    private DataConnector dataConnector = new DataConnector();

    public void dueDateList() throws SQLException {
        Connection connection = dataConnector.getCurrConnection();
        System.out.println("Listing Users");
        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();
        AdminServices myAdmin = new AdminServices();

        myAdmin.listBorrower(connection);
    }
    public void printMainMenu()
    {
    	Main.ui.adminMainMenu();
        //System.out.println("---------Welcome to Admin menu--------");
        //System.out.println("1) Go to Book Menu");
        //System.out.println("2) Go to Author Menu");
        //System.out.println("3) Go to Publisher Menu");
        //System.out.println("4) Go to Borrower Menu");
       // System.out.println("5) Go to Library Branch Menu");
        //System.out.println("\n0) exit");

    }
    public void printBookMenu()
    {
        System.out.println("--------Welcome to Book Menu--------");
        System.out.println("1) Add Book");
        System.out.println("2) Edit Book");
        System.out.println("3) Delete Book");
        System.out.println("4) List Books");
        System.out.println("\n0) Go Back to Main Menu");
    }
    public void printBookSubMenu()
    {
        System.out.println("--------Book Sub Menu---------");
        System.out.println("1) This is a Completely New Book [Author / Publisher Not listed");
        System.out.println("2) This Book's author is Not listed");
        System.out.println("3) This Book's Publisher is not listed");
        System.out.println("4) This Book has an existing Author and Publisher");
        System.out.println("5) List Authors");
        System.out.println("6) List Publisher");
        System.out.println("\n0) Return to Book Menu");
    }
    public void printAuthorMenu()
    {
        System.out.println("--------Welcome to Author Menu--------");
        System.out.println("1) Add Author");
        System.out.println("2) Edit Author");
        System.out.println("3) Delete Author");;
        System.out.println("4) List Authors");;
        System.out.println("\n0) Go back to Main Menu");
    }
    public void printPublisherMenu()
    {
        System.out.println("--------Welcome to Publisher Menu--------");
        System.out.println("1) Add Publisher");
        System.out.println("2) Edit Publisher");
        System.out.println("3) Delete Publisher");
        System.out.println("4) List Publishers");
        System.out.println("\n0) Go back to Main Menu");
    }
    public void printLibBranchMenu()
    {
        System.out.println("--------Welcome to Library Menu--------");
        System.out.println("1) Add Library Branch");
        System.out.println("2) Edit Library Branch");
        System.out.println("3) Delete Library Branch");
        System.out.println("4) List Library Branches");
        System.out.println("\n0) Go back to Main Menu");
    }
    public void printBorrowerMenu()
    {
        System.out.println("--------Welcome to Borrower Menu");
        System.out.println("1) Add Borrower");
        System.out.println("2) Edit Borrower");
        System.out.println("3) Delete Borrower");
        System.out.println("4) Change Book Due Date");
        System.out.println("5) List All Borrowers");
        System.out.println("6) List Borrowers With Books");
        System.out.println("\n0) Go Back to Main Menu");
    }

}
