package CLI;

import java.sql.SQLException;
import java.util.Scanner;
import Database.FoodDatabase;
import MealPrep.MealManager;
import User.User;
import Enum.*;
import Activity.*;
import Goal.*;
import HealthIssue.*;

public class MealPlannerCLI {
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        // Connect to the database
        FoodDatabase foodDatabase = new FoodDatabase();

        // Get user input
        User user = createUser();

        // Generate and display meal plan
        MealManager mealManager = new MealManager(foodDatabase);
        mealManager.generateWeeklyMealPlan(user);

        while (true) {
            System.out.println("\n==== Meal Planner Menu ====");
            System.out.println("1. View Weekly Meal Plan");
            System.out.println("2. Generate a New Meal Plan");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    mealManager.displayWeeklyMealPlan();
                    break;
                case 2:
                    mealManager.generateWeeklyMealPlan(user);
                    System.out.println("New meal plan generated.");
                    break;
                case 3:
                    System.out.println("Exiting... Goodbye!");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static User createUser() {
        System.out.print("Enter your name: ");
        String name = scanner.nextLine();

        System.out.print("Enter your age: ");
        int age = scanner.nextInt();

        System.out.print("Enter your weight (kg): ");
        double weight = scanner.nextDouble();

        System.out.print("Enter your height (cm): ");
        double height = scanner.nextDouble();

        scanner.nextLine(); // Consume newline

        System.out.print("Enter your gender (Male/Female): ");
        Gender gender = Gender.valueOf(scanner.nextLine());

        System.out.print("Enter your activity level (LowActivity/ModerateActivity/HightActivity): ");
        ActivityLevel activityLevel = getActivityLevel(scanner.nextLine());

        System.out.print("Do you have any health conditions? (Diabetes, HeartDisease,HyperTension,KidneyDisease, Obesity,UnderWeight None): ");
        HealthCondition healthCondition = getHealthCondition(scanner.nextLine());

        System.out.print("Enter your goal (LoseFat/MaintainWeight/GainWeight/GainMuscle): ");
        Goal goal = getGoal(scanner.nextLine());

        return new User(name, age, weight, height, gender, activityLevel, healthCondition, goal);
    }

    private static ActivityLevel getActivityLevel(String input) {
        switch (input.toLowerCase()) {
            case "lowactivity": return new LowActivity();
            case "moderateactivity": return new ModerateActivity();
            case "highactiviy": return new HighActivity();
            default: return new ModerateActivity();
        }
    }

    private static HealthCondition getHealthCondition(String input) {
        switch (input.toLowerCase()) {
            case "diabetes": return new Diabetes();
            case "heartdisease": return new HeartDisease();
            case "hypertension": return new Hypertension();
            case "kidneydisease": return new KidneyDisease();
            case "obesity": return new Obesity();
            case "underweight": return new UnderWeight();
            default: return new NoHealthIssues();
        }
    }

    private static Goal getGoal(String input) {
        switch (input.toLowerCase()) {
            case "loseweight": return new LoseFat();
            case "maintainweight": return new MaintainWeight();
            case "gainweight": return new GainWeight();
            case "gainmuscle": return new GainMuscle();
            default: return new MaintainWeight();
        }
    }
}
