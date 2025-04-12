package HealthIssue;
import User.*;

import java.util.Set;

public class Hypertension extends HealthCondition {
    private static Set<String> hypertensionDiscouraged = Set.of(
            "Muri (Puffed Rice)", "Ghee (Desi)", "Salted",
            "Processed", "Pickled"
    );
    public Hypertension() {
        super(hypertensionDiscouraged);
    }
    @Override
    public void adjustNutritionalNeeds(User user) {
        user.setMacros(40, 30, 30);
        user.setSaltAndSugar(20, 120);
    }

    @Override
    public void addDiscouragedFood(User user) {
        for (String it: hypertensionDiscouraged) {
            user.getDiscouragedFoods().add(it);
        }
    }

}