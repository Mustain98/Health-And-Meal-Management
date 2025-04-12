package HealthIssue;
import User.*;

import java.util.Set;

public abstract class HealthCondition {
    protected Set<String> discouragedFoods;

    public HealthCondition(Set<String> discouragedFoods) {
        this.discouragedFoods = discouragedFoods;
    }

    public abstract void adjustNutritionalNeeds(User user);

    public void addDiscouragedFood(User user) {
        for (String food : discouragedFoods) {
            user.addDiscouragedFood(food);
        }
    }

    public void removeDiscouragedFood(User user) {
        for (String food : discouragedFoods) {
            user.removeDiscouragedFood(food);
        }
    }
}
