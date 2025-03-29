// WeeklyMealDisplay.java
package MealPrep;

import CLI.ConsoleIO;

import java.sql.SQLException;
import java.util.Scanner;

import static MealPrep.MealManager.days;

public class WeeklyMealDisplay {
    private final MealManager mealManager;
    private final Scanner scanner;
    private final ConsoleIO consoleIO;
    public WeeklyMealDisplay(MealManager mealManager) {
        this.mealManager = mealManager;
        this.scanner = new Scanner(System.in);
        this.consoleIO=new ConsoleIO();
    }

    public void showMainMenu() throws SQLException {
        while (true) {
            System.out.println("\n=== WEEKLY MEAL PLAN ===");
            System.out.println("1. View Day");
            System.out.println("2. Manage Meals");
            System.out.println("3. Delete Meal");
            System.out.println("4. View Weekly Summary");
            System.out.println("0. Back to Main Menu");
            System.out.print("Choice: ");

            int choice = Integer.parseInt(scanner.nextLine());
            switch (choice) {
                case 1: showDayMenu(); break;
                case 2: manageMealsMenu(); break;
                case 3:deleteMealMenu(); break;
                case 4: mealManager.showWeeklySummary(); break;
                case 0: return;
                default: System.out.println("Invalid choice");
            }
        }
    }

    private void deleteMealMenu() throws SQLException {
        String mealKey = getMealKeyFromMenu();
        if (mealKey == null) return;

        Meal meal = mealManager.getMeal(mealKey);
        System.out.println("\nCurrent meal:");
        System.out.println(meal);

        if (meal.getMealId() == 0) {
            System.out.println("\nThis meal hasn't been saved to the database yet.");
            return;
        }

        if (consoleIO.getConfirmation("Are you sure you want to delete this meal?")) {
            mealManager.deleteMeal(mealKey);
            System.out.println("Meal deleted!");
        }
    }

    private void showDayMenu() throws SQLException {
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

    private void showDayDetails(String day) throws SQLException {
        System.out.println("\n=== " + day.toUpperCase() + " ===");
        showMeal(day + "_Breakfast");
        showMeal(day + "_Lunch");
        showMeal(day + "_Dinner");
    }
    private void showMeal(String mealKey) throws SQLException {
        Meal meal = mealManager.getMeal(mealKey);
        System.out.println("\n" + mealKey.split("_")[1] + ":");
        System.out.println(meal);
    }

    private void manageMealsMenu() throws SQLException {
        String mealKey = getMealKeyFromMenu();
        if (mealKey == null) return;
        if(mealManager.getmeals().get(mealKey).mealId!=0){
            System.out.println("Meal already saved");
            return;
        }
        mealManager.manageMeal(mealKey);
        if (consoleIO.getConfirmation("\nSave this meal?")) {
            mealManager.saveMeal(day(mealKey),meal(mealKey),mealKey);
            System.out.println("Meal saved!");
        }
    }

    private String getMealKeyFromMenu() {
        System.out.println("\nSelect Day to Manage:");
        for (int i = 0; i < days.length; i++) {
            System.out.printf("%d. %s\n", i+1, days[i]);
        }
        System.out.println("0. Back");
        System.out.print("Choice: ");

        int dayChoice = Integer.parseInt(scanner.nextLine());
        if (dayChoice == 0) return null;
        if (dayChoice < 1 || dayChoice > 7) {
            System.out.println("Invalid day");
            return null;
        }

        System.out.println("\nSelect Meal:");
        System.out.println("1. Breakfast");
        System.out.println("2. Lunch");
        System.out.println("3. Dinner");
        System.out.println("0. Back");
        System.out.print("Choice: ");

        int mealChoice = Integer.parseInt(scanner.nextLine());
        if (mealChoice == 0) return null;
        if (mealChoice < 1 || mealChoice > 3) {
            System.out.println("Invalid meal");
            return null;
        }

        String mealKey = days[dayChoice-1] + "_" +
                (mealChoice == 1 ? "Breakfast" : mealChoice == 2 ? "Lunch" : "Dinner");
        return mealKey;
    }
    private String day(String mealKey) {
       String[]parts = mealKey.split("_");
       return parts[0];
    }
    private String meal(String mealKey) {
        String[]parts = mealKey.split("_");
        return parts[1];
    }

}