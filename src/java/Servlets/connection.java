package Servlets;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class connection {

    // Using ThreadLocal to ensure each thread gets a separate connection
    private static final ThreadLocal<Connection> threadLocalConnection = new ThreadLocal<>();

    // Private constructor to prevent instantiation
    private connection() {}

    // Method to get the connection instance
    public static Connection getConnection() {
        System.out.println("connecting");
        // Check if there's already a connection in the current thread's local storage
        Connection conn = threadLocalConnection.get();
        if (conn == null) {
            try {
                System.out.println("still connecting");
                // Load the MySQL JDBC driver
                Class.forName("com.mysql.cj.jdbc.Driver");

                // Establish the connection
                conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/RekrekDB", "root", "Te16@Ha21");
                threadLocalConnection.set(conn); // Store the connection for the current thread

                System.out.println("Database connection established.");
            } catch (ClassNotFoundException e) {
                System.err.println("MySQL JDBC Driver not found. Please add the driver to your classpath.");
            } catch (SQLException e) {
                System.err.println("Error connecting to the database.");
            }
        }
        return conn;
    }

    // Method to close the connection
    public static void closeConnection() {
        Connection conn = threadLocalConnection.get();
        if (conn != null) {
            try {
                conn.close();
                threadLocalConnection.remove(); // Remove the connection for the current thread
                System.out.println("Database connection closed.");
            } catch (SQLException e) {
                System.err.println("Error closing the database connection.");
            }
        }
    }
}
