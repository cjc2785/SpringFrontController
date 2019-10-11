package com.ss.lms.dao;

import com.ss.lms.model.Author;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@Component
public class AdminAuthorDao implements AdminDao<Author, Connection > {

    @Override
    public void add(Author author, Connection connection) throws SQLException {
        PreparedStatement st = connection.prepareStatement("insert into tbl_author (authorId, authorName)" +
                "VALUES (?,?)");
        st.setString(1, String.valueOf(author.getAuthorId()));
        st.setString(2, author.getAuthorName());
        st.executeUpdate();
    }

    @Override
    public void delete(Author author, Connection connection)  throws SQLException {
        PreparedStatement st = connection.prepareStatement("DELETE FROM tbl_author WHERE authorId = ?");
        st.setString(1, String.valueOf(author.getAuthorId()));
        st.executeUpdate();
    }

    @Override
    public void update(Author author, Connection connection) throws SQLException {

        PreparedStatement st = connection.prepareStatement("UPDATE tbl_author SET authorName = ? " +
                "WHERE authorId = ?");
        st.setString(1,author.getAuthorName());
        st.setString(2,String.valueOf(author.getAuthorId()));
        st.executeUpdate();
    }
}
