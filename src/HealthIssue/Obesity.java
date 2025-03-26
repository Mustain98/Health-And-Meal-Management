package HealthIssue;
import User.*;

import java.util.Set;

public class Obesity extends HealthCondition {
    Set<String> obesityDiscouraged = Set.of(
            "Duck (Desi)", "Hilsa Fish", "Goat Meat", "Luchi Flour",
            "Ghee (Desi)", "Mustard Oil", "Coconut Oil", "Sesame Oil (Til)",
            "Niger Seed Oil (Kalojeera)", "Coconut (Narkel)"
    );
    @Override
    public void adjustNutritionalNeeds(User user) {
        user.setMacros(40, 35, 25);
        user.setSaltAndSugar(25, 80);
    }

    @Override
    public void addDiscouragedFood(User user) {
        for (String disco : obesityDiscouraged) {
            user.getDiscouragedFoods().add(disco);
        }
    }
}