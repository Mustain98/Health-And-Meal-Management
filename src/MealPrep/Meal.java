package MealPrep;

import Food.FoodItem;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Meal {
    private final HashMap<FoodItem, Integer> foodItems;
    private double totalCalories;
    private double totalProtein;
    private double totalCarbs;
    private double totalFat;
    private final double targetCalories;
    private final double targetProtein;
    private final double targetCarbs;
    private final double targetFat;

    public Meal(double targetCalories, double targetProtein, double targetCarbs, double targetFat) {
        this.foodItems = new HashMap<>();
        this.targetCalories = targetCalories;
        this.targetProtein = targetProtein;
        this.targetCarbs = targetCarbs;
        this.targetFat = targetFat;
    }

    public void addFoodItem(FoodItem food,int amount) {
        foodItems.put(food, amount);
        totalCalories += food.getCalories()*amount;
        totalProtein += food.getProtein()*amount;
        totalCarbs += food.getCarbs()*amount;
        totalFat += food.getFat()*amount;
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

//    public List<FoodItem> getFoodItems() {
//        return new ArrayList<>(foodItems);
//    }

    public double getTotalCalories() { return totalCalories; }
    public double getTotalProtein() { return totalProtein; }
    public double getTotalCarbs() { return totalCarbs; }
    public double getTotalFat() { return totalFat; }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getProgress()).append("\n");
        for (Map.Entry<FoodItem,Integer> item : foodItems.entrySet()) {
            sb.append("- ").append(item.getKey()).append(item.getValue()).append("\n");
        }
        return sb.toString();
    }
}