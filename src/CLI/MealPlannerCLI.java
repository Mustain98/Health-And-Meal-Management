package CLI;

import Database.*;
import MealPrep.*;
import User.*;
import java.sql.SQLException;

public class MealPlannerCLI {
    private final ConsoleIO console;
    private final FoodDatabase foodDatabase;
    private final UserDAO userDAO;

    public MealPlannerCLI() throws SQLException {
        this.console = new ConsoleIO();
        this.foodDatabase = new FoodDatabase();
        this.userDAO = new UserDAO();
    }

    public void run() {
        try {
            User user = new AuthenticationService(userDAO, console).authenticate();
            if (user == null) return;

            MealManager mealManager = new MealManager(foodDatabase, user);
            WeeklyMealDisplay weeklyDisplay = new WeeklyMealDisplay(mealManager);
            new MainMenuService(console, mealManager, weeklyDisplay).show();

        } catch (Exception e) {
            console.printError("Error: " + e.getMessage());
        } finally {
            userDAO.close();
        }
    }

    public static void main(String[] args) {
        try {
            new MealPlannerCLI().run();
        } catch (SQLException e) {
            System.err.println("Failed to initialize application: " + e.getMessage());
        }
    }
}