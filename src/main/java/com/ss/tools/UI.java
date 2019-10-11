package com.ss.tools;

public class UI {

	public void mainMenuTop() {
		System.out.println("\n");
		System.out.println(ConsoleColors.GREEN_BOLD
				+ " ________________________________________________________________________________");
		System.out.println("|________________________________________________________________________________|");
		System.out.println("| Welcome to the GCIT Libary Management System. Which category of a user are you |");
		System.out.println("|________________________________________________________________________________|");
	}

	public void mainMenuBottom() {
		System.out.println("\n");
		System.out.println(ConsoleColors.GREEN_BOLD
				+ " ________________________________________________________________________________");
	}

	public void mainMenu() {

		System.out.println("|____________________|  __         __    __     ______                           | ");
		System.out.println("|[ 1) Librarian     ]| /\\ \\       /\\ \"-./  \\   /\\  ___\\                          |");
		System.out.println(
				"|                    | \\ \\ \\____  \\ \\ \\-./\\ \\  \\ \\___  \\                         |");
		System.out.println(
				"|[ 2) Administrator ]|  \\ \\_____\\  \\ \\_\\ \\ \\_\\  \\/\\_____\\                        |");
		System.out.println("|                    |   \\/_____/   \\/_/  \\/_/   \\/_____/                        |");
		System.out.println("|[ 3) Borrower      ]|                                                           |");
		System.out.println("|                    |                                                           |");
		System.out.println("|[ 0) Quit          ]|                                 Version 0.0.1e (C) 1985   |");
		System.out.println("|____________________|___________________________________________________________|");
		System.out.print("| User Select->"+ ConsoleColors.RESET);
	}

	public void mainMenuQuit() {
		System.out.println(ConsoleColors.GREEN_BOLD+"|Thank you have a day|");
		System.out.println("|Shutting down....   |");
		System.out.println("|____________________|");
	}

	public void menuBoxBottom() {
		System.out.println(ConsoleColors.GREEN_BOLD+"|____________________|___________________________________________________________|"+ConsoleColors.RESET);
	}

	public void menuBoxBottomOffset() {
		System.out.println(ConsoleColors.GREEN_BOLD+"                     |____________________|                                       "+ConsoleColors.RESET);
	}

	
	/*
	 * 
	 * Libararian UI block
	 * 
	 * 
	 */
	public void librarianMenu() {
		System.out.print(ConsoleColors.CYAN_BOLD);
		System.out.println("|                                                                                |");
		System.out.println("|________________________________________________________________________________| ");
		System.out.println("| __      __  ______  ______  ______  ______  __  ______  __   __                |");
		System.out.println("|/\\ \\    /\\ \\/\\  == \\/\\  == \\/\\  __ \\/\\  == \\/\\ \\/\\  __ \\/\\ \"-.\\ \\               |");
		System.out.println("|\\ \\ \\___\\ \\ \\ \\  __<\\ \\  __<\\ \\  __ \\ \\  __<\\ \\ \\ \\  __ \\ \\ \\-.  \\              |");
		System.out.println("| \\ \\_____\\ \\_\\ \\_____\\ \\_\\ \\_\\ \\_\\ \\_\\ \\_\\ \\_\\ \\_\\ \\_\\ \\_\\ \\_\\\\\"\\_\\             |");
		System.out.println("|  \\/_____/\\/_/\\/_____/\\/_/ /_/\\/_/\\/_/\\/_/ /_/\\/_/\\/_/\\/_/\\/_/ \\/_/             |");
		System.out.println("|                                                                                |");
		System.out.println("|                                                                                |");
		System.out.println("|                                                                                |");
		System.out.println("| "+ConsoleColors.RED_BACKGROUND+"[1) Enter Branch you Manage ]"+ConsoleColors.RESET+ConsoleColors.CYAN_BOLD+"                                                  |");
		System.out.println("| "+ConsoleColors.RED_BACKGROUND+"[2) Quit to Previous  ]"+ConsoleColors.RESET+ConsoleColors.CYAN_BOLD+"                                Version 0.0.1e (C) 1985 |");
		System.out.println("|________________________________________________________________________________|");
		System.out.print("|Input->");
	}
	
	
	public void librarianBranchMenuBottom() {
		System.out.println("\n                                          |______________________________________| "+ConsoleColors.RESET);
	}
    //-----------------------------------------------
	
	
	public void borrowerLogIn() {
		System.out.print(ConsoleColors.PURPLE_BOLD);
		System.out.println(" ________________________________________________________________________________");
		System.out.print("|Login with Card #:");
	}
	public void borrowerMenu() {
		System.out.print(ConsoleColors.PURPLE_BOLD);
		System.out.println(" ________________________________________________________________________________");
		System.out.println("|________________________________________________________________________________| ");
		System.out.println("| ______   ______   ______   ______   ______   __     __   ______   ______       |");
		System.out.println("|/\\  == \\ /\\  __ \\ /\\  == \\ /\\  == \\ /\\  __ \\ /\\ \\  _ \\ \\ /\\  ___\\ /\\  == \\      |");
		System.out.println("|\\ \\  __< \\ \\ \\/\\ \\\\ \\  __< \\ \\  __< \\ \\ \\/\\ \\\\ \\ \\/ \".\\ \\\\ \\  __\\ \\ \\  __<      |");
		System.out.println("| \\ \\_____\\\\ \\_____\\\\ \\_\\ \\_\\\\ \\_\\ \\_\\\\ \\_____\\\\ \\__/\".~\\_\\\\ \\_____\\\\ \\_\\ \\_\\    |");
		System.out.println("|  \\/_____/ \\/_____/ \\/_/ /_/ \\/_/ /_/ \\/_____/ \\/_/   \\/_/ \\/_____/ \\/_/ /_/    |");
		System.out.println("|                                                                                |");
		System.out.println("|                                                                                |");
		System.out.print(ConsoleColors.PURPLE_BOLD);
		System.out.println("|                                                                                |");
		System.out.println("| "+ConsoleColors.BLUE_BACKGROUND_BRIGHT+"[1) Check out Book]"+ConsoleColors.RESET+ConsoleColors.PURPLE_BOLD+"                                                            |");
		System.out.println("| "+ConsoleColors.BLUE_BACKGROUND_BRIGHT+"[2) Return a Book ]"+ConsoleColors.RESET+ConsoleColors.PURPLE_BOLD+"                                                            |");
		System.out.println("| "+ConsoleColors.BLUE_BACKGROUND_BRIGHT+"[3) Quit to Main  ]"+ConsoleColors.RESET+ConsoleColors.PURPLE_BOLD+"                                    Version 0.0.1e (C) 1985 |");
		System.out.println("|________________________________________________________________________________|");
		System.out.print("|Input->");
	}
	public void borrowerMenuBottom() {
		System.out.println("|________________________________________________________________________________|");
	}
	
	public void borrowerMenuOne() {
		System.out.println("|                                                                                |");
		System.out.println("|                                                                                |");
	}
	public void borrowerMenuOneBottome() {
		System.out.println("|________________________________________________________________________________|");
		
	}
	
	
	public void adminMainMenu() {
		System.out.print(ConsoleColors.GREEN_BOLD);
		System.out.println(" ________________________________________________________________________________");
		System.out.println("|________________________________________________________________________________| ");
		System.out.println("| ______   _____    __    __   __   __   __                                      |");
		System.out.println("|/\\  __ \\ /\\  __-. /\\ \"-./  \\ /\\ \\ /\\ \"-.\\ \\                                     |");
		System.out.println("|\\ \\  __ \\\\ \\ \\/\\ \\\\ \\ \\-./\\ \\\\ \\ \\\\ \\ \\-.  \\                                    |");
		System.out.println("| \\ \\_\\ \\_\\\\ \\____- \\ \\_\\ \\ \\_\\\\ \\_\\\\ \\_\\\\\"\\_\\                                   |");
		System.out.println("|  \\/_/\\/_/ \\/____/  \\/_/  \\/_/ \\/_/ \\/_/ \\/_/                                   |");
		System.out.println("|                                                                                |");
		System.out.println("|                                                                                |");
		System.out.println("|                                                                                |");
		System.out.println("| "+ConsoleColors.BLUE_BACKGROUND_BRIGHT+"[1) Go to Book Menu ]"+ConsoleColors.RESET+ConsoleColors.GREEN_BOLD+"                                                          |");
		System.out.println("| "+ConsoleColors.BLUE_BACKGROUND_BRIGHT+"[2) Go to Author Menu ]"+ConsoleColors.RESET+ConsoleColors.GREEN_BOLD+"                                                        |");
		System.out.println("| "+ConsoleColors.BLUE_BACKGROUND_BRIGHT+"[3) Go to Publisher Menu ]"+ConsoleColors.RESET+ConsoleColors.GREEN_BOLD+"                                                     |");
		System.out.println("| "+ConsoleColors.BLUE_BACKGROUND_BRIGHT+"[4) Go to Borrower Menu ]"+ConsoleColors.RESET+ConsoleColors.GREEN_BOLD+"                                                      |");
		System.out.println("| "+ConsoleColors.BLUE_BACKGROUND_BRIGHT+"[5) Go to Library Branch Menu ]"+ConsoleColors.RESET+ConsoleColors.GREEN_BOLD+"                                                |");
		System.out.println("| "+ConsoleColors.BLUE_BACKGROUND_BRIGHT+"[0) Quit to Main Menu ]"+ConsoleColors.RESET+ConsoleColors.GREEN_BOLD+"                                Version 0.0.1e (C) 1985 |");
		System.out.println("|________________________________________________________________________________|");
		System.out.print("|Input->");
	}
	
	
	
}
