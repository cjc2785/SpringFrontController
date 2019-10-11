package com.ss.lms.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ValidationService {
    public boolean validateLibBranch(int userInput, Connection connection) throws SQLException
    {
        PreparedStatement st = connection.prepareStatement("SELECT branchID FROM tbl_library_branch");
        ResultSet rs = st.executeQuery();
        return validationAl(userInput,"branchID", rs,connection);
    }

    public boolean validBookLoan( int userInput, Connection connection) throws SQLException {
        PreparedStatement st = connection.prepareStatement("SELECT bookId FROM tbl_book_loans");
        ResultSet rs = st.executeQuery();
        return validationAl(userInput,"bookId", rs,connection);
    }
    public boolean validCardLoan( int userInput, Connection connection) throws SQLException {
        PreparedStatement st = connection.prepareStatement("SELECT cardNo FROM tbl_book_loans");
        ResultSet rs = st.executeQuery();
        return validationAl(userInput,"cardNo", rs,connection);
    }

    public boolean validateAuthor(int userInput, Connection connection) throws SQLException
    {
        PreparedStatement st = connection.prepareStatement("SELECT authorId FROM tbl_author");
        ResultSet rs = st.executeQuery();
        return validationAl(userInput, "authorID", rs, connection);
    }


    public boolean validatePublisher(int userInput, Connection connection) throws SQLException
    {
        PreparedStatement st = connection.prepareStatement("SELECT  publisherId FROM tbl_publisher");
        ResultSet rs = st.executeQuery();
        return validationAl(userInput, "publisherId", rs, connection);
    }


    public boolean validateBorrower(int userInput, Connection connection) throws SQLException {
        PreparedStatement st = connection.prepareStatement("SELECT cardNo FROM tbl_borrower");

        ResultSet rs = st.executeQuery();
        return validationAl(userInput, "cardNo", rs, connection);
    }

    public boolean validateBook(int userInput, Connection connection) throws SQLException {
        PreparedStatement st = connection.prepareStatement("SELECT bookId FROM tbl_book");

        ResultSet rs = st.executeQuery();
        return validationAl(userInput, "bookID", rs,connection);
    }


    private boolean validationAl(int userInput, String itemID, ResultSet rs, Connection connection) throws SQLException {
        boolean check = false;
        String currRow = "";
        while (rs.next())
        {
            currRow = rs.getString(itemID);
            if (currRow.equals(String.valueOf(userInput)))
            {
                check = true;
            }
        }
        return check;
    }
}
