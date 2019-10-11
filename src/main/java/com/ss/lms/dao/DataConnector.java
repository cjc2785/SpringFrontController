package com.ss.lms.dao;

import com.ss.lms.secret.Url;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataConnector {

    public static Connection connection = null;
    private Url myUrl = new Url();

    public Connection getCurrConnection() throws SQLException {
        if(connection != null )
        {
            return connection;
        }
        else{
            System.out.println("Opening Connection...");
            return DriverManager.getConnection(myUrl.getUrl());
        }
    }


}
