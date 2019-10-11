package com.ss.lms.services;

import com.ss.lms.model.Borrower;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ListService {
    public void listBorrower( Connection connection) throws SQLException {
        PreparedStatement st = connection.prepareStatement("SELECT cardNo, name FROM tbl_borrower");
        ResultSet rs = st.executeQuery();
        while (rs.next())
        {
            System.out.println(rs.getString("cardNo") + " | " + rs.getString("name"));
        }
    }
    public void listBooks ( Connection connection) throws SQLException {
        PreparedStatement st = connection.prepareStatement("select bookId, title from tbl_book");
        ResultSet rs = st.executeQuery();
        while (rs.next())
        {
            System.out.println(rs.getString("bookId") + " | " + rs.getString("title"));
        }
    }
    public void listAuthors ( Connection connection) throws SQLException {
        PreparedStatement st = connection.prepareStatement("select * from tbl_author");
        ResultSet rs = st.executeQuery();
        while (rs.next())
        {
            System.out.println(rs.getString("authorId") + " | " + rs.getString("authorName"));
        }
    }

    public void listPublisher( Connection connection) throws SQLException {
        PreparedStatement st = connection.prepareStatement("select publisherId, publisherName from tbl_publisher");
        ResultSet rs = st.executeQuery();
        while (rs.next())
        {
            System.out.println(rs.getString("publisherId") + " | " + rs.getString("publisherName"));
        }
    }
    public void listLibraryBranch(Connection connection) throws SQLException
    {
        PreparedStatement st = connection.prepareStatement("SELECT * From tbl_library_branch");
        ResultSet rs = st.executeQuery();
        while (rs.next())
        {
            System.out.println(rs.getString(1) + " | " + rs.getString(2) + " | " + rs.getString(3));
        }
    }

    public void listBookLoans(Connection connection) throws SQLException
    {
        PreparedStatement st = connection.prepareStatement("select o.cardNo,o.name,i.bookId,i.dateOut,i.dueDate from tbl_book_loans i " +
                "left join tbl_borrower o on o.cardNo = i.cardNo;");
        ResultSet rs = st.executeQuery();
        while (rs.next())
        {
            System.out.println(rs.getInt(1) +" | " +rs.getString(2) + " | " + rs.getString(3) +" | " + rs.getString(4) + " | " +rs.getString(5));
        }
    }




}
