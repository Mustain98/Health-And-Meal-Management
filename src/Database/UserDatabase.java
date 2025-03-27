package Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class UserDatabase {
    private static UserDatabase instance;
    private Connection connection;

    // Database configuration (move these to config file in production)
    private static final String DB_URL = "jdbc:mysql://localhost:3306/meal_planner";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "Delta-Charlie-1998";

    // Private constructor to prevent instantiation
    private UserDatabase() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            this.connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException("Failed to initialize database connection", e);
        }
    }

    public static synchronized UserDatabase getInstance() {
        if (instance == null) {
            instance = new UserDatabase();
        }
        return instance;
    }

    public Connection getConnection() {
        try {
            if (connection == null || connection.isClosed()) {
                // Recreate connection if closed
                connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            }
            return connection;
        } catch (SQLException e) {
            throw new RuntimeException("Failed to get database connection", e);
        }
    }

    public void closeConnection() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        } catch (SQLException e) {
            System.err.println("Error closing database connection: " + e.getMessage());
        }
    }
}