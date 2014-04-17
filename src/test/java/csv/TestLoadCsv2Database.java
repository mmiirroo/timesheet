/*
 * copyright (C) 2006-2014 the original author or authors.

 * date: 2014-4-12
 * description: 
 */

package csv;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.junit.Test;

public class TestLoadCsv2Database {

    private static String JDBC_CONNECTION_URL = "jdbc:mysql://localhost:3306/timesheet";
    private static String USER = "root";
    private static String PASSWORD = "111111";

    @Test
    public void testLoadCsv() {
        CSVLoader loader = new CSVLoader(getCon());
        String csvLoadFile = TestLoadCsv2Database.class.getResource("employee.csv").getFile();

        try {
            loader.loadCSV(csvLoadFile, "employee", true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static Connection getCon() {
        Connection connection = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(JDBC_CONNECTION_URL, USER, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return connection;
    }
}
