package shiftscheduler;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.HashSet;

/**
 * Represents an employee
 */
public class Employee {
    private String name;
    private Map<Day, Shift> preferences;
    private Map<Day, Shift> assignments;
    private Set<Day> workDays;

    public Employee(String name) {
        this.name = name;
        this.preferences = new HashMap<>();
        this.assignments = new HashMap<>();
        this.workDays = new HashSet<>();
    }

    public void addPreference(Day day, Shift shift) {
        preferences.put(day, shift);
    }

    public boolean assignShift(Day day, Shift shift) {
        // Check if employee already has 5 work days assigned
        if (workDays.size() >= 5 && !workDays.contains(day)) {
            return false;
        }

        // Check if employee already has a shift on this day
        if (assignments.containsKey(day)) {
            return false;
        }

        assignments.put(day, shift);
        workDays.add(day);
        return true;
    }

    public boolean hasMaxWorkDays() {
        return workDays.size() >= 5;
    }

    public boolean isWorkingOnDay(Day day) {
        return assignments.containsKey(day);
    }

    public Shift getPreferredShift(Day day) {
        return preferences.get(day);
    }

    public Shift getAssignedShift(Day day) {
        return assignments.get(day);
    }

    public String getName() {
        return name;
    }

    public int getWorkDaysCount() {
        return workDays.size();
    }

    public Set<Day> getWorkDays() {
        return workDays;
    }
}
