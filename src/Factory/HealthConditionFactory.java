package Factory;

import HealthIssue.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class HealthConditionFactory {
    private static final Scanner scanner = new Scanner(System.in);

    public static List<HealthCondition> create() {
        List<HealthCondition> conditions = new ArrayList<>();

        System.out.println("Enter health conditions (comma separated): ");
        System.out.println("Options: DIABETES, HEART_DISEASE, HYPERTENSION, OBESITY, NONE");
        String[] inputs = scanner.nextLine().toUpperCase().split(",");

        for (String input : inputs) {
            String trimmedInput = input.trim();
            HealthCondition condition = createSingle(trimmedInput);
            conditions.add(condition);
        }

        if (conditions.isEmpty()) {
            conditions.add(new NoHealthIssues());
        }
        return conditions;
    }

    public static HealthCondition createSingle(String conditionName) {
        return switch (conditionName.toUpperCase()) {
            case "DIABETES" -> new Diabetes();
            case "HEARTDISEASE", "HEART_DISEASE" -> new HeartDisease();
            case "HYPERTENSION" -> new Hypertension();
            case "OBESITY" -> new Obesity();
            default -> new NoHealthIssues();
        };
    }
}