package com.ss.lms.dao;

import java.sql.*;

public interface AdminDao<T, C> {
    public void add(T type, C connection) throws SQLException;
    public void delete(T type,C connection) throws SQLException;
    public void update(T type, C connection) throws SQLException;
}


