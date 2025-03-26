package Activity;

public class LowActivity extends ActivityLevel {
    @Override
    public double calculateTDEE(double bmr) {
        return bmr * 1.2;
    }
}