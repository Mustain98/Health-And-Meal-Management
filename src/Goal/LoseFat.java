package Goal;
import User.*;
public class LoseFat extends Goal {
    @Override
    public void adjustCaloricIntake(User user) {
        user.setDailyCalorieRequirement(user.getDailyCalorieRequirement() * 0.8); // 20% Calorie Deficit
        user.setMacros(40, 40, 20);
    }
}