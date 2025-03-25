class HighActivity extends ActivityLevel {
    @Override
    public double calculateTDEE(double bmr) {
        return bmr * 1.9;
    }
}