package HealthIssue;
import User.*;

import java.util.Set;

public abstract class HealthCondition {
    Set<String> discouragedFoods = Set.of();
    public abstract void adjustNutritionalNeeds(User user);
    public abstract void addDiscouragedFood(User user);
    public  void removeDiscouragedFood(User user){
        for(String food: discouragedFoods){
            user.removeDiscouragedFood(food);
        }
    }
}