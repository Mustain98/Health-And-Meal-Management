package Database;

import Food.FoodItem;
import User.User;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FoodDatabase {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/Food";
    private static final String USER = "root";
    private static final String PASSWORD = "Delta-Charlie-1998";

    // Static block to load the JDBC driver
    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Failed to load MySQL JDBC driver", e);
        }
    }

    public List<FoodItem> getFoodsByNutritionalValue(User user) {
        List<FoodItem> foodItems = new ArrayList<>();
        Connection connection = null;

        String query = "SELECT name, calories, protein, fat, carbs, sugar, fiber, sodium, potassium, iron, zinc, vitamin_c, vitamin_a " +
                "FROM proteins UNION ALL " +
                "SELECT name, calories, protein, fat, carbs, sugar, fiber, sodium, potassium, iron, zinc, vitamin_c, vitamin_a FROM carbs UNION ALL " +
                "SELECT name, calories, protein, fat, carbs, sugar, fiber, sodium, potassium, iron, zinc, vitamin_c, vitamin_a FROM fats UNION ALL " +
                "SELECT name, calories, protein, fat, carbs, sugar, fiber, sodium, potassium, iron, zinc, vitamin_c, vitamin_a FROM vegetables UNION ALL " +
                "SELECT name, calories, protein, fat, carbs, sugar, fiber, sodium, potassium, iron, zinc, vitamin_c, vitamin_a FROM fruits UNION ALL " +
                "SELECT name, calories, protein, fat, carbs, sugar, fiber, sodium, potassium, iron, zinc, vitamin_c, vitamin_a FROM nuts";

        try {
            connection = DriverManager.getConnection(DB_URL, USER, PASSWORD);
            try (PreparedStatement stmt = connection.prepareStatement(query);
                 ResultSet rs = stmt.executeQuery()) {

                while (rs.next()) {
                    FoodItem food = new FoodItem(
                            rs.getString("name"),
                            rs.getDouble("calories"),
                            rs.getDouble("protein"),
                            rs.getDouble("fat"),
                            rs.getDouble("carbs"),
                            rs.getDouble("sugar"),
                            rs.getDouble("fiber"),
                            rs.getDouble("sodium"),
                            rs.getDouble("potassium"),
                            rs.getDouble("iron"),
                            rs.getDouble("zinc"),
                            rs.getDouble("vitamin_c"),
                            rs.getDouble("vitamin_a")
                    );

                    if (food.matchesUserNeeds(user)) {
                        foodItems.add(food);
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Database error", e);
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    System.err.println("Failed to close database connection: " + e.getMessage());
                }
            }
        }

        return foodItems;
    }
}