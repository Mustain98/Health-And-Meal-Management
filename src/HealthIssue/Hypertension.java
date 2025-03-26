package HealthIssue;
import User.*;
public class Hypertension extends HealthCondition {
    @Override
    public void adjustNutritionalNeeds(User user) {
        user.setMacros(40, 30, 30);
        user.setSaltAndSugar(20, 120);
    }
}