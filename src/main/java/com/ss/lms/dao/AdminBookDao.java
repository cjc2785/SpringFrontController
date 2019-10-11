package com.ss.lms.dao;

import com.ss.lms.model.Book;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@Component
public class AdminBookDao implements AdminDao<Book, Connection> {
    @Override
    public void add(Book book, Connection connection) throws SQLException {
        int authId = book.getBookAuthor().getAuthorId();
        int pubId = book.getBookPublisher().getPublisherId();

        PreparedStatement st = connection.prepareStatement("insert into tbl_book (bookId,title,authId,pubId) "+
                "VALUES (?,?,?,?)");
        st.setString(1, String.valueOf(book.getBookId()));
        st.setString(2,book.getBookTitle());
        st.setString(3,String.valueOf(authId));
        st.setString(4,String.valueOf(pubId));
        st.executeUpdate();
    }

    @Override
    public void delete(Book book, Connection connection) throws SQLException {

        PreparedStatement st = connection.prepareStatement("DELETE FROM tbl_book WHERE bookId = ?");
        st.setString(1,String.valueOf(book.getBookId()));
        st.executeUpdate();
    }

    @Override
    public void update(Book book, Connection connection) throws SQLException {
        int bookId = book.getBookId();
        PreparedStatement st = connection.prepareStatement("UPDATE tbl_book SET title = ? " +
                "WHERE bookId = ?");
        st.setString(1,book.getBookTitle());
        st.setString(2,String.valueOf(bookId));
        st.executeUpdate();
    }
}
