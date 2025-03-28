package Factory;
import Activity.*;

public class ActivityLevelFactory {
    public static ActivityLevel create(String input) {
        return switch (input.toUpperCase()) {
            case "LOW" -> new LowActivity();
            case "MODERATE" -> new ModerateActivity();
            case "HIGH" -> new HighActivity();
            default -> new ModerateActivity();
        };
    }
}