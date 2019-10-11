package com.ss.lms.dao;

import com.ss.lms.model.Book;
import com.ss.lms.model.Borrower;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AdminDueDate {


    public void dueDate( Borrower borrower, Book book,Connection connection, String date) throws SQLException {
        PreparedStatement st = connection.prepareStatement("UPDATE tbl_book_loans set dueDate = ? WHERE bookID = ? AND cardNo = ?");
        st.setString(1,date);
        st.setString(2, String.valueOf(book.getBookId()));
        st.setString(3, String.valueOf(borrower.getBorrowerCardNumber()));
        st.executeUpdate();
    }
}
