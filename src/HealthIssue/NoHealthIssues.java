package HealthIssue;
import User.*;

import java.util.Set;

public class NoHealthIssues extends HealthCondition {
    private static Set<String> noHealthIssues;
    public NoHealthIssues() {
        super(noHealthIssues);
    }
    @Override
    public void adjustNutritionalNeeds(User user) {
        user.setMacros(50, 25, 25);
        user.setSaltAndSugar(30, 150);
    }

    @Override
    public void addDiscouragedFood(User user){}
}