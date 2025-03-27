package Factory;
import Goal.*;

public class GoalFactory {
    public static Goal create(String input) {
        return switch (input.toUpperCase()) {
            case "LOSEFAT" -> new LoseFat();
            case "MAINTAIN" -> new MaintainWeight();
            case "GAINWEIGHT" -> new GainWeight();
            case "GAINMUSCLE" -> new GainMuscle();
            default -> new MaintainWeight();
        };
    }
}
