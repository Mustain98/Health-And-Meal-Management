//package MealPrep;
//import java.sql.SQLException;
//import java.util.List;
//import java.util.Random;
//import Database.FoodDatabase;
//import User.User;
//import Food.FoodItem;
//
//public class MealPlanner {
//    private FoodDatabase foodDatabase;
//
//    public MealPlanner(FoodDatabase foodDatabase) {
//        this.foodDatabase = foodDatabase;
//    }
//
//    public Meal generateMeal(User user, String mealType) throws SQLException {
//        Meal meal = new Meal(user.getDailyCalorieRequirement(), user.getProteinRequirement(), user.getCarbRequirement(), user.getFatRequirement());
//        List<FoodItem> suitableFoods = foodDatabase.getFoodsByNutritionalValue(user);
//        Random random = new Random();
//
//        double calorieTarget = user.getDailyCalorieRequirement() / 3;
//
//        while (meal.getTotalCalories() < calorieTarget && !suitableFoods.isEmpty()) {
//            FoodItem food = suitableFoods.remove(random.nextInt(suitableFoods.size()));
//            meal.addFoodItem(food);
//        }
//
//        return meal;
//    }
//}
