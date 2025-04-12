package CLI;
import Activity.ActivityLevel;
import Factory.HealthConditionFactory;
import Goal.Goal;
import HealthIssue.HealthCondition;
import MealPrep.*;
import User.*;
public class ProfileUpdateService {
    private ConsoleIO consoleIO;
    private User user;
    private UserdbManager userdb;

    public ProfileUpdateService(ConsoleIO consoleIO, User user) {
        this.consoleIO = consoleIO;
        this.user = user;
        this.userdb = new UserdbManager();
    }

    public void show() {
        boolean exit = false;
        while (!exit) {
            int choice = consoleIO.getChoice(
                    "Update Name",
                    "Update age",
                    "Update Weight",
                    "Update Goal",
                    "update ActivityLevel",
                    "Add Health Condition",
                    "Remove Health Condition",
                    "Add Food You are Allergic to",
                    "Back to Main Menu"
            );

            switch (choice) {
                case 1:
                    updateName();
                    break;
                case 2:
                    updateAge();
                    break;
                case 3:
                    updateWeight();
                    break;
                case 4:
                    updateGoal();
                    break;
                case 5:
                    updateActivity();
                    break;
                case 6:
                    addHealthCondition();
                    break;
                case 7:
                    removeHealthCondition();
                    break;
                case 8:
                    addFoodAllergy();
                    break;
                case 9:
                    exit = true;
                    break;
                default:
                    consoleIO.printMessage("Invalid choice. Please try again.");
            }
        }
    }

    public void updateName() {
        String newName = consoleIO.getInput("Enter New Name");
        user.setName(newName);
        userdb.updateName(user.getUserID(), newName);
        consoleIO.printMessage("Name updated successfully!");
    }

    public void updateAge() {
        int newAge = consoleIO.getIntInput("Enter new Age");
        user.setAge(newAge);
        userdb.updateAge(user.getUserID(), newAge);
        consoleIO.printMessage("Age updated successfully!");
    }

    public void updateWeight() {
        double newWeight = consoleIO.getDoubleInput("Enter new Weight");
        user.setWeight(newWeight);
        userdb.UpdateWeight(user.getUserID(), newWeight);
        consoleIO.printMessage("Weight updated successfully!");
    }

    public void updateGoal() {
        Goal newGoal = consoleIO.getGoal();
        user.setGoal(newGoal);
        userdb.updateGoal(user.getUserID(), newGoal.getClass().getSimpleName());
        consoleIO.printMessage("Goal updated successfully!");
    }

    public void addHealthCondition() {
        HealthCondition healthCondition= consoleIO.getHealthCondition();
        user.addHealthCondition(healthCondition);
        userdb.addHealthCondition(user.getUserID(), healthCondition.getClass().getSimpleName());
        consoleIO.printMessage("Health condition added successfully!");
    }

    public void removeHealthCondition() {
        HealthCondition healthCondition= consoleIO.getHealthCondition();
        if (user.getHealthConditions().contains(healthCondition)) {
            user.removeHealthCondition(healthCondition);
            userdb.removeHealthCondition(user.getUserID(), healthCondition.getClass().getSimpleName());
            consoleIO.printMessage("Health condition removed successfully!");
        } else {
            consoleIO.printMessage("This health condition doesn't exist for the user.");
        }
    }

    public void addFoodAllergy() {
        String food = consoleIO.getInput("Enter food you're allergic to");

        user.addDiscouragedFood(food);
        userdb.addFoodAllergy(user.getUserID(), food);
        consoleIO.printMessage("Food allergy added successfully!");
    }
    private void updateActivity() {
        ActivityLevel activityLevel= consoleIO.getActivityLevel();
        user.setactivity(activityLevel);
        userdb.updateActivity(user.getUserID(),activityLevel.getClass().getSimpleName());
    }
}