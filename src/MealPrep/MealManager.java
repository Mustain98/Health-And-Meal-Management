// MealManager.java
package MealPrep;

import Database.FoodDatabase;
import Database.MealRepo;
import Food.FoodItem;
import User.User;
import java.sql.SQLException;
import java.util.*;

public class MealManager {
    private final Map<String, Meal> meals;
    private final FoodDatabase foodDatabase;
    private final User user;
    private final FoodRecommender foodRecommender;
    private final Set<String> discouragedItems;
    private final MealRepo mealRepo;
    public static String[] days = {"Monday", "Tuesday", "Wednesday", "Thursday",
            "Friday", "Saturday", "Sunday"};
    public MealManager(FoodDatabase foodDatabase, User user, MealRepo mealRepo) throws SQLException {
        this.foodDatabase = foodDatabase;
        this.user = user;
        this.mealRepo = mealRepo;
        this.discouragedItems = initializeDiscouragedItems(user);
        this.foodRecommender = new FoodRecommender(foodDatabase, user, discouragedItems);
        this.meals = initializeWeeklyMeals(); // Then populate it
    }

    private Set<String> initializeDiscouragedItems(User user) throws SQLException {
        Set<String> discouraged = new HashSet<>();
        for (String item : user.getDiscouragedFoods()) {
            for (FoodItem food : foodDatabase.searchFoods(item)) {
                discouraged.add(food.getName());
            }
        }
        return discouraged;
    }

    private Map<String, Meal> initializeWeeklyMeals() throws SQLException {
        Map<String, Meal> mealMap = new LinkedHashMap<>(); // Ensure we always return a map

        try {
            double mealCalories = user.getDailyCalorieRequirement() / 3;
            double mealProtein = user.getProteinRequirement() / 3;
            double mealCarbs = user.getCarbRequirement() / 3;
            double mealFat = user.getFatRequirement() / 3;

            for (String day : days) {
                Meal breakfast = loadOrCreateMeal(day + "_Breakfast", mealCalories, mealProtein, mealCarbs, mealFat);
                Meal lunch = loadOrCreateMeal(day + "_Lunch", mealCalories, mealProtein, mealCarbs, mealFat);
                Meal dinner = loadOrCreateMeal(day + "_Dinner", mealCalories, mealProtein, mealCarbs, mealFat);

                mealMap.put(day + "_Breakfast", breakfast);
                mealMap.put(day + "_Lunch", lunch);
                mealMap.put(day + "_Dinner", dinner);
            }
        } catch (Exception e) {
            System.err.println("Error initializing meals: " + e.getMessage());
        }

        return mealMap;
    }

    private Meal loadOrCreateMeal(String mealKey, double defaultCalories, double defaultProtein, double defaultCarbs, double defaultFat) throws SQLException {
        try {
            Meal meal = getMeal(mealKey);
            return meal != null ? meal : new Meal(defaultCalories, defaultProtein, defaultCarbs, defaultFat);
        } catch (Exception e) {
            System.err.println("Error loading meal: " + e.getMessage());
            return new Meal(defaultCalories, defaultProtein, defaultCarbs, defaultFat);
        }
    }

    public void manageMeal(String mealKey) throws SQLException {
        Meal meal = meals.get(mealKey);
        Scanner scanner = new Scanner(System.in);

        while (!meal.isWithinTarget()) {
            showFoodRecommendations(meal);
            System.out.println("\nCurrent Meal Status:");
            System.out.println(meal.getProgress());

            System.out.print("\nEnter food item to add (or 'done' to finish): ");
            String foodName = scanner.nextLine();
            if (foodName.equalsIgnoreCase("done")) break;

            try {
                List<FoodItem> matches = filterFoods(foodDatabase.searchFoods(foodName), discouragedItems);

                if (matches.isEmpty()) {
                    System.out.println("No safe matches found. Try different keywords.");
                    continue;
                }

                System.out.println("\nFound foods:");
                for (int i = 0; i < matches.size(); i++) {
                    System.out.printf("%d. %s\n", i+1, matches.get(i));
                }

                System.out.print("Select food number: ");
                int choice = Integer.parseInt(scanner.nextLine());

                if (choice > 0 && choice <= matches.size()) {
                    System.out.print("Enter amount (in grams): ");
                    double amount = Double.parseDouble(scanner.nextLine());

                    FoodItem selected = matches.get(choice-1);
                    meal.addFoodItem(selected, amount);
                    System.out.printf("Added: %.1fg of %s\n", amount, selected.getName());
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }
    public void deleteMeal(String mealKey) throws SQLException {
        Meal meal = meals.get(mealKey);
        if (meal.getMealId() != 0) {
            mealRepo.deleteMeal(meal.getMealId());
        }
        double mealCalories = user.getDailyCalorieRequirement() / 3;
        double mealProtein = user.getProteinRequirement() / 3;
        double mealCarbs = user.getCarbRequirement() / 3;
        double mealFat = user.getFatRequirement() / 3;
        Meal newMeal = new Meal(mealCalories, mealProtein, mealCarbs, mealFat);
        meals.put(mealKey, newMeal);
    }
    private List<FoodItem> filterFoods(List<FoodItem> foods, Set<String> discouraged) {
        List<FoodItem> filtered = new ArrayList<>();
        for (FoodItem food : foods) {
            if (!discouraged.contains(food.getName())) {
                filtered.add(food);
            }
        }
        return filtered;
    }

    public void showFoodRecommendations(Meal meal) throws SQLException {
        System.out.println("\n=== FOOD RECOMMENDATIONS ===");
        System.out.println("If newly updated foods are not being shown login again\n");
        System.out.printf("Remaining: Protein %.1fg | Carbs %.1fg | Fat %.1fg\n",
                meal.protein.getRemaining(),
                meal.carbs.getRemaining(),
                meal.fat.getRemaining());

        Map<String, List<FoodItem>> recommendations = foodRecommender.getRecommendedFoodsByCategory(meal);
        System.out.println(recommendations.size());
        for (Map.Entry<String, List<FoodItem>> entry : recommendations.entrySet()) {
            System.out.println("\n" + entry.getKey().toUpperCase() + ":");
            for (FoodItem food : entry.getValue()) {
                System.out.printf("- %s (P:%.1f C:%.1f F:%.1f)\n",
                        food.getName(), food.getProtein(), food.getCarbs(), food.getFat());
            }
        }
    }

    public void showWeeklySummary() throws SQLException {
        System.out.println("\n=== WEEKLY SUMMARY ===");
        System.out.printf("%-10s %-10s %-10s %-10s %-10s\n",
                "Day", "Calories", "Protein", "Carbs", "Fat");
        for (String day : days) {
            double calories = 0, protein = 0, carbs = 0, fat = 0;
            for (String mealType : new String[]{"Breakfast","Lunch","Dinner"}) {
                Meal meal = meals.get(day + "_" + mealType);
                calories += meal.calories.getCurrent();
                protein += meal.protein.getCurrent();
                carbs += meal.carbs.getCurrent();
                fat += meal.fat.getCurrent();
            }
            System.out.printf("%-10s %-10.1f %-10.1f %-10.1f %-10.1f\n",
                    day, calories, protein, carbs, fat);
        }
    }

    public Meal getMeal(String mealKey) throws SQLException {
        if (meals == null || mealKey == null) {
            return null;
        }

        try {
            String[] parts = mealKey.split("_");
            if (parts.length < 2) return null;

            String dayOfWeek = parts[0];
            String mealType = parts[1];

            int mealId = mealRepo.getMealIdForDay(user.getUserID(), dayOfWeek, mealType);
            if (mealId != -1) {
                Meal meal = mealRepo.getMeal(mealId,
                        user.getDailyCalorieRequirement()/3,
                        user.getProteinRequirement()/3,
                        user.getFatRequirement()/3,
                        user.getCarbRequirement()/3);
                if (meal != null) {
                    meals.get(mealKey).setMealId(mealId);
                    return meal;
                }
            }
            return meals.get(mealKey);
        } catch (Exception e) {
            System.err.println("Error getting meal: " + e.getMessage());
            return null;
        }
    }

    public void showDiscouragedFoods() {
        System.out.println("\n=== DISCOURAGED FOODS ===");
        if (discouragedItems.isEmpty()) {
            System.out.println("No discouraged foods");
        } else {
            for (String food : discouragedItems) {
                System.out.println("- " + food);
            }
        }
    }

    public User getUser() {
        return user;
    }
    public void saveMeal(String day,String meal,String mealKey) throws SQLException {
        mealRepo.addMeal(user.getUserID(), day,meal,meals.get(mealKey));
    }
    Map<String,Meal>getmeals(){
        return new HashMap<>(meals);
    }
}