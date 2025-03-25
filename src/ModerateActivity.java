class ModerateActivity extends ActivityLevel {
    @Override
    public double calculateTDEE(double bmr) {
        return bmr * 1.55;
    }
}