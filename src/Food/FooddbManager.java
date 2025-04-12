package Food;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import Database.FoodDatabase;

public class FooddbManager {
    private final Connection con;
    public FooddbManager() {
        this.con= FoodDatabase.getInstance().getConnection();
    }

    public List<FoodItem> getFoodsByCategory(String category, double calorieLimit, double minProtein, double maxCarbs, double maxFat) throws SQLException {
        String query = String.format(
                "SELECT name, calories, protein, fat, carbs FROM %s " +
                        "WHERE calories <= ? AND protein <= ? AND carbs <= ? AND fat <= ?",
                category.toLowerCase());
            try( PreparedStatement stmt = con.prepareStatement(query)) {

            stmt.setDouble(1, calorieLimit);
            stmt.setDouble(2, minProtein);
            stmt.setDouble(3, maxCarbs);
            stmt.setDouble(4, maxFat);

            return getResult(stmt);
        }
    }
    private List<FoodItem> getResult(PreparedStatement stmt) throws SQLException {
        List<FoodItem> results = new ArrayList<>();
        try (ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                results.add(new FoodItem(
                                rs.getString("name"),
                                rs.getDouble("calories"),
                                rs.getDouble("protein"),
                                rs.getDouble("fat"),
                                rs.getDouble("carbs")
                        )
                );
            }
        }
        return results;
    }
    public List<FoodItem> searchFoods(String food) throws SQLException {
        String query = """
            SELECT * FROM (
                SELECT name, calories, protein, fat, carbs FROM proteins
                UNION ALL 
                SELECT name, calories, protein, fat, carbs FROM carbs
                UNION ALL 
                SELECT name, calories, protein, fat, carbs FROM fats
                UNION ALL 
                SELECT name, calories, protein, fat, carbs FROM vegetables
                UNION ALL 
                SELECT name, calories, protein, fat, carbs FROM fruits
                UNION ALL 
                SELECT name, calories, protein, fat, carbs FROM nuts
            ) AS all_foods WHERE LOWER(name) LIKE LOWER(?)
            """;
             try(PreparedStatement stmt = con.prepareStatement(query)) {

            stmt.setString(1, "%" + food + "%");
            return getResult(stmt);
        }
    }
    public  FoodItem getFoodByName(String name) throws SQLException {
        String query = """
            SELECT * FROM (
                SELECT name, calories, protein, fat, carbs FROM proteins
                UNION ALL 
                SELECT name, calories, protein, fat, carbs FROM carbs
                UNION ALL 
                SELECT name, calories, protein, fat, carbs FROM fats
                UNION ALL 
                SELECT name, calories, protein, fat, carbs FROM vegetables
                UNION ALL 
                SELECT name, calories, protein, fat, carbs FROM fruits
                UNION ALL 
                SELECT name, calories, protein, fat, carbs FROM nuts
            ) AS all_foods WHERE LOWER(name) = LOWER(?)
            """;

            try( PreparedStatement stmt = con.prepareStatement(query)) {

            stmt.setString(1, name);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new FoodItem(
                            rs.getString("name"),
                            rs.getDouble("calories"),
                            rs.getDouble("protein"),
                            rs.getDouble("fat"),
                            rs.getDouble("carbs")
                    );
                }
            }
        }
        return null;
    }
}
