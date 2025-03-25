class KidneyDisease extends HealthCondition {
    @Override
    public void adjustNutritionalNeeds(User user) {
        user.setMacros(50, 15, 35);
        user.setSaltAndSugar(15, 110);
    }
}

