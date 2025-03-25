class Underweight extends HealthCondition {
    @Override
    public void adjustNutritionalNeeds(User user) {
        user.setMacros(50, 25, 25);
        user.setSaltAndSugar(35, 200);
    }
}