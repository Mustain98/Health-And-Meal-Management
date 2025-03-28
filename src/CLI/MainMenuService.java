package CLI;

import MealPrep.*;
import User.*;
import java.sql.SQLException;

public class MainMenuService {
    private final ConsoleIO console;
    private final MealManager mealManager;
    private final WeeklyMealDisplay weeklyDisplay;

    public MainMenuService(ConsoleIO console, MealManager mealManager,
                           WeeklyMealDisplay weeklyDisplay) {
        this.console = console;
        this.mealManager = mealManager;
        this.weeklyDisplay = weeklyDisplay;
    }

    public void show() throws SQLException {
        while (true) {
            console.printHeader("MAIN MENU");
            int choice = console.getChoice(
                    "Weekly Meal Plan",
                    "Food Recommendations",
                    "View Discouraged Foods",
                    "View User Profile",
                    "Update User Profile",
                    "Logout"
            );

            switch (choice) {
                case 1: weeklyDisplay.showMainMenu(); break;
                case 2: showRecommendations(); break;
                case 3: mealManager.showDiscouragedFoods(); break;
                case 4: showProfile(); break;
                case 5: updateProfile(); break;
                case 6: return;
            }
        }
    }

    private void showRecommendations() throws SQLException {
        User user = mealManager.getUser();
        Meal sampleMeal = new Meal(
                user.getDailyCalorieRequirement()/3,
                user.getProteinRequirement()/3,
                user.getCarbRequirement()/3,
                user.getFatRequirement()/3
        );
        mealManager.showFoodRecommendations(sampleMeal);
    }

    private void showProfile() {
        console.printHeader("USER PROFILE");
        console.printMessage(mealManager.getUser().toString());
    }

    private void updateProfile() {
//        new ProfileUpdateService(console, mealManager).show();
    }
}