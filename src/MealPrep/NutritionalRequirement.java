package MealPrep;

public class NutritionalRequirement {
    private final double target;
    private double current;
    private final String name;
    private final double tolerance;
    private double min;
    private double max;
    private double accumulated = 0;

    public NutritionalRequirement(String name, double target, double tolerance) {
        this.name = name;
        this.target = target;
        this.tolerance = tolerance;
        this.current = 0;
        this.min = target * (1 - tolerance);
        this.max = target * (1 + tolerance);
    }

    public void add(double amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("Amount cannot be negative");
        }
        this.current += amount;
        this.accumulated += amount;
    }

    public void subtract(double amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("Amount cannot be negative");
        }
        this.current = Math.max(0, this.current - amount);
    }

    public void reset() {
        this.current = 0;
    }

    public boolean isFulfilled() {
        return current >= min;
    }

    public boolean isExceeded() {
        return current > max;
    }

    public boolean isWithinRange() {
        return current >= min && current <= max;
    }

    public double getRemaining() {
        return Math.max(0, target - current);
    }

    public double getExcess() {
        return Math.max(0, current - target);
    }

    public double getCurrent() {
        return current;
    }

    public double getTarget() {
        return target;
    }

    public double getTolerance() {
        return tolerance;
    }

    public double getAccumulated() {
        return accumulated;
    }

    public String getProgress() {
        return String.format("%-8s: %6.1f/%-6.1f %3d%%",
                name, current, target, getPercentage());
    }

    public String getProgressWithBar() {
        int progress = (int) Math.min(100, getPercentage());
        int barLength = 20;
        int filled = progress * barLength / 100;

        String bar = "[" + "=".repeat(filled) +
                " ".repeat(barLength - filled) + "]";

        String status = isWithinRange() ? "✓" :
                isExceeded() ? "⚠" : "↑";

        return String.format("%s %-8s: %6.1f/%-6.1f %3d%% %s",
                status, name, current, target, progress, bar);
    }

    private int getPercentage() {
        return (int) Math.round((current / target) * 100);
    }

    public String getRangeString() {
        return String.format("%.1f-%.1f", min, max);
    }

    @Override
    public String toString() {
        return String.format("%s: %.1f/%.1f (%.1f-%.1f, tol: %.0f%%)",
                name, current, target, min, max, tolerance * 100);
    }
}