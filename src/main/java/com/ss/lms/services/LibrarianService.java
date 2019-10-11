package com.ss.lms.services;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.ss.lms.Main;
import com.ss.lms.dao.LibrarianDAO;
import com.ss.lms.model.LibraryBook;
import com.ss.lms.model.LibraryPOJO;

public class LibrarianService {
	private LibrarianDAO lib = new LibrarianDAO();
	
	public void libraryMain(Scanner scan) {
		try {
			Connection conn = lib.openConnection();
			while(true) {
				Main.ui.librarianMenu();
				//System.out.println("1) Enter Branch you manage");
			//	System.out.println("2) Quit to previous");
				if(validate(2,scan) == 1) {
					libraryTwo(scan, conn);
				}else {
					//close connection and return to main menu
					lib.closeConnection(conn);
					break;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void libraryTwo(Scanner scan, Connection conn) {
		while(true) {
			int options = 1;
			List<LibraryPOJO> branches = new ArrayList<>();
			try {
				
				//call to DAO for list of all branches
				branches = lib.viewBranches(conn);
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
			//display all branches as options
			for(LibraryPOJO branch : branches) {
				System.out.println(options + ") " + branch.getBranchName());
				options ++;
			}
			System.out.println(options + ") Quit to previous");
			int selection = validate(options, scan);
			if(selection == options) {
				//return to libraryMain
				break;
			}else {
				LibraryPOJO branch = new LibraryPOJO();
				try {
				
					//call to DAO for the branch info of selected branch as well as the number of copies it has of each book
					branch = lib.getBranchInfo(branches.get(selection-1).getBranchId(), conn);
				
				} catch (SQLException e) {
					e.printStackTrace();
				}
				libraryThree(branch, scan, conn);
			}
		}
	}
	
	public void libraryThree(LibraryPOJO branch, Scanner scan, Connection conn) {
option:	while(true) {
			System.out.println("1) Update the details of the library");
			System.out.println("2) Add copies of Book to the Branch");
			System.out.println("3) Quit to previous");
			switch(validate(3, scan)) {
			case 1:
				libraryEdit(branch, scan, conn);
				break;
			case 2:
				libraryAddBooks(branch, scan, conn);
				break;
			case 3:
				//return to libraryTwo
				break option;
			}
		}
	}
	
	public void libraryEdit(LibraryPOJO branch, Scanner scan, Connection conn) {
		
		System.out.println("You have chosen to update the Branch with Branch Id: " + branch.getBranchId() + " and Branch Name: " + branch.getBranchName());
		System.out.println("Enter 'quit' at any prompt to cancel operation.");
		System.out.println();
		scan.nextLine();
		//ask for name while checking for quit and N/A
		System.out.println("Please enter new branch name or enter N/A for no change:");
		String name = scan.nextLine();
		if(name.equalsIgnoreCase("quit")){return;}
		//ask for address while checking for quit and N/A
		System.out.println("Please enter new branch address or enter N/A for no change:");
		String address = scan.nextLine();
		if(address.equalsIgnoreCase("quit")){return;}
		if(!(name.equalsIgnoreCase("n/a"))){
			branch.setBranchName(name);
		}
		if(!(address.equalsIgnoreCase("n/a"))){
			branch.setBranchAddress(address);
		}
		try {
			//update the branch with the new info
			lib.updateBranchInfo(branch.getBranchId(), branch.getBranchName(), branch.getBranchAddress(), conn);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void libraryAddBooks(LibraryPOJO branch, Scanner scan, Connection conn) {
		int count = 1;
		//display all books
		for(LibraryBook book : branch.getBooks()) {
			System.out.println(count + ") " + book.getBookTitle());
			count ++;
		}
		count ++;
		System.out.println(count + ") Cancel");
		int selection = validate((count), scan);
		if(!(selection == count)) {
			selection --;
			int copies = branch.getBooks().get(selection).getNoOfCopies();
			System.out.println("Existing number of copies: " + copies);
			System.out.println("Enter number of new copies: ");
			//make sure they only input an int
			while(!scan.hasNextInt()) {
				scan.nextLine();
				System.out.println("Please enter a number!");
			}
			boolean none = (copies == 0);
			copies += scan.nextInt();
			//update the number of copies in the list
			branch.getBooks().get(selection).setNoOfCopies(copies);
		
			try {
				//update the number of copies of the selected book
				lib.addCopies(branch.getBooks().get(selection).getBookId(), branch.getBranchId(), branch.getBooks().get(selection).getNoOfCopies(), none, conn);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public int validate(int numberOfResponses, Scanner scan){
		//only allows for the response to be between 1 and the number of allowed responses
		while(true){
			while(!scan.hasNextInt()){
				scan.nextLine();
				scan.nextLine();
				System.out.println("Please only enter a number!");
			}
			int response = scan.nextInt();
			if(response > 0 && response <= numberOfResponses){
				return response;
			}
			System.out.print("Please only enter 1");
			for(int x = 2; x<numberOfResponses; x++){
			System.out.print(", " + x);
			}	
			System.out.println(" or " + numberOfResponses + "!");
		}
	}
}
