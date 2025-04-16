package User;

import Database.UserDatabase;
import HealthIssue.*;
import Enum.*;
import Factory.*;
import java.sql.*;
import java.util.*;

public class UserDAO {
    private final Connection connection;

    public UserDAO() {
        this.connection = UserDatabase.getInstance().getConnection();
    }

    public int createUser(User user, String password) throws SQLException {
        connection.setAutoCommit(false);
        try {
            // Insert into users table
            int userId = insertUser(user.getName(), password);
            user.setUserID(userId);

            // Insert user profile
            insertUserProfile(userId, user);

            // Insert health conditions
            if (!user.getHealthConditions().isEmpty()) {
                insertHealthConditions(userId, user.getHealthConditions());
            }

            if (!user.getDiscouragedFoods().isEmpty()) {
                insertDiscouragedFoods(userId, user.getDiscouragedFoods());
            }

            connection.commit();
            return userId;
        } catch (SQLException e) {
            connection.rollback();
            throw e;
        } finally {
            connection.setAutoCommit(true);
        }
    }

    private int insertUser(String username, String password) throws SQLException {
        String sql = "INSERT INTO users (username, password_hash, created_at) VALUES (?, ?, CURRENT_TIMESTAMP)";

        try (PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, username);
            stmt.setString(2, password);
            stmt.executeUpdate();

            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    return rs.getInt(1);
                }
                throw new SQLException("Failed to get generated user ID");
            }
        }
    }

    private void insertUserProfile(int userId, User user) throws SQLException {
        String sql = "INSERT INTO user_profiles " +
                "(user_id, age, gender, weight, height, activity_level, goal) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, userId);
            stmt.setInt(2, user.getAge());
            stmt.setString(3, user.getGender().name());
            stmt.setDouble(4, user.getWeight());
            stmt.setDouble(5, user.getHeight());
            stmt.setString(6, user.getActivityLevel().getClass().getSimpleName());
            stmt.setString(7, user.getGoal().getClass().getSimpleName());
            stmt.executeUpdate();
        }
    }

    private void insertHealthConditions(int userId, List<HealthCondition> conditions) throws SQLException {
        String sql = "INSERT INTO user_health_conditions (user_id, condition_name) VALUES (?, ?)";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            for (HealthCondition condition : conditions) {
                if (!(condition instanceof NoHealthIssues)) {
                    stmt.setInt(1, userId);
                    stmt.setString(2, condition.getClass().getSimpleName());
                    stmt.addBatch();
                }
            }
            stmt.executeBatch();
        }
    }

    private void insertDiscouragedFoods(int userId, Set<String> foods) throws SQLException {
        String sql = "INSERT INTO user_discouraged_foods (user_id, food_name) VALUES (?, ?)";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            for (String food : foods) {
                stmt.setInt(1, userId);
                stmt.setString(2, food);
                stmt.addBatch();
            }
            stmt.executeBatch();
        }
    }

    public User getUserByCredentials(String username, String passwordHash) throws SQLException {
        String sql = "SELECT u.*, up.* FROM users u " +
                "JOIN user_profiles up ON u.user_id = up.user_id " +
                "WHERE u.username = ? AND u.password_hash = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, username);
            stmt.setString(2, passwordHash);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return mapResultSetToUser(rs);
                }
            }
        }
        return null;
    }

    private User mapResultSetToUser(ResultSet rs) throws SQLException {
        int userId = rs.getInt("user_id");

        User user = new User(
                rs.getString("username"),
                rs.getInt("age"),
                rs.getDouble("weight"),
                rs.getDouble("height"),
                Gender.valueOf(rs.getString("gender")),
                ActivityLevelFactory.create(rs.getString("activity_level")),
                getHealthConditions(userId),
                GoalFactory.create(rs.getString("goal"))
        );
        user.setUserID(rs.getInt("user_id"));
        for(String food:getDiscouragedFoods(userId)) {
            user.addDiscouragedFood(food);
        }
        return user;
    }

    private List<HealthCondition> getHealthConditions(int userId) throws SQLException {
        List<HealthCondition> conditions = new ArrayList<>();
        String sql = "SELECT condition_name FROM user_health_conditions WHERE user_id = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, userId);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    conditions.add(HealthConditionFactory.createSingle(rs.getString("condition_name")));
                }
            }
        }

        if (conditions.isEmpty()) {
            conditions.add(new NoHealthIssues());
        }
        return conditions;
    }

    private Set<String> getDiscouragedFoods(int userId) throws SQLException {
        Set<String> foods = new HashSet<>();
        String sql = "SELECT food_name FROM user_discouraged_foods WHERE user_id = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, userId);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    foods.add(rs.getString("food_name"));
                }
            }
        }
        return foods;
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