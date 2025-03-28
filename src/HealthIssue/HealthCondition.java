package HealthIssue;
import User.*;

import java.util.Set;

public abstract class HealthCondition {
    Set<String> underweightDiscouraged = Set.of();
    public abstract void adjustNutritionalNeeds(User user);
    public abstract void addDiscouragedFood(User user);
}