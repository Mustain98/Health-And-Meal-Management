public class Main {
    public static void main(String[] args) {
        ActivityLevel moderate = new ModerateActivity();
        HealthCondition diabetes = new Diabetes();
        Goal loseFat = new LoseFat();

        User user1 = new User("John", 30, 75, 175, Gender.Male, moderate, diabetes, loseFat);
        System.out.println(user1);

        ActivityLevel highActivity = new HighActivity();
        HealthCondition obesity = new Obesity();
        Goal gainMuscle = new GainMuscle();

        User user2 = new User("Alice", 28, 90, 165, Gender.Female, highActivity, obesity, gainMuscle);
        System.out.println(user2);
    }
}
