package HealthIssue;
import User.*;

import java.util.Set;

public class UnderWeight extends HealthCondition {
    private static Set<String> underweightDiscouraged = Set.of(
            "Bitter Gourd (Korola)", "Ridge Gourd (Jhinga)",
            "Pointed Gourd (Potol)", "Star Fruit (Kamranga)"
    );
    public UnderWeight() {
        super(underweightDiscouraged);
    }
    @Override
    public void adjustNutritionalNeeds(User user) {
        user.setMacros(50, 25, 25);
        user.setSaltAndSugar(35, 200);
    }

    @Override
    public void addDiscouragedFood(User user) {
        for(String it:underweightDiscouraged){
            user.getDiscouragedFoods().add(it);
        }
    }


}