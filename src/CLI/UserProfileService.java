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
        ActivityLevel activityLevel = console.getActivityLevel();
        Goal goal = console.getGoal();
        List<HealthCondition> healthConditions = HealthConditionFactory.create();
        return new User(username, age, weight, height, gender,
                activityLevel, healthConditions, goal);
    }

}