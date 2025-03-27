package CLI;

import User.*;
import Activity.*;
import Goal.*;
import HealthIssue.*;
import Enum.*;
import java.util.*;
import Factory.*;

public class UserProfileService {
    private final ConsoleIO console;
    private Scanner scanner=new Scanner(System.in);
    public UserProfileService(ConsoleIO console) {
        this.console = console;
    }

    public User createUser() {
        console.printHeader("CREATE USER PROFILE");

        String username = console.getInput("Username");
        int age = console.getIntInput("Age");
        double weight = console.getDoubleInput("Weight (kg)");
        double height = console.getDoubleInput("Height (cm)");
        Gender gender = console.getEnumInput("Gender", Gender.class);
        ActivityLevel activityLevel = getActivityLevel();
        Goal goal = getGoal();
        List<HealthCondition> healthConditions = getHealthConditions();
        return new User(username, age, weight, height, gender,
                activityLevel, healthConditions, goal);
    }

    private ActivityLevel getActivityLevel() {
        System.out.println("Enter Activity Level (Low/Moderate/High): ");
        String input = scanner.nextLine().trim();
        return ActivityLevelFactory.create(input);
    }
    private Goal getGoal() {
        System.out.println("Enter Goal (LoseFat/Maintain/GAINWEIGHT/GainMuscle): ");
        String input = scanner.nextLine().trim();
        return GoalFactory.create(input);
    }
    private List<HealthCondition> getHealthConditions() {
        return HealthConditionFactory.create();
    }
}