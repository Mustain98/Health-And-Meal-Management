class User {
    private String name;
    private int age;
    private double weight; // in kg
    private double height; // in cm
    private Gender gender;
    private ActivityLevel activityLevel;
    private HealthCondition healthCondition;
    private Goal goal;
    private double dailyCalorieRequirement;
    private double proteinRequirement; // in grams
    private double carbRequirement; // in grams
    private double fatRequirement; // in grams
    private double waterRequirement; // in liters
    private double weeklySaltIntake; // in grams
    private double weeklySugarIntake; // in grams

    public User(String name, int age, double weight, double height, Gender gender, ActivityLevel activityLevel, HealthCondition healthCondition, Goal goal) {
        this.name = name;
        this.age = age;
        this.weight = weight;
        this.height = height;
        this.gender = gender;
        this.activityLevel = activityLevel;
        this.healthCondition = healthCondition;
        this.goal = goal;
        calculateDailyRequirements();
    }

    private void calculateWaterRequirement() {
        this.waterRequirement = weight * 0.03; // 30 mL per kg body weight
    }

    private void calculateDailyRequirements() {
        double bmr = (gender==Gender.Male)
                ? 10 * weight + 6.25 * height - 5 * age + 5
                : 10 * weight + 6.25 * height - 5 * age - 161;

        this.dailyCalorieRequirement = activityLevel.calculateTDEE(bmr);
        healthCondition.adjustNutritionalNeeds(this);
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

    @Override
    public String toString() {
        return "User [name=" + name + ", age=" + age + ", weight=" + weight + " kg, height=" + height + " cm, gender=" + gender +
                ", activityLevel=" + activityLevel.getClass().getSimpleName() +
                ", healthCondition=" + healthCondition.getClass().getSimpleName() +
                ", goal=" + goal.getClass().getSimpleName() +
                ", dailyCalorieRequirement=" + dailyCalorieRequirement + " kcal, proteinRequirement=" + proteinRequirement + " g, carbRequirement=" + carbRequirement + " g, fatRequirement=" + fatRequirement + " g, waterRequirement=" + waterRequirement + " L, " +
                "weeklySaltIntake=" + weeklySaltIntake + " g, weeklySugarIntake=" + weeklySugarIntake + " g]";
    }
}
