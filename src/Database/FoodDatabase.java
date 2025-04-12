package Database;

import Food.FoodItem;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FoodDatabase {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/Food";
    private static final String USER = "root";
    private static final String PASSWORD = "Delta-Charlie-1998";
    private static FoodDatabase instance = new FoodDatabase();
    private Connection connection;

    private FoodDatabase() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Failed to load MySQL JDBC driver", e);
        }
    }
    public static synchronized FoodDatabase getInstance() {
        if (instance == null) {
            instance = new FoodDatabase();
        }
        return instance;
    }
    public Connection getConnection() {
        try {
            if (connection == null || connection.isClosed()) {
                connection = DriverManager.getConnection(DB_URL, USER, PASSWORD);
            }
            return connection;
        } catch (SQLException e) {
            throw new RuntimeException("Failed to get database connection", e);
        }
    }


}