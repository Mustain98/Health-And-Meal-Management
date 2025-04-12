package CLI;

import Database.*;
import Food.FooddbManager;
import MealPrep.*;
import User.*;
import java.sql.SQLException;

public class MealPlannerCLI {
    private final ConsoleIO console;
    private final FooddbManager fooddbManager;
    private final UserDAO userDAO;

    public MealPlannerCLI() throws SQLException {
        this.console = new ConsoleIO();
        this.fooddbManager = new FooddbManager();
        this.userDAO = new UserDAO();
    }

    public void run() {
        try {
            User user = new AuthenticationService(userDAO, console).authenticate();
            if (user == null) return;
            MealRepo mealRepo=new MealRepo(fooddbManager);
            MealManager mealManager = new MealManager(fooddbManager, user,mealRepo);
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