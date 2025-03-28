package HealthIssue;
import User.*;

import java.util.Set;

public class HeartDisease extends HealthCondition {
    Set<String> heartConditionDiscouraged = Set.of(
            "Duck", "Beef", "Goat Meat", "Prawns",
            "Ghee", "Coconut Oil", "Mustard Oil", "Coconut (Narkel)",
            "Muri (Puffed Rice)"
    );
    @Override
    public void adjustNutritionalNeeds(User user) {
        user.setMacros(45, 25, 30);
        user.setSaltAndSugar(18, 100);
    }

    @Override
    public void addDiscouragedFood(User user) {
        for(String it:heartConditionDiscouraged){
            user.getDiscouragedFoods().add(it);
        }
    }
}