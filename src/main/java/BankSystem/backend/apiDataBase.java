package BankSystem;

import java.sql.Connection; // Import the Connection class for managing the database connection
import java.sql.DriverManager; // Import the DriverManager class to establish the connection
import java.sql.SQLException; // Import SQLException for handling SQL errors

public class apiDataBase {

    public static Connection connect() {
        // Define the database URL (replace with your actual database path)
        String url = "jdbc:mysql://localhost:3306/bankdb"; // JDBC URL for MySQL database 'bankdb' on localhost
        String username = "root"; // Database username
        String password = ""; // Database password

        try {
            // Load the JDBC driver class for MySQL
            // Class.forName("com.mysql.cj.jdbc.Driver");
            // Attempt to establish a connection using the URL, username, and password
            Connection conn = DriverManager.getConnection(url, username, password);
            // Return the established connection object
            return conn;

        } catch (SQLException e) {
            // Handle SQL errors during connection
            System.out.println("Database connection failed: " + e.getMessage());
        }
        // Return null if connection was not successful
        return null;
    }
}