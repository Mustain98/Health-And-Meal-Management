package HealthIssue;
import User.*;
public class Diabetes extends HealthCondition {
    @Override
    public void adjustNutritionalNeeds(User user) {
        user.setMacros(35, 30, 35);
        user.setSaltAndSugar(35, 100);
    }
}