package com.ss.lms.dao;

import org.springframework.stereotype.Component;

import java.sql.*;

@Component
public interface AdminDao<T, C> {
    public void add(T type, C connection) throws SQLException;
    public void delete(T type,C connection) throws SQLException;
    public void update(T type, C connection) throws SQLException;
}


