package org.example.database;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class database {

    public static class DatabaseConnection {
        private static final String URL = "jdbc:postgresql://localhost:5432/spsco";
        private static final String USER = "postgres";
        private static final String PASSWORD = "arshiapsq";

        public static Connection connect() {
            try {
                Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
                System.out.println("اتصال موفق!");
                return conn;
            } catch (SQLException e) {
                System.err.println("خطا در اتصال: " + e.getMessage());
                return null;
            }
        }

    }

}