class MaintainWeight extends Goal {
    @Override
    public void adjustCaloricIntake(User user) {
        user.setDailyCalorieRequirement(user.getDailyCalorieRequirement()); // Maintain Current Calories
        user.setMacros(50, 25, 25);
    }
}