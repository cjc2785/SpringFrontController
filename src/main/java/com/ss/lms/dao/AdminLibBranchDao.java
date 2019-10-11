package com.ss.lms.dao;

import com.ss.lms.model.LibraryPOJO;
import com.ss.lms.secret.GenerateID;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AdminLibBranchDao implements AdminDao<LibraryPOJO, Connection> {
    @Override
    public void add(LibraryPOJO library, Connection connection) throws SQLException {

        PreparedStatement st = connection.prepareStatement("insert into tbl_library_branch (branchId, branchName,branchAddress)" +
                " VALUES (?,?,?);");
        st.setString(1, String.valueOf(library.getBranchId()));
        st.setString(2, library.getBranchName());
        st.setString(3, library.getBranchAddress());
        st.executeUpdate();
    }

    @Override
    public void delete(LibraryPOJO library, Connection connection) throws SQLException {
        PreparedStatement st = connection.prepareStatement("DELETE FROM tbl_library_branch WHERE branchID = ?");
        st.setString(1,String.valueOf(library.getBranchId()));
        st.executeUpdate();
    }

    @Override
    public void update(LibraryPOJO library, Connection connection) throws SQLException {
        PreparedStatement st = connection.prepareStatement("UPDATE tbl_library_branch SET branchName = ?" +
                ", branchAddress = ? WHERE branchId = ?");
        st.setString(1,library.getBranchName());
        st.setString(2,library.getBranchAddress());
        st.setString(3, String.valueOf(library.getBranchId()));
        st.executeUpdate();
    }
}
