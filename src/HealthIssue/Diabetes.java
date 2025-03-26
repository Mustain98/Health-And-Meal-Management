package HealthIssue;
import User.*;

import java.util.Set;

public class Diabetes extends HealthCondition {
    Set<String> diabetesDiscouraged = Set.of(
            "Basmati Rice", "Luchi Flour", "Muri (Puffed Rice)",
            "Chira (Flattened Rice)", "Suji (Semolina)", "Mango (Aam)",
            "Litchi (Lichu)", "Banana (Kola)", "Jackfruit (Kathal)",
            "Wood Apple (Kathbel)", "Betel Nut (Supari)"
    );
    @Override
    public void adjustNutritionalNeeds(User user) {
        user.setMacros(35, 30, 35);
        user.setSaltAndSugar(35, 100);
    }

    @Override
    public void addDiscouragedFood(User user) {
        for(String it:diabetesDiscouraged){
            user.getDiscouragedFoods().add(it);
        }
    }
}