package MealPrep;

import Food.FoodItem;
import java.util.ArrayList;
import java.util.List;

public class Meal {
    private final List<FoodItem> foodItems;
    private double totalCalories;
    private double totalProtein;
    private double totalCarbs;
    private double totalFat;
    private final double targetCalories;
    private final double targetProtein;
    private final double targetCarbs;
    private final double targetFat;

    public Meal(double targetCalories, double targetProtein, double targetCarbs, double targetFat) {
        this.foodItems = new ArrayList<>();
        this.targetCalories = targetCalories;
        this.targetProtein = targetProtein;
        this.targetCarbs = targetCarbs;
        this.targetFat = targetFat;
    }

    public void addFoodItem(FoodItem food) {
        foodItems.add(food);
        totalCalories += food.getCalories();
        totalProtein += food.getProtein();
        totalCarbs += food.getCarbs();
        totalFat += food.getFat();
    }

    public boolean isWithinTarget() {
        return totalCalories <= targetCalories * 1.1 &&
                totalProtein >= targetProtein * 0.9 &&
                totalCarbs <= targetCarbs * 1.1 &&
                totalFat <= targetFat * 1.1;
    }

    public String getProgress() {
        return String.format(
                "Progress: Calories %.1f/%.1f | Protein %.1f/%.1f | Carbs %.1f/%.1f | Fat %.1f/%.1f",
                totalCalories, targetCalories,
                totalProtein, targetProtein,
                totalCarbs, targetCarbs,
                totalFat, targetFat
        );
    }

    public List<FoodItem> getFoodItems() {
        return new ArrayList<>(foodItems);
    }

    public double getTotalCalories() { return totalCalories; }
    public double getTotalProtein() { return totalProtein; }
    public double getTotalCarbs() { return totalCarbs; }
    public double getTotalFat() { return totalFat; }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getProgress()).append("\n");
        for (FoodItem item : foodItems) {
            sb.append("- ").append(item).append("\n");
        }
        return sb.toString();
    }
}