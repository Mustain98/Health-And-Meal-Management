package MealPrep;
import java.util.HashMap;
import java.util.Map;
import User.User;
import Database.FoodDatabase;

public class MealManager {
    private Map<String, Meal> weeklyMealPlan;
    private MealPlanner mealPlanner;

    public MealManager(FoodDatabase foodDatabase) {
        this.weeklyMealPlan = new HashMap<>();
        this.mealPlanner = new MealPlanner(foodDatabase);
    }

    public void generateWeeklyMealPlan(User user) {
        String[] days = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};
        for (String day : days) {
            weeklyMealPlan.put(day + "_Breakfast", mealPlanner.generateMeal(user, "Breakfast"));
            weeklyMealPlan.put(day + "_Lunch", mealPlanner.generateMeal(user, "Lunch"));
            weeklyMealPlan.put(day + "_Dinner", mealPlanner.generateMeal(user, "Dinner"));
        }
    }

    public void displayWeeklyMealPlan() {
        for (Map.Entry<String, Meal> entry : weeklyMealPlan.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }
}
