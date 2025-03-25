class GainMuscle extends Goal {
    @Override
    public void adjustCaloricIntake(User user) {
        user.setDailyCalorieRequirement(user.getDailyCalorieRequirement() * 1.15); // 15% Calorie Surplus
        user.setMacros(40, 35, 25);
    }
}