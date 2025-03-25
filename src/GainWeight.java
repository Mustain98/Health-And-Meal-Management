class GainWeight extends Goal {
    @Override
    public void adjustCaloricIntake(User user) {
        user.setDailyCalorieRequirement(user.getDailyCalorieRequirement() * 1.1); // 10% Calorie Surplus
        user.setMacros(55, 20, 25);
    }
}