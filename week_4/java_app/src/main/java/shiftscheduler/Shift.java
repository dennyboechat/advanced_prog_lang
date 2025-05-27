package shiftscheduler;

/**
 * Enumeration for shifts
 */
public enum Shift {
    MORNING("Morning"),
    AFTERNOON("Afternoon"),
    EVENING("Evening");

    private final String displayName;

    Shift(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }

    public static Shift[] getAllShifts() {
        return values();
    }
}
