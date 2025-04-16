package CLI;

import Activity.ActivityLevel;
import Factory.ActivityLevelFactory;
import Factory.GoalFactory;
import Factory.HealthConditionFactory;
import Goal.Goal;
import HealthIssue.HealthCondition;

import java.util.Scanner;
import java.util.List;
import java.util.Arrays;

public class ConsoleIO {
    private final Scanner scanner;

    public ConsoleIO() {
        this.scanner = new Scanner(System.in);
    }

    // ========================
    // Display Methods
    // ========================

    public void printHeader(String title) {
        System.out.println("\n=== " + title.toUpperCase() + " ===");
    }

    public void printMessage(String message) {
        System.out.println(message);
    }

    public void printSuccess(String message) {
        System.out.println("\n✅ " + message);
    }

    public void printError(String message) {
        System.out.println("\n❌ Error: " + message);
    }

    public void printBulletList(String... items) {
        for (String item : items) {
            System.out.println(" • " + item);
        }
    }

    public void printTable(String[] headers, String[][] rows) {
        // Calculate column widths
        int[] widths = new int[headers.length];
        for (int i = 0; i < headers.length; i++) {
            widths[i] = headers[i].length();
        }

        for (String[] row : rows) {
            for (int i = 0; i < row.length; i++) {
                widths[i] = Math.max(widths[i], row[i].length());
            }
        }

        // Print header
        printTableRow(headers, widths);

        // Print separator
        System.out.println("-".repeat(Arrays.stream(widths).sum() + widths.length * 3 + 1));

        // Print rows
        for (String[] row : rows) {
            printTableRow(row, widths);
        }
    }

    private void printTableRow(String[] cells, int[] widths) {
        for (int i = 0; i < cells.length; i++) {
            System.out.printf(" %-" + widths[i] + "s |", cells[i]);
        }
        System.out.println();
    }

    // ========================
    // Input Methods
    // ========================

    public int getChoice(String... options) {
        for (int i = 0; i < options.length; i++) {
            System.out.println((i+1) + ". " + options[i]);
        }

        while (true) {
            System.out.print("\nEnter your choice (1-" + options.length + "): ");
            String input = scanner.nextLine().trim();

            try {
                int choice = Integer.parseInt(input);
                if (choice >= 1 && choice <= options.length) {
                    return choice;
                }
            } catch (NumberFormatException e) {
                // Continue to error message
            }

            printError("Please enter a number between 1 and " + options.length);
        }
    }

    public String getInput(String prompt) {
        System.out.print(prompt + ": ");
        return scanner.nextLine().trim();
    }

    public Goal getGoal() {
        String input = getInput("Enter Goal (LoseFat/Maintain/GAINWEIGHT/GainMuscle)");
        return GoalFactory.create(input);
    }
    public HealthCondition getHealthCondition() {
        String input = getInput("Enter Health Condition(DIABETES/HEART_DISEASE/HYPERTENSION/OBESITY/NONE)");
        return HealthConditionFactory.createSingle(input);
    }
    public ActivityLevel getActivityLevel() {
        String input = getInput("Enter ActivityLevel(Low/Moderate/High)");
        return ActivityLevelFactory.create(input);
    }
    public String getPassword(String prompt) {
        // Note: In real application, use Console.readPassword() for hidden input
        System.out.print(prompt + ": ");
        return scanner.nextLine().trim();
    }

    public String getPasswordWithConfirmation() {
        while (true) {
            String password = getPassword("Enter password");
            if (password.length() < 8) {
                printError("Password must be at least 8 characters");
                continue;
            }

            String confirm = getPassword("Confirm password");
            if (password.equals(confirm)) {
                return password;
            }
            printError("Passwords don't match!");
        }
    }

    public int getIntInput(String prompt) {
        while (true) {
            System.out.print(prompt + ": ");
            String input = scanner.nextLine().trim();

            try {
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                printError("Please enter a valid number");
            }
        }
    }

    public int getIntInput(String prompt, int min, int max) {
        while (true) {
            int value = getIntInput(prompt + " (" + min + "-" + max + ")");
            if (value >= min && value <= max) {
                return value;
            }
            printError("Please enter a number between " + min + " and " + max);
        }
    }

    public double getDoubleInput(String prompt) {
        while (true) {
            System.out.print(prompt + ": ");
            String input = scanner.nextLine().trim();

            try {
                return Double.parseDouble(input);
            } catch (NumberFormatException e) {
                printError("Please enter a valid number");
            }
        }
    }

    public double getDoubleInput(String prompt, double min, double max) {
        while (true) {
            double value = getDoubleInput(prompt + " (" + min + "-" + max + ")");
            if (value >= min && value <= max) {
                return value;
            }
            printError("Please enter a number between " + min + " and " + max);
        }
    }

    public boolean getConfirmation(String prompt) {
        while (true) {
            System.out.print(prompt + " (y/n): ");
            String input = scanner.nextLine().trim().toLowerCase();

            if (input.equals("y") || input.equals("yes")) {
                return true;
            } else if (input.equals("n") || input.equals("no")) {
                return false;
            }

            printError("Please enter 'y' or 'n'");
        }
    }

    @SuppressWarnings("unchecked")
    public <T extends Enum<T>> T getEnumInput(String prompt, Class<T> enumClass) {
        T[] constants = enumClass.getEnumConstants();
        String[] options = Arrays.stream(constants)
                .map(Enum::toString)
                .toArray(String[]::new);

        int choice = getChoice(options);
        return constants[choice - 1];
    }

    public <T extends Enum<T>> T getEnumInput(String prompt, String[] options) {
        while (true) {
            String input = getInput(prompt + " (" + String.join("/", options) + ")")
                    .trim().toUpperCase();

            for (String option : options) {
                if (option.equalsIgnoreCase(input)) {
                    return (T) Enum.valueOf(Enum.class, option.toUpperCase());
                }
            }

            printError("Invalid option. Please choose from: " + String.join(", ", options));
        }
    }

    public void pause() {
        System.out.print("\nPress Enter to continue...");
        scanner.nextLine();
    }

    public void close() {
        scanner.close();
    }
}