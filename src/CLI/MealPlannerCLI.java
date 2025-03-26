package CLI;

import Database.FoodDatabase;
import MealPrep.MealManager;
import User.*;
import Enum.*;
import Activity.*;
import Goal.*;
import HealthIssue.*;
import java.util.*;

public class MealPlannerCLI {
    private static final Scanner scanner = new Scanner(System.in);
    private static MealManager mealManager;

    public static void main(String[] args) {
        try {
            FoodDatabase foodDatabase = new FoodDatabase();
            User user = createUser();
            mealManager = new MealManager(foodDatabase, user);

            while (true) {
                System.out.println("\n=== MAIN MENU ===");
                System.out.println("1. Manage Weekly Meals");
                System.out.println("2. View Weekly Summary");
                System.out.println("3. View User Profile");
                System.out.println("0. Exit");
                System.out.print("Choice: ");

                String choice = scanner.nextLine();
                switch (choice) {
                    case "1": manageWeeklyMeals(); break;
                    case "2": mealManager.displayWeeklyPlan(); break;
                    case "3": System.out.println(user); break;
                    case "0": exit();
                    default: System.out.println("Invalid choice");
                }
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private static void manageWeeklyMeals() {
        String[] days = {"Monday", "Tuesday", "Wednesday", "Thursday",
                "Friday", "Saturday", "Sunday"};

        System.out.println("\nSelect Day:");
        for (int i = 0; i < days.length; i++) {
            System.out.printf("%d. %s\n", i+1, days[i]);
        }

        System.out.print("Enter day number (1-7): ");
        int dayChoice = Integer.parseInt(scanner.nextLine());
        if (dayChoice < 1 || dayChoice > 7) {
            System.out.println("Invalid day selection");
            return;
        }

        System.out.println("\nSelect Meal:");
        System.out.println("1. Breakfast\n2. Lunch\n3. Dinner");
        System.out.print("Enter meal choice (1-3): ");
        int mealChoice = Integer.parseInt(scanner.nextLine());

        String[] mealTypes = {"Breakfast", "Lunch", "Dinner"};
        String selectedMeal = days[dayChoice-1] + "_" + mealTypes[mealChoice-1];

        mealManager.manageMeal(selectedMeal);
    }

    private static void exit() {
        System.out.println("Saving meal plan...");
        System.out.println("Goodbye!");
        System.exit(0);
    }

    private static User createUser() {
        System.out.println("\n=== CREATE USER PROFILE ===");
        System.out.print("Enter your name: ");
        String name = scanner.nextLine();

        System.out.print("Enter your age: ");
        int age = Integer.parseInt(scanner.nextLine());

        System.out.print("Enter your weight (kg): ");
        double weight = Double.parseDouble(scanner.nextLine());

        System.out.print("Enter your height (cm): ");
        double height = Double.parseDouble(scanner.nextLine());

        System.out.print("Enter your gender (Male/Female): ");
        Gender gender = Gender.valueOf(scanner.nextLine());

        System.out.print("Enter activity level (LOW/MODERATE/HIGH): ");
        ActivityLevel activityLevel = createActivityLevel(scanner.nextLine());

        List<HealthCondition> healthConditions = createHealthConditions();

        System.out.print("Enter goal (LOSE FAT/MAINTAIN WEIGHT/GAIN WEIGHT/GAIN MUSCLE): ");
        Goal goal = createGoal(scanner.nextLine());

        return new User(name, age, weight, height, gender,
                activityLevel, healthConditions, goal);
    }

    private static ActivityLevel createActivityLevel(String input) {
        switch (input.toUpperCase()) {
            case "LOW": return new LowActivity();
            case "MODERATE": return new ModerateActivity();
            case "HIGH": return new HighActivity();
            default: return new ModerateActivity();
        }
    }

    private static List<HealthCondition> createHealthConditions() {
        List<HealthCondition> conditions = new ArrayList<>();
        System.out.println("\nAdd Health Conditions (type DONE when finished):");
        System.out.println("Options: DIABETES, HEART CONDITION, HYPERTENSION, KIDNEY CONDITION, OBESITY, UNDERWEIGHT, NONE");

        while (true) {
            System.out.print("Condition: ");
            String input = scanner.nextLine().toUpperCase();
            if (input.equals("DONE")) break;

            HealthCondition condition = switch (input) {
                case "DIABETES" -> new Diabetes();
                case "HEART CONDITION" -> new HeartDisease();
                case "HYPERTENSION" -> new Hypertension();
                case "KIDNEY CONDITION" -> new KidneyDisease();
                case "OBESITY" -> new Obesity();
                case "UNDERWEIGHT" -> new UnderWeight();
                case "NONE" -> new NoHealthIssues();
                default -> {
                    System.out.println("Invalid condition");
                    yield null;
                }
            };

            if (condition != null) {
                conditions.add(condition);
            }
        }

        if (conditions.isEmpty()) {
            conditions.add(new NoHealthIssues());
        }
        return conditions;
    }

    private static Goal createGoal(String input) {
        return switch (input.toUpperCase()) {
            case "LOSE FAT" -> new LoseFat();
            case "MAINTAIN WEIGHT" -> new MaintainWeight();
            case "GAIN WEIGHT" -> new GainWeight();
            case "GAIN MUSCLE" -> new GainMuscle();
            default -> new MaintainWeight();
        };
    }
}