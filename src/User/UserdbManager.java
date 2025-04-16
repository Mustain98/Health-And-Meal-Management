package User;
import Database.UserDatabase;
import java.sql.*;

public class UserdbManager {
    private final Connection connection;

    public UserdbManager() {
        this.connection = UserDatabase.getInstance().getConnection();
    }

    public void updateName(int userID, String newName) {
        String query = "UPDATE users SET username = ? WHERE user_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, newName);
            stmt.setInt(2, userID);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error updating name: " + e.getMessage());
        }
    }

    public void UpdateWeight(int userID, double weight) {
        String query = "UPDATE user_profiles SET weight = ? WHERE user_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setDouble(1, weight);
            stmt.setInt(2, userID);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error updating weight: " + e.getMessage());
        }
    }

    public void updateAge(int userID, int age) {
        String query = "UPDATE user_profiles SET age = ? WHERE user_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, age);
            stmt.setInt(2, userID);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error updating age: " + e.getMessage());
        }
    }

    public void updateGoal(int userID, String goal) {
        String query = "UPDATE users_profiles SET goal = ? WHERE user_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, goal);
            stmt.setInt(2, userID);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error updating goal: " + e.getMessage());
        }
    }

    public void addHealthCondition(int userID, String healthCondition) {
        String query = "INSERT INTO user_health_conditions (user_id, condition_name) VALUES (?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, userID);
            stmt.setString(2, healthCondition);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error adding health condition: " + e.getMessage());
        }
    }

    public void removeHealthCondition(int userID, String condition) {
        String query = "DELETE FROM user_health_conditions WHERE user_id = ? AND condition_name = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, userID);
            stmt.setString(2, condition);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error removing health condition: " + e.getMessage());
        }
    }

    public void addFoodAllergy(int userID, String food) {
        String query = "INSERT INTO user_discouraged_foods (user_id, food_name) VALUES (?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, userID);
            stmt.setString(2, food);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error adding food allergy: " + e.getMessage());
        }
    }

    public void updateActivity(int userID, String simpleName) {
        String query = "UPDATE user_profiles SET activity = ? WHERE user_id = ?";
        try(PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, simpleName);
            stmt.setInt(2, userID);
        }catch (SQLException e) {
            System.err.println("Can't update activity");
        }
    }
}