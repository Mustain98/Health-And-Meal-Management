package User;

import Activity.*;
import Food.FoodItem;
import Goal.*;
import HealthIssue.*;
import Enum.*;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class User {
    private int userID;
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
    private Set<String>discouragedFoods;

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
        this.discouragedFoods=new HashSet<>();
        for(HealthCondition healthCondition : healthConditions) {
            healthCondition.addDiscouragedFood(this);
        }
    }

    public void addDiscouragedFood(String foodItem) {
        this.discouragedFoods.add(foodItem);
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
    public Set<String>getDiscouragedFoods() {
        return discouragedFoods;
    }
    public void setDiscouragedFoods(Set<String> discouragedFoods) {
        this.discouragedFoods.clear();
        if (discouragedFoods != null) {
            this.discouragedFoods.addAll(discouragedFoods);
        }
    }
    public void setWeight(double weight) {
        this.weight = weight;
        calculateDailyRequirements();
        calculateWaterRequirement();
    }
    public void setactivity(ActivityLevel activity) {
        this.activityLevel=activity;
        calculateDailyRequirements();
    }
    public List<HealthCondition> getHealthConditions() {
        return new ArrayList<>(healthConditions);
    }
    public void addHealthCondition(HealthCondition healthCondition) {
        this.healthConditions.add(healthCondition);
        healthCondition.addDiscouragedFood(this);
    }
    public void removeHealthCondition(HealthCondition healthCondition) {
        this.healthConditions.remove(healthCondition);
        healthCondition.removeDiscouragedFood(this);
    }
    public String getName(){
        return this.name;
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
        for (String df : discouragedFoods ) {
            sb.append(String.format("| Discouraged Food       | %-25s |\n",df));
        }

        sb.append("+------------------------+----------------------------+\n");

        return sb.toString();
    }

    public int getAge() {
        return age;
    }

    public Gender getGender() {
        return gender;
    }

    public double getWeight() {
        return weight;
    }

    public double getHeight() {
        return height;
    }

    public ActivityLevel getActivityLevel() {
        return activityLevel;
    }

    public Goal getGoal() {
        return goal;
    }
    public void setUserID(int userID) {
        this.userID = userID;
    }
    public int getUserID() {
        return userID;
    }

    public void setName(String newName) {
        this.name=newName;
    }

    public void setAge(int newAge) {
        this.age=newAge;
    }
    public void removeDiscouragedFood(String food){
        this.discouragedFoods.remove(food);
    }
}
