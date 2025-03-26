package User;

import Activity.*;
import Goal.*;
import HealthIssue.*;
import Enum.*;

import java.util.List;

public class User {
    private String name;
    private int age;
    private double weight; // in kg
    private double height; // in cm
    private Gender gender;
    private ActivityLevel activityLevel;
    private List<HealthCondition> healthConditions;
    private Goal goal;
    private double dailyCalorieRequirement;
    private double proteinRequirement; // in grams
    private double carbRequirement; // in grams
    private double fatRequirement; // in grams
    private double waterRequirement; // in liters
    private double weeklySaltIntake; // in grams
    private double weeklySugarIntake; // in grams

    public User(String name, int age, double weight, double height, Gender gender, ActivityLevel activityLevel,List<HealthCondition>healthConditions, Goal goal) {
        this.name = name;
        this.age = age;
        this.weight = weight;
        this.height = height;
        this.gender = gender;
        this.activityLevel = activityLevel;
        this.healthConditions = healthConditions;
        this.goal = goal;
        calculateDailyRequirements();
    }

    private void calculateWaterRequirement() {
        this.waterRequirement = weight * 0.03; // 30 mL per kg body weight
    }

    private void calculateDailyRequirements() {
        double bmr = (gender== Gender.Male)
                ? 10 * weight + 6.25 * height - 5 * age + 5
                : 10 * weight + 6.25 * height - 5 * age - 161;

        this.dailyCalorieRequirement = activityLevel.calculateTDEE(bmr);
        for(HealthCondition healthCondition : healthConditions) {
            healthCondition.adjustNutritionalNeeds(this);
        }
        goal.adjustCaloricIntake(this);
        calculateWaterRequirement();
    }

    public void setMacros(double carbPercent, double proteinPercent, double fatPercent) {
        this.carbRequirement = (dailyCalorieRequirement * (carbPercent / 100)) / 4;
        this.proteinRequirement = (dailyCalorieRequirement * (proteinPercent / 100)) / 4;
        this.fatRequirement = (dailyCalorieRequirement * (fatPercent / 100)) / 9;
    }

    public void setSaltAndSugar(double salt, double sugar) {
        this.weeklySaltIntake = salt;
        this.weeklySugarIntake = sugar;
    }

    public void setDailyCalorieRequirement(double dailyCalorieRequirement) {
        this.dailyCalorieRequirement = dailyCalorieRequirement;
    }

    public double getDailyCalorieRequirement() {
        return this.dailyCalorieRequirement;
    }

    public void setGoal(Goal goal) {
        this.goal = goal;
        calculateDailyRequirements();
    }
    public Double getProteinRequirement() {
        return this.proteinRequirement;
    }
    public Double getCarbRequirement() {
        return this.carbRequirement;
    }
    public Double getFatRequirement(){
        return this.fatRequirement;
    }
    public Double getWaterRequirement() {
        return this.waterRequirement;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append("+------------------------+----------------------------+\n");
        sb.append("| Attribute              | Value                      |\n");
        sb.append("+------------------------+----------------------------+\n");
        sb.append(String.format("| Name                   | %-25s |\n", name));
        sb.append(String.format("| Age                    | %-25d |\n", age));
        sb.append(String.format("| Weight                 | %-22.2f kg |\n", weight));
        sb.append(String.format("| Height                 | %-22.2f cm |\n", height));
        sb.append(String.format("| Gender                 | %-25s |\n", gender));
        sb.append(String.format("| Activity Level         | %-25s |\n", activityLevel.getClass().getSimpleName()));
        sb.append(String.format("| Goal                   | %-25s |\n", goal.getClass().getSimpleName()));
        sb.append(String.format("| Daily Calorie Req.     | %-20.2f kcal |\n", dailyCalorieRequirement));
        sb.append(String.format("| Protein Requirement    | %-23.2f g |\n", proteinRequirement));
        sb.append(String.format("| Carb Requirement       | %-23.2f g |\n", carbRequirement));
        sb.append(String.format("| Fat Requirement        | %-23.2f g |\n", fatRequirement));
        sb.append(String.format("| Water Requirement      | %-23.2f L |\n", waterRequirement));
        sb.append(String.format("| Weekly Salt Intake     | %-23.2f g |\n", weeklySaltIntake));
        sb.append(String.format("| Weekly Sugar Intake    | %-23.2f g |\n", weeklySugarIntake));

        // Display Health Conditions (if more than one health condition exists)
        for (HealthCondition healthCondition : healthConditions) {
            sb.append(String.format("| Health Condition       | %-25s |\n", healthCondition.getClass().getSimpleName()));
        }

        sb.append("+------------------------+----------------------------+\n");

        return sb.toString();
    }


}
