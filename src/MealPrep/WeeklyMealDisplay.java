// WeeklyMealDisplay.java
package MealPrep;

import java.sql.SQLException;
import java.util.Scanner;

public class WeeklyMealDisplay {
    private final MealManager mealManager;
    private final Scanner scanner;

    public WeeklyMealDisplay(MealManager mealManager) {
        this.mealManager = mealManager;
        this.scanner = new Scanner(System.in);
    }

    public void showMainMenu() throws SQLException {
        while (true) {
            System.out.println("\n=== WEEKLY MEAL PLAN ===");
            System.out.println("1. View Day");
            System.out.println("2. Manage Meals");
            System.out.println("3. View Weekly Summary");
            System.out.println("0. Back to Main Menu");
            System.out.print("Choice: ");

            int choice = Integer.parseInt(scanner.nextLine());
            switch (choice) {
                case 1: showDayMenu(); break;
                case 2: manageMealsMenu(); break;
                case 3: mealManager.showWeeklySummary(); break;
                case 0: return;
                default: System.out.println("Invalid choice");
            }
        }
    }

    private void showDayMenu() {
        String[] days = {"Monday","Tuesday","Wednesday","Thursday",
                "Friday","Saturday","Sunday"};

        System.out.println("\nSelect Day:");
        for (int i = 0; i < days.length; i++) {
            System.out.printf("%d. %s\n", i+1, days[i]);
        }
        System.out.println("0. Back");
        System.out.print("Choice: ");

        int choice = Integer.parseInt(scanner.nextLine());
        if (choice == 0) return;
        if (choice < 1 || choice > 7) {
            System.out.println("Invalid day");
            return;
        }

        showDayDetails(days[choice-1]);
    }

    private void showDayDetails(String day) {
        System.out.println("\n=== " + day.toUpperCase() + " ===");
        showMeal(day + "_Breakfast");
        showMeal(day + "_Lunch");
        showMeal(day + "_Dinner");
    }

    private void manageMealsMenu() throws SQLException, SQLException {
        String[] days = {"Monday","Tuesday","Wednesday","Thursday",
                "Friday","Saturday","Sunday"};

        System.out.println("\nSelect Day to Manage:");
        for (int i = 0; i < days.length; i++) {
            System.out.printf("%d. %s\n", i+1, days[i]);
        }
        System.out.println("0. Back");
        System.out.print("Choice: ");

        int dayChoice = Integer.parseInt(scanner.nextLine());
        if (dayChoice == 0) return;
        if (dayChoice < 1 || dayChoice > 7) {
            System.out.println("Invalid day");
            return;
        }

        System.out.println("\nSelect Meal:");
        System.out.println("1. Breakfast");
        System.out.println("2. Lunch");
        System.out.println("3. Dinner");
        System.out.println("0. Back");
        System.out.print("Choice: ");

        int mealChoice = Integer.parseInt(scanner.nextLine());
        if (mealChoice == 0) return;
        if (mealChoice < 1 || mealChoice > 3) {
            System.out.println("Invalid meal");
            return;
        }

        String mealKey = days[dayChoice-1] + "_" +
                (mealChoice == 1 ? "Breakfast" : mealChoice == 2 ? "Lunch" : "Dinner");
        mealManager.manageMeal(mealKey);
    }

    private void showMeal(String mealKey) {
        Meal meal = mealManager.getMeal(mealKey);
        System.out.println("\n" + mealKey.split("_")[1] + ":");
        System.out.println(meal);
    }
}