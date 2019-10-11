package com.ss.lms;

import com.ss.lms.dao.DataConnector;


import com.ss.lms.services.LibrarianService;
import com.ss.tools.*;

import java.sql.SQLException;
import java.util.Scanner;


public class Main {

	public static UI ui = new UI();
	static LibrarianService libraryService = new LibrarianService();
	static AdminMenu adminMenu = new AdminMenu();
	static DataConnector connection = new DataConnector();
	public static Scanner userInput = new Scanner(System.in);
	
	public static void main(String[] args) throws SQLException {

		char input = ' ';

		ui.mainMenuTop();

		while (true) {
			
			ui.mainMenu();

			input = userInput.next().charAt(0);

			ui.menuBoxBottom();

			if (input == '1') {
				java.awt.Toolkit.getDefaultToolkit().beep();
				libraryService.libraryMain(userInput);


			} else if (input == '2') {
				java.awt.Toolkit.getDefaultToolkit().beep();
				adminMenu.runMainMenu(connection.getCurrConnection());
			} else if (input == '3') {
				java.awt.Toolkit.getDefaultToolkit().beep();  

			} else if (input == '0') {
				break;
			} else {
				System.out.println("Not a choice!");
			}

			ui.mainMenuBottom();

		}
		ui.mainMenuQuit();
		userInput.close();
	}
}
