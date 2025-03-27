package MealPrep;

import Food.FoodItem;
import java.util.HashMap;
import java.util.Map;

public class Meal {
    private final Map<FoodItem, Double> foodItems;
    final NutritionalRequirement calories;
    final NutritionalRequirement protein;
    final NutritionalRequirement carbs;
    final NutritionalRequirement fat;
    private final double tolerance;

    public Meal(double targetCalories, double targetProtein,
                double targetCarbs, double targetFat) {
        this(targetCalories, targetProtein, targetCarbs, targetFat, 0.1);
    }

    public Meal(double targetCalories, double targetProtein,
                double targetCarbs, double targetFat, double tolerance) {
        this.foodItems = new HashMap<>();
        this.tolerance = tolerance;
        this.calories = new NutritionalRequirement("Calories", targetCalories, tolerance);
        this.protein = new NutritionalRequirement("Protein", targetProtein, tolerance);
        this.carbs = new NutritionalRequirement("Carbs", targetCarbs, tolerance);
        this.fat = new NutritionalRequirement("Fat", targetFat, tolerance);
    }

    public void addFoodItem(FoodItem food, Double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Amount must be positive");
        }
        foodItems.merge(food, amount, Double::sum);
        double multiplier = amount / 100.0; // Assuming amount is in grams
        calories.add(food.getCalories() * multiplier);
        protein.add(food.getProtein() * multiplier);
        carbs.add(food.getCarbs() * multiplier);
        fat.add(food.getFat() * multiplier);
    }

    public boolean removeFoodItem(FoodItem food, double amount) {
        if (!foodItems.containsKey(food) || amount <= 0) {
            return false;
        }

        Double currentAmount = foodItems.get(food);
        if (amount >= currentAmount) {
            // Remove completely
            removeFoodNutrition(food, currentAmount);
            foodItems.remove(food);
        } else {
            // Reduce amount
            removeFoodNutrition(food, amount);
            foodItems.put(food, currentAmount - amount);
        }
        return true;
    }

    private void removeFoodNutrition(FoodItem food, double amount) {
        double multiplier = amount / 100.0;
        calories.subtract(food.getCalories() * multiplier);
        protein.subtract(food.getProtein() * multiplier);
        carbs.subtract(food.getCarbs() * multiplier);
        fat.subtract(food.getFat() * multiplier);
    }

    public boolean isWithinTarget() {
        return !isExceeded() && allMacrosFulfilled();
    }

    public boolean allMacrosFulfilled() {
        return protein.isFulfilled() && carbs.isFulfilled() && fat.isFulfilled();
    }

    public boolean isExceeded() {
        return calories.isExceeded() || carbs.isExceeded() || fat.isExceeded();
    }

    public String getProgress() {
        StringBuilder sb = new StringBuilder();
        sb.append("=== NUTRITIONAL PROGRESS ===\n");
        sb.append(calories.getProgressWithBar()).append("\n");
        sb.append(protein.getProgressWithBar()).append("\n");
        sb.append(carbs.getProgressWithBar()).append("\n");
        sb.append(fat.getProgressWithBar()).append("\n\n");

        // Status messages
        if (allMacrosFulfilled()) {
            sb.append("✓ All macro targets met!\n");
        } else {
            if (!protein.isFulfilled()) {
                sb.append(String.format("Need %.1fg more protein\n", protein.getRemaining()));
            }
            if (!carbs.isFulfilled()) {
                sb.append(String.format("Need %.1fg more carbs\n", carbs.getRemaining()));
            }
            if (!fat.isFulfilled()) {
                sb.append(String.format("Need %.1fg more fat\n", fat.getRemaining()));
            }
        }

        if (isExceeded()) {
            sb.append("\n⚠ WARNINGS:\n");
            if (calories.isExceeded()) {
                sb.append(String.format("- Calories exceeded by %.1f\n", calories.getExcess()));
            }
            if (carbs.isExceeded()) {
                sb.append(String.format("- Carbs exceeded by %.1fg\n", carbs.getExcess()));
            }
            if (fat.isExceeded()) {
                sb.append(String.format("- Fat exceeded by %.1fg\n", fat.getExcess()));
            }
        }

        return sb.toString();
    }

    public String getMacroRatios() {
        double total = protein.getCurrent() + carbs.getCurrent() + fat.getCurrent();
        if (total == 0) return "No macros recorded yet";

        return String.format("P:%.1f%% C:%.1f%% F:%.1f%%",
                (protein.getCurrent() / total) * 100,
                (carbs.getCurrent() / total) * 100,
                (fat.getCurrent() / total) * 100);
    }

    public Map<FoodItem, Double> getFoodItems() {
        return new HashMap<>(foodItems);
    }

    public double getTolerance() {
        return tolerance;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getProgress()).append("\n");
        sb.append("Macro Ratio: ").append(getMacroRatios()).append("\n\n");
        sb.append("=== FOOD ITEMS ===\n");

        if (foodItems.isEmpty()) {
            sb.append("No food items added yet\n");
        } else {
            foodItems.forEach((item, amount) ->
                    sb.append(String.format("- %s x%.1fg (P:%.1fg C:%.1fg F:%.1fg)\n",
                            item.getName(),
                            amount,
                            item.getProtein() * (amount / 100.0),
                            item.getCarbs() * (amount / 100.0),
                            item.getFat() * (amount / 100.0))));
        }

        return sb.toString();
    }
}