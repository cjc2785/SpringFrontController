package com.ss.lms.model;

public class Borrower {
	
	private int borrowerCardNumber;
	private String borrowerName;
	private String borrowerAddress;
	private String borrowerPhoneNumber;
	
	public Borrower() {};
	
	public Borrower(int cardNumber, String name, String address, String phoneNumber) {
		borrowerCardNumber = cardNumber;
		borrowerName = name;
		borrowerAddress = address;
		borrowerPhoneNumber = phoneNumber;
		
	}

	public int getBorrowerCardNumber() {
		return borrowerCardNumber;
	}

	public void setBorrowerCardNumber(int borrowerCardNumber) {
		this.borrowerCardNumber = borrowerCardNumber;
	}

	public String getBorrowerName() {
		return borrowerName;
	}

	public void setBorrowerName(String borrowerName) {
		this.borrowerName = borrowerName;
	}

	public String getBorrowerAddress() {
		return borrowerAddress;
	}

	public void setBorrowerAddress(String borrowerAddress) {
		this.borrowerAddress = borrowerAddress;
	}

	public String getBorrowerPhoneNumber() {
		return borrowerPhoneNumber;
	}

	public void setBorrowerPhoneNumber(String borrowerPhoneNumber) {
		this.borrowerPhoneNumber = borrowerPhoneNumber;
	}
	
}
