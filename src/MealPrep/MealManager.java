package MealPrep;

import Database.FoodDatabase;
import Food.FoodItem;
import User.User;

import java.sql.SQLException;
import java.util.*;

public class MealManager {
    private final Map<String, Meal> meals;
    private final FoodDatabase foodDatabase;
    private final User user;

    public MealManager(FoodDatabase foodDatabase, User user) {
        this.foodDatabase = foodDatabase;
        this.user = user;
        this.meals = initializeWeeklyMeals();
    }

    private Map<String, Meal> initializeWeeklyMeals() {
        Map<String, Meal> mealMap = new LinkedHashMap<>();
        String[] days = {"Monday", "Tuesday", "Wednesday", "Thursday",
                "Friday", "Saturday", "Sunday"};

        double mealCalories = user.getDailyCalorieRequirement() / 3;
        double mealProtein = user.getProteinRequirement() / 3;
        double mealCarbs = user.getCarbRequirement() / 3;
        double mealFat = user.getFatRequirement() / 3;

        for (String day : days) {
            mealMap.put(day + "_Breakfast",
                    new Meal(mealCalories, mealProtein, mealCarbs, mealFat));
            mealMap.put(day + "_Lunch",
                    new Meal(mealCalories, mealProtein, mealCarbs, mealFat));
            mealMap.put(day + "_Dinner",
                    new Meal(mealCalories, mealProtein, mealCarbs, mealFat));
        }
        return mealMap;
    }

    public void manageMeal(String mealKey) throws SQLException {
        Meal meal = meals.get(mealKey);
        Scanner scanner = new Scanner(System.in);
        showRecommededFoods(user);
        while (!meal.isWithinTarget()) {
            System.out.println("\n" + meal.getProgress()+"\n\n");
            System.out.print("Enter food item to add (or 'done' to finish): ");
            String foodName = scanner.nextLine();
            if (foodName.equalsIgnoreCase("done")) break;

            try {
                List<FoodItem> matches = foodDatabase.searchFoods(foodName);
                if (matches.isEmpty()) {
                    System.out.println("No matches found. Try different keywords.");
                    continue;
                }

                System.out.println("Found foods:");
                for (int i = 0; i < matches.size(); i++) {
                    System.out.printf("%d. %s\n", i+1, matches.get(i));
                }

                System.out.print("Select food number: ");
                int choice = scanner.nextInt();
                scanner.nextLine();
                if (choice > 0 && choice <= matches.size()) {
                    System.out.println("(100g x number)\n"+"Number: " );
                    int amount=scanner.nextInt();
                    meal.addFoodItem(matches.get(choice-1),amount);
                    System.out.println("Added: " + matches.get(choice-1)+" x "+amount);
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    public void displayWeeklyPlan() {
        System.out.println("\n=== WEEKLY MEAL PLAN ===");
        meals.forEach((key, meal) -> {
            String[] parts = key.split("_");
            System.out.printf("\n%s - %s:\n%s", parts[0], parts[1], meal);
        });
    }

//    public Map<String, Meal> getMeals() {
//        return Collections.unmodifiableMap(meals);
//    }
    public void showRecommededFoods(User user) throws SQLException {
        List<FoodItem>recommededFoods=foodDatabase.getFoodsByNutritionalValue(user);
        System.out.println("=====Recommended Foods======\n");
        for (FoodItem foodItem : recommededFoods) {
            System.out.println(foodItem);
        }
    }

}