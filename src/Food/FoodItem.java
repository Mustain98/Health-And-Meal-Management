package Food;

import User.User;

public class FoodItem {
    private String name;
    private double calories;
    private double protein;
    private double fat;
    private double carbs;
    private double sugar;
    private double fiber;
    private double sodium;
    private double potassium;
    private double iron;
    private double zinc;
    private double vitaminC;
    private double vitaminA;

    public FoodItem(String name, double calories, double protein, double fat,
                    double carbs, double sugar, double fiber, double sodium,
                    double potassium, double iron, double zinc,
                    double vitaminC, double vitaminA) {
        this.name = name;
        this.calories = calories;
        this.protein = protein;
        this.fat = fat;
        this.carbs = carbs;
        this.sugar = sugar;
        this.fiber = fiber;
        this.sodium = sodium;
        this.potassium = potassium;
        this.iron = iron;
        this.zinc = zinc;
        this.vitaminC = vitaminC;
        this.vitaminA = vitaminA;
    }

    public FoodItem(String name, double calories, double protein,
                    double fat, double carbs) {
        this(name, calories, protein, fat, carbs, 0, 0, 0, 0, 0, 0, 0, 0);
    }

    public boolean matchesUserNeeds(User user) {
        return (this.calories <= user.getDailyCalorieRequirement() / 3) &&
                (this.protein <= user.getProteinRequirement() / 3) &&
                (this.carbs <= user.getCarbRequirement() / 3) &&
                (this.fat <= user.getFatRequirement() / 3);
    }

    // Getters
    public String getName() { return name; }
    public double getCalories() { return calories; }
    public double getProtein() { return protein; }
    public double getFat() { return fat; }
    public double getCarbs() { return carbs; }
    public double getSugar() { return sugar; }
    public double getFiber() { return fiber; }
    public double getSodium() { return sodium; }
    public double getPotassium() { return potassium; }
    public double getIron() { return iron; }
    public double getZinc() { return zinc; }
    public double getVitaminC() { return vitaminC; }
    public double getVitaminA() { return vitaminA; }

    @Override
    public String toString() {
        return String.format("%s (%.1f kcal, P:%.1fg, F:%.1fg, C:%.1fg)",
                name, calories, protein, fat, carbs);
    }
}