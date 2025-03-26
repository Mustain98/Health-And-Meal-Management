package MealPrep;
import java.util.ArrayList;
import java.util.List;

import Food.FoodItem;

public class Meal {
    private List<FoodItem> foodItems;
    private double totalCalories;
    private double totalProtein;
    private double totalCarbs;
    private double totalFat;

    public Meal() {
        this.foodItems = new ArrayList<>();
    }

    public void addFoodItem(FoodItem food) {
        foodItems.add(food);
        totalCalories += food.getCalories();
        totalProtein += food.getProtein();
        totalCarbs += food.getCarbs();
        totalFat += food.getFat();
    }

    public List<FoodItem> getFoodItems() {
        return foodItems;
    }

    public double getTotalCalories() {
        return totalCalories;
    }

    public double getTotalProtein() {
        return totalProtein;
    }

    public double getTotalCarbs() {
        return totalCarbs;
    }

    public double getTotalFat() {
        return totalFat;
    }

    @Override
    public String toString() {
        return "Meal: " + foodItems + " | Calories: " + totalCalories + " kcal, Protein: " + totalProtein + "g, Carbs: " + totalCarbs + "g, Fat: " + totalFat + "g";
    }
}
