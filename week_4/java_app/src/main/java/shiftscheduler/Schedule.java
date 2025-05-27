package shiftscheduler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Represents the schedule for all shifts and days
 */
public class Schedule {
    private Map<Day, Map<Shift, List<Employee>>> schedule;
    
    public Schedule() {
        schedule = new HashMap<>();
        
        for (Day day : Day.getAllDays()) {
            schedule.put(day, new HashMap<>());
            
            for (Shift shift : Shift.getAllShifts()) {
                schedule.get(day).put(shift, new ArrayList<>());
            }
        }
    }
    
    public boolean addEmployeeToShift(Employee employee, Day day, Shift shift) {
        if (employee.isWorkingOnDay(day)) {
            return false;
        }
        
        boolean assigned = employee.assignShift(day, shift);
        if (assigned) {
            schedule.get(day).get(shift).add(employee);
        }
        return assigned;
    }
    
    public int getEmployeeCountForShift(Day day, Shift shift) {
        return schedule.get(day).get(shift).size();
    }
    
    public List<Employee> getEmployeesForShift(Day day, Shift shift) {
        return schedule.get(day).get(shift);
    }
    
    public boolean hasEnoughEmployees(Day day, Shift shift) {
        return getEmployeeCountForShift(day, shift) >= 2;
    }
    
    public Map.Entry<Day, Shift> findNextAvailableSlot(Day day, Shift originalShift) {
        // Try other shifts on the same day first
        for (Shift shift : Shift.getAllShifts()) {
            if (shift != originalShift && getEmployeeCountForShift(day, shift) < 5) {
                return Map.entry(day, shift);
            }
        }
        
        // Try shifts on the next day
        Day[] allDays = Day.getAllDays();
        for (int i = 0; i < allDays.length; i++) {
            if (allDays[i] == day) {
                // Get the next day (wrap around to Monday if it's Sunday)
                Day nextDay = (i < allDays.length - 1) ? allDays[i + 1] : allDays[0];
                
                // Try all shifts on the next day
                for (Shift shift : Shift.getAllShifts()) {
                    if (getEmployeeCountForShift(nextDay, shift) < 5) {
                        return Map.entry(nextDay, shift);
                    }
                }
            }
        }
        
        return null;
    }
    
    public void displaySchedule() {
        System.out.println("\n=== WEEKLY SHIFT SCHEDULE ===\n");
        
        for (Day day : Day.getAllDays()) {
            System.out.println("=== " + day.getDisplayName() + " ===");
            
            for (Shift shift : Shift.getAllShifts()) {
                System.out.println(shift.getDisplayName() + " Shift:");
                
                List<Employee> employees = schedule.get(day).get(shift);
                if (employees.isEmpty()) {
                    System.out.println("  No employees assigned");
                } else {
                    for (Employee employee : employees) {
                        String preferredMarker = (employee.getPreferredShift(day) == shift) ? " (preferred)" : "";
                        System.out.println("  - " + employee.getName() + preferredMarker);
                    }
                }
                System.out.println();
            }
            System.out.println("---------------------------");
        }
    }
}
