package com.ss.lms.dao;

import com.ss.lms.model.Borrower;
import com.ss.lms.model.Publisher;
import com.ss.lms.secret.GenerateID;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class AdminPublisherDao implements AdminDao<Publisher, Connection> {

    @Override
    public void add(Publisher publisher, Connection connection) throws SQLException {

        PreparedStatement st = connection.prepareStatement("insert into tbl_publisher (publisherId, publisherName, " +
                "publisherAddress, publisherPhone)" +
                "VALUES (?,?,?,?)");
        st.setString(1, String.valueOf(publisher.getPublisherId()));
        st.setString(2, publisher.getPublisherName());
        st.setString(3, publisher.getPublisherAddress());
        st.setString(4, publisher.getPublisherPhoneNumber());
        st.executeUpdate();
    }

    @Override
    public void delete(Publisher publisher, Connection connection) throws SQLException {
        PreparedStatement st = connection.prepareStatement("DELETE FROM tbl_publisher WHERE publisherId = ? ");
        st.setString(1, String.valueOf(publisher.getPublisherId()));
        st.executeUpdate();
    }

    @Override
    public void update(Publisher publisher, Connection connection) throws SQLException {
        int pubId = publisher.getPublisherId();

        PreparedStatement st = connection.prepareStatement("UPDATE tbl_publisher SET publisherName = ?," +
                "publisherAddress = ?," +
                "publisherPhone = ? " +
                "WHERE publisherId = ?");
        st.setString(1, publisher.getPublisherName());
        st.setString(2, publisher.getPublisherAddress());
        st.setString(3, publisher.getPublisherPhoneNumber());
        st.setString(4, String.valueOf(pubId));
        st.executeUpdate();

    }
}
