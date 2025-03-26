package Database;
import User.*;
import Food.FoodItem;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FoodDatabase {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/Food";
    private static final String USER = "root";
    private static final String PASSWORD = "Delta-Charlie-1998";

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Failed to load MySQL JDBC driver", e);
        }
    }

    public List<FoodItem> getFoodsByNutritionalValue(User user) throws SQLException {
        List<FoodItem> foodItems = new ArrayList<>();
        String query = "SELECT name, calories, protein, fat, carbs, sugar, fiber, " +
                "sodium, potassium, iron, zinc, vitamin_c, vitamin_a " +
                "FROM proteins UNION ALL " +
                "SELECT name, calories, protein, fat, carbs, sugar, fiber, " +
                "sodium, potassium, iron, zinc, vitamin_c, vitamin_a FROM carbs UNION ALL " +
                "SELECT name, calories, protein, fat, carbs, sugar, fiber, " +
                "sodium, potassium, iron, zinc, vitamin_c, vitamin_a FROM fats UNION ALL " +
                "SELECT name, calories, protein, fat, carbs, sugar, fiber, " +
                "sodium, potassium, iron, zinc, vitamin_c, vitamin_a FROM vegetables UNION ALL " +
                "SELECT name, calories, protein, fat, carbs, sugar, fiber, " +
                "sodium, potassium, iron, zinc, vitamin_c, vitamin_a FROM fruits UNION ALL " +
                "SELECT name, calories, protein, fat, carbs, sugar, fiber, " +
                "sodium, potassium, iron, zinc, vitamin_c, vitamin_a FROM nuts";

        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(query);
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
        return foodItems;
    }

    public List<FoodItem> searchFoods(String query) throws SQLException {
        List<FoodItem> results = new ArrayList<>();
        String sql = "SELECT * FROM (" +
                "SELECT name, calories, protein, fat, carbs FROM proteins UNION ALL " +
                "SELECT name, calories, protein, fat, carbs FROM carbs UNION ALL " +
                "SELECT name, calories, protein, fat, carbs FROM fats UNION ALL " +
                "SELECT name, calories, protein, fat, carbs FROM vegetables UNION ALL " +
                "SELECT name, calories, protein, fat, carbs FROM fruits UNION ALL " +
                "SELECT name, calories, protein, fat, carbs FROM nuts" +
                ") AS all_foods WHERE LOWER(name) LIKE LOWER(?) LIMIT 5";

        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, "%" + query + "%");
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                results.add(new FoodItem(
                        rs.getString("name"),
                        rs.getDouble("calories"),
                        rs.getDouble("protein"),
                        rs.getDouble("fat"),
                        rs.getDouble("carbs")
                ));
            }
        }
        return results;
    }
}