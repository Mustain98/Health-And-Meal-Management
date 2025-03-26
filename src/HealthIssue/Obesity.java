package HealthIssue;
import User.*;
public class Obesity extends HealthCondition {
    @Override
    public void adjustNutritionalNeeds(User user) {
        user.setMacros(40, 35, 25);
        user.setSaltAndSugar(25, 80);
    }
}