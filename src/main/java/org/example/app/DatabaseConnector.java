package org.example.app;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnector {
    private static final String URL = "jdbc:mysql://127.0.0.1:3306/company";
    private static final String USER = "root";
    private static final String PASSWORD = "root_password";

    public Connection connect() throws SQLException {
        try {
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            System.out.println("Connection failed: " + e.getMessage());
            throw e;  // пробрасываем исключение дальше для обработки
        }
    }
}