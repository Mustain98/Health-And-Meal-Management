// MealPlannerCLI.java
package CLI;

import Database.FoodDatabase;
import MealPrep.*;
import User.*;
import Enum.*;
import Activity.*;
import Goal.*;
import HealthIssue.*;
import java.sql.SQLException;
import java.util.*;

public class MealPlannerCLI {
    private static final Scanner scanner = new Scanner(System.in);
    private static MealManager mealManager;
    private static WeeklyMealDisplay weeklyDisplay;

    public static void main(String[] args) {
        try {
            FoodDatabase foodDatabase = new FoodDatabase();
            User user = createUser();
            mealManager = new MealManager(foodDatabase, user);
            weeklyDisplay = new WeeklyMealDisplay(mealManager);

            mainMenu(user);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private static void mainMenu(User user) throws SQLException {
        while (true) {
            System.out.println("\n=== MAIN MENU ===");
            System.out.println("1. Weekly Meal Plan");
            System.out.println("2. Food Recommendations");
            System.out.println("3. View Discouraged Foods");
            System.out.println("4. View User Profile");
            System.out.println("5. Update User Profile");
            System.out.println("0. Exit");
            System.out.print("Choice: ");

            String choice = scanner.nextLine();
            switch (choice) {
                case "1": weeklyDisplay.showMainMenu(); break;
                case "2": showGeneralRecommendations(); break;
                case "3": mealManager.showDiscouragedFoods(); break;
                case "4": showUserProfile(); break;
                case "5": updateUserProfile(user); break;
                case "0":
                    System.out.println("Goodbye!");
                    System.exit(0);
                default: System.out.println("Invalid choice");
            }
        }
    }

    private static void showGeneralRecommendations() throws SQLException {
        Meal sampleMeal = new Meal(
                mealManager.getUser().getDailyCalorieRequirement()/3,
                mealManager.getUser().getProteinRequirement()/3,
                mealManager.getUser().getCarbRequirement()/3,
                mealManager.getUser().getFatRequirement()/3
        );
        mealManager.showFoodRecommendations(sampleMeal);
    }

    private static void showUserProfile() {
        System.out.println("\n=== USER PROFILE ===");
        System.out.println(mealManager.getUser().toString());
    }

    private static void updateUserProfile(User user) {
        System.out.println("\n=== UPDATE PROFILE ===");
        System.out.println("1. Update Weight");
        System.out.println("2. Update Activity Level");
        System.out.println("3. Update Goal");
        System.out.println("4. Update Health Conditions");
        System.out.println("5. Update Discouraged Foods");
        System.out.println("0. Back");
        System.out.print("Choice: ");

        String choice = scanner.nextLine();
        switch (choice) {
            case "1": updateWeight(user); break;
            case "2": updateActivityLevel(user); break;
            case "3": updateGoal(user); break;
            case "4": updateHealthConditions(user); break;
            case "5": updateDiscouragedFoods(user); break;
            case "0": return;
            default: System.out.println("Invalid choice");
        }
    }

    private static void updateWeight(User user) {
        System.out.print("Enter new weight (kg): ");
        double newWeight = Double.parseDouble(scanner.nextLine());
        user.setWeight(newWeight);
        System.out.println("Weight updated to: " + newWeight + "kg");
    }

    private static void updateActivityLevel(User user) {
        System.out.print("Enter new activity level (LOW/MODERATE/HIGH): ");
        String level = scanner.nextLine().toUpperCase();
        user.setactivity(createActivityLevel(level));
        System.out.println("Activity level updated to: " + level);
    }

    private static void updateGoal(User user) {
        System.out.print("Enter new goal (LOSE FAT/MAINTAIN/GAIN WEIGHT/GAIN MUSCLE): ");
        String goal = scanner.nextLine().toUpperCase();
        user.setGoal(createGoal(goal));
        System.out.println("Goal updated to: " + goal);
    }

    private static void updateHealthConditions(User user) {
        System.out.println("Current health conditions: \n");
        for(HealthCondition hc:user.getHealthConditions()){
            System.out.println(hc.getClass().getSimpleName());
        }
        System.out.println("Enter new health conditions (comma separated): ");
        user.setHealthConditions(createHealthConditions());
    }

    private static void updateDiscouragedFoods(User user) {
        System.out.println("Current discouraged foods: " +
                String.join(", ", mealManager.getUser().getDiscouragedFoods()));
        System.out.println("Enter new discouraged/avoided foods (comma separated): ");
        user.addDiscouragedFood(createDiscouragedFoods());
    }

    private static User createUser() {
        System.out.println("\n=== CREATE USER PROFILE ===");

        // Name
        System.out.print("Enter your name: ");
        String name = scanner.nextLine();

        // Age
        System.out.print("Enter your age: ");
        int age = Integer.parseInt(scanner.nextLine());

        // Weight
        System.out.print("Enter your weight (kg): ");
        double weight = Double.parseDouble(scanner.nextLine());

        // Height
        System.out.print("Enter your height (cm): ");
        double height = Double.parseDouble(scanner.nextLine());

        // Gender
        System.out.print("Enter your gender (Male/Female): ");
        Gender gender = Gender.valueOf(scanner.nextLine());

        // Activity Level
        System.out.print("Enter activity level (LOW/MODERATE/HIGH): ");
        ActivityLevel activityLevel = createActivityLevel(scanner.nextLine().toUpperCase());

        // Health Conditions
        List<HealthCondition> healthConditions = createHealthConditions();

        // Goal
        System.out.print("Enter goal (LOSE FAT/MAINTAIN/GAIN WEIGHT/GAIN MUSCLE): ");
        Goal goal = createGoal(scanner.nextLine().toUpperCase());

        return new User(name, age, weight, height, gender,
                activityLevel, healthConditions, goal);
    }

    private static ActivityLevel createActivityLevel(String input) {
        return switch (input) {
            case "LOW" -> new LowActivity();
            case "MODERATE" -> new ModerateActivity();
            case "HIGH" -> new HighActivity();
            default -> new ModerateActivity();
        };
    }

    private static List<HealthCondition> createHealthConditions() {
        List<HealthCondition> conditions = new ArrayList<>();
        System.out.println("Enter health conditions (comma separated): ");
        System.out.println("Options: DIABETES, HEART_DISEASE, HYPERTENSION, OBESITY, NONE");
        String[] inputs = scanner.nextLine().toUpperCase().split(",");

        for (String input : inputs) {
            HealthCondition condition = switch (input.trim()) {
                case "DIABETES" -> new Diabetes();
                case "HEART_DISEASE" -> new HeartDisease();
                case "HYPERTENSION" -> new Hypertension();
                case "OBESITY" -> new Obesity();
                case "NONE" -> new NoHealthIssues();
                default -> null;
            };
            if (condition != null) conditions.add(condition);
        }

        if (conditions.isEmpty()) conditions.add(new NoHealthIssues());
        return conditions;
    }

    private static Goal createGoal(String input) {
        return switch (input) {
            case "LOSE FAT" -> new LoseFat();
            case "MAINTAIN" -> new MaintainWeight();
            case "GAIN WEIGHT" -> new GainWeight();
            case "GAIN MUSCLE" -> new GainMuscle();
            default -> new MaintainWeight();
        };
    }

    private static List<String> createDiscouragedFoods() {
        System.out.println("Enter foods to avoid (comma separated): ");
        String[] foods = scanner.nextLine().split(",");
        return Arrays.stream(foods)
                .map(String::trim)
                .filter(s -> !s.isEmpty())
                .toList();
    }
}