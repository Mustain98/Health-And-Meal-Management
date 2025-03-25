class HeartDisease extends HealthCondition {
    @Override
    public void adjustNutritionalNeeds(User user) {
        user.setMacros(45, 25, 30);
        user.setSaltAndSugar(18, 100);
    }
}