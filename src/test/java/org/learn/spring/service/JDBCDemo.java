/**
 * Project Name:timesheet
 * File Name:JdbcDemo.java
 * Package Name:org.learn.spring.service
 * Date:2014-1-12ÏÂÎç11:21:04
 * Copyright (c) 2014, https://github.com/seiyaa All Rights Reserved.
 *
 */
package org.learn.spring.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCDemo
{
    /**
     * @param args
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public static void main(String[] args) throws ClassNotFoundException, SQLException
    {
        String connUrl = "jdbc:mysql://localhost:3306/timesheet";
        String username = "root";
        String password = "111111";

        Class.forName("com.mysql.jdbc.Driver");
        Connection conn = DriverManager.getConnection(connUrl, username, password);
        Statement st = conn.createStatement();

        String query = "select * from employee";
        ResultSet rs = st.executeQuery(query);

        while (rs.next())
        {
            System.out.println("department - " + rs.getString(1) + " name -" + rs.getString(2));
        }
    }
}