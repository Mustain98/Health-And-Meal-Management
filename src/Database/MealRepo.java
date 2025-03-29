package Database;

import MealPrep.*;
import Food.FoodItem;
import java.sql.*;
import java.util.HashMap;
import java.util.Map;

public class MealRepo {
    private final Connection connection;

    public MealRepo() throws SQLException {
        this.connection = UserDatabase.getInstance().getConnection();
    }

    public int addMeal(int userId, String dayOfWeek, String mealType, Meal meal) throws SQLException {
        if (mealExists(userId, dayOfWeek, mealType)) {
            int existingId = getMealIdForDay(userId, dayOfWeek, mealType);
            meal.setMealId(existingId);
            return existingId;
        }

        connection.setAutoCommit(false);
        try {
            int mealId = insertMealHeader(userId, dayOfWeek, mealType, meal);
            meal.setMealId(mealId);
            insertMealItems(mealId, meal.getFoodItems());

            connection.commit();
            return mealId;
        } catch (SQLException e) {
            connection.rollback();
            throw e;
        } finally {
            connection.setAutoCommit(true);
        }
    }

    private int insertMealHeader(int userId, String dayOfWeek, String mealType, Meal meal) throws SQLException {
        String sql = "INSERT INTO meals (user_id, day_of_week, meal_type, calories, protein, carbs, fat) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setInt(1, userId);
            stmt.setString(2, dayOfWeek);
            stmt.setString(3, mealType);
            stmt.setDouble(4, meal.calories.getCurrent());
            stmt.setDouble(5, meal.protein.getCurrent());
            stmt.setDouble(6, meal.carbs.getCurrent());
            stmt.setDouble(7, meal.fat.getCurrent());
            stmt.executeUpdate();

            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    return rs.getInt(1);
                }
                throw new SQLException("Failed to get generated meal ID");
            }
        }
    }

    private void insertMealItems(int mealId, Map<FoodItem, Double> foodItems) throws SQLException {
        if (foodItems == null || foodItems.isEmpty()) {
            return;
        }

        String sql = "INSERT INTO meal_items (meal_id, food_name, amount, calories, protein, carbs, fat) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            for (Map.Entry<FoodItem, Double> entry : foodItems.entrySet()) {
                FoodItem food = entry.getKey();
                double amount = entry.getValue();
                double multiplier = amount / 100.0; // per 100g

                stmt.setInt(1, mealId);
                stmt.setString(2, food.getName());
                stmt.setDouble(3, amount);
                stmt.setDouble(4, food.getCalories() * multiplier);
                stmt.setDouble(5, food.getProtein() * multiplier);
                stmt.setDouble(6, food.getCarbs() * multiplier);
                stmt.setDouble(7, food.getFat() * multiplier);
                stmt.addBatch();
            }
            stmt.executeBatch();
        }
    }

    public void deleteMeal(int mealId) throws SQLException {
        if (mealId <= 0) {
            throw new SQLException("Invalid meal ID");
        }

        connection.setAutoCommit(false);
        try {
            deleteMealItems(mealId);
            deleteMealHeader(mealId);
            connection.commit();
        } catch (SQLException e) {
            connection.rollback();
            throw new SQLException("Failed to delete meal: " + e.getMessage());
        } finally {
            connection.setAutoCommit(true);
        }
    }

    private void deleteMealItems(int mealId) throws SQLException {
        String sql = "DELETE FROM meal_items WHERE meal_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, mealId);
            stmt.executeUpdate();
        }
    }

    private void deleteMealHeader(int mealId) throws SQLException {
        String sql = "DELETE FROM meals WHERE meal_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, mealId);
            stmt.executeUpdate();
        }
    }

    public int getMealIdForDay(int userId, String dayOfWeek, String mealType) throws SQLException {
        String sql = "SELECT meal_id FROM meals WHERE user_id = ? AND day_of_week = ? AND meal_type = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, userId);
            stmt.setString(2, dayOfWeek);
            stmt.setString(3, mealType);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("meal_id");
                }
            }
        }
        return -1;
    }


    public Meal getMeal(int mealId,Double calReq,Double ProReq,Double fatReq,Double carbReq) throws SQLException {
        // First get the meal header
        Meal meal = new Meal(calReq,ProReq,carbReq,fatReq);
        meal.setMealId(mealId);
        if (meal == null) {
            return null;
        }

        Map<FoodItem, Double> foodItems = getMealItems(mealId);
        for (Map.Entry<FoodItem, Double> entry : foodItems.entrySet()) {
            meal.addFoodItem(entry.getKey(), entry.getValue());
        }

        return meal;
    }

    private Map<FoodItem, Double> getMealItems(int mealId) throws SQLException {
        String sql = "SELECT food_name, amount FROM meal_items WHERE meal_id = ?";
        Map<FoodItem, Double> items = new HashMap<>();

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, mealId);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    String foodName = rs.getString("food_name");
                    double amount = rs.getDouble("amount");
                    FoodItem food = FoodDatabase.getFoodByName(foodName);
                    if (food != null) {
                        items.put(food, amount);
                    }
                }
            }
        }
        return items;
    }

    public boolean mealExists(int userId, String dayOfWeek, String mealType) throws SQLException {
        String sql = "SELECT COUNT(*) FROM meals WHERE user_id = ? AND day_of_week = ? AND meal_type = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, userId);
            stmt.setString(2, dayOfWeek);
            stmt.setString(3, mealType);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0;
                }
            }
        }
        return false;
    }

    public void close() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        } catch (SQLException e) {
            System.err.println("Error closing connection: " + e.getMessage());
        }
    }
}