package HealthIssue;
import User.*;

import java.util.Set;

public class KidneyDisease extends HealthCondition {
    Set<String> kidneyConditionDiscouraged = Set.of(
            "Rui Fish", "Katla Fish", "Taro Root (Kochu)",
            "Drumstick (Sajna)", "Amaranth Leaves (Data Shak)",
            "Banana (Kola)", "Wood Apple (Kathbel)", "Jackfruit (Kathal)",
            "Sesame Seeds (Til)", "Niger Seeds (Kalojeera)", "Poppy Seeds (Posto)"
    );
    @Override
    public void adjustNutritionalNeeds(User user) {
        user.setMacros(50, 15, 35);
        user.setSaltAndSugar(15, 110);
    }

    @Override
    public void addDiscouragedFood(User user) {
        for(String it: kidneyConditionDiscouraged) {
            user.getDiscouragedFoods().add(it);
        }
    }
}

