// FoodRecommender.java (refactored)
package MealPrep;
import User.*;
import Database.FoodDatabase;
import Food.FoodItem;
import java.sql.SQLException;
import java.util.*;
import java.util.stream.Collectors;

public class FoodRecommender {
    private final FoodDatabase foodDatabase;
    private final Set<String> discouragedItems;
    private final User user;

    public FoodRecommender(FoodDatabase foodDatabase, User user, Set<String> discouragedItems) {
        this.foodDatabase = foodDatabase;
        this.user = user;
        this.discouragedItems = discouragedItems;
    }

    public Map<String, List<FoodItem>> getRecommendedFoodsByCategory(Meal meal) throws SQLException {
        Map<String, List<FoodItem>> recommendations = new LinkedHashMap<>();
        String[] categories = {"proteins", "carbs", "fats", "vegetables", "fruits", "nuts"};
        int maxRecommendations = 15;
        int offset = 10;
        // Get remaining nutritional needs
        double remainingCalories = meal.calories.getRemaining()+80;
        double remainingProtein = meal.protein.getRemaining()+ offset;
        double remainingCarbs = meal.carbs.getRemaining()+ offset;
        double remainingFat = meal.fat.getRemaining()+ offset;


        for (String category : categories) {
            List<FoodItem> foods = foodDatabase.getFoodsByCategory(
                    category,
                    remainingCalories,
                    remainingProtein,
                    remainingCarbs,
                    remainingFat
            );

            List<FoodItem> filteredFoods = new ArrayList<>();
            for (FoodItem food : foods) {
                if (!discouragedItems.contains(food.getName())) {
                    filteredFoods.add(food);
                }
            }

            sortFoodsByRelevance(filteredFoods, category);

            // Limit number of recommendations
            if (filteredFoods.size() > maxRecommendations) {
                filteredFoods = filteredFoods.subList(0, maxRecommendations);
            }

            if (!filteredFoods.isEmpty()) {
                recommendations.put(category, filteredFoods);
            }
        }

        return recommendations;
    }

    private void sortFoodsByRelevance(List<FoodItem> foods, String category) {
        switch (category) {
            case "proteins":
                foods.sort((a, b) -> Double.compare(b.getProtein(), a.getProtein()));
                break;
            case "carbs":
                foods.sort((a, b) -> Double.compare(b.getCarbs(), a.getCarbs()));
                break;
            case "fats":
                foods.sort((a, b) -> Double.compare(b.getFat(), a.getFat()));
                break;
            default:
                foods.sort((a, b) -> Double.compare(b.getCalories(), a.getCalories()));
        }
    }
}