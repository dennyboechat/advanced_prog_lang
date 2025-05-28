package shiftscheduler;

import java.util.*;

/**
 * Main scheduler class that handles employee input and scheduling logic
 */
public class ShiftScheduler {
    private List<Employee> employees;
    private Schedule schedule;
    private Scanner scanner;

    public ShiftScheduler() {
        employees = new ArrayList<>();
        schedule = new Schedule();
        scanner = new Scanner(System.in);
    }

    public void run() {
        collectEmployeeData();
        createSchedule();
        resolveConflicts();
        ensureMinimumStaffing();
        schedule.displaySchedule();
    }

    private void collectEmployeeData() {
        System.out.println("\nEntering employee data and shift preferences");
        System.out.print("Enter the number of employees: ");
        int numEmployees = getValidIntInput(1, 100);

        for (int i = 0; i < numEmployees; i++) {
            System.out.print("\nEnter name for employee " + (i + 1) + ": ");
            String name = scanner.nextLine().trim();
            Employee employee = new Employee(name);

            System.out.println("\nEntering shift preferences for " + name + ":");
            System.out.println("Shift options: MORNING, AFTERNOON, EVENING");

            for (Day day : Day.getAllDays()) {
                System.out.print("Preferred shift for " + day.getDisplayName() + " (M/A/E or N for none): ");
                String input = scanner.nextLine().trim().toUpperCase();

                if (!input.equals("N")) {
                    Shift shift = parseShiftInput(input);
                    if (shift != null) {
                        employee.addPreference(day, shift);
                    }
                }
            }

            employees.add(employee);
        }
    }

    private Shift parseShiftInput(String input) {
        switch (input) {
            case "M":
                return Shift.MORNING;
            case "A":
                return Shift.AFTERNOON;
            case "E":
                return Shift.EVENING;
            default:
                System.out.println("Invalid input. Skipping preference for this day.");
                return null;
        }
    }

    private void createSchedule() {
        System.out.println("\nCreating initial schedule based on preferences...");

        // First pass: Assign employees to their preferred shifts
        for (Employee employee : employees) {
            for (Day day : Day.getAllDays()) {
                Shift preferredShift = employee.getPreferredShift(day);

                // If employee has preference for this day, try to assign them
                if (preferredShift != null) {
                    schedule.addEmployeeToShift(employee, day, preferredShift);
                }
            }
        }
    }

    private void resolveConflicts() {
        System.out.println("Resolving scheduling conflicts...");

        for (Employee employee : employees) {
            if (employee.hasMaxWorkDays()) {
                continue;
            }

            for (Day day : Day.getAllDays()) {
                Shift preferredShift = employee.getPreferredShift(day);

                // If employee has preference for this day but wasn't assigned
                if (preferredShift != null && !employee.isWorkingOnDay(day)) {
                    // Try to find alternative slot
                    Map.Entry<Day, Shift> alternativeSlot = schedule.findNextAvailableSlot(day, preferredShift);

                    if (alternativeSlot != null && !employee.hasMaxWorkDays() &&
                            !employee.isWorkingOnDay(alternativeSlot.getKey())) {
                        schedule.addEmployeeToShift(employee, alternativeSlot.getKey(), alternativeSlot.getValue());
                        System.out.println(employee.getName() + "'s preference for " +
                                day.getDisplayName() + " " + preferredShift.getDisplayName() +
                                " was reassigned to " + alternativeSlot.getKey().getDisplayName() +
                                " " + alternativeSlot.getValue().getDisplayName());
                    }
                }
            }
        }
    }

    private void ensureMinimumStaffing() {
        System.out.println("Ensuring minimum staffing levels...");

        for (Day day : Day.getAllDays()) {
            for (Shift shift : Shift.getAllShifts()) {
                // Check if this shift has less than 2 employees
                while (!schedule.hasEnoughEmployees(day, shift)) {
                    Employee selectedEmployee = findAvailableEmployee(day);

                    if (selectedEmployee == null) {
                        System.out.println("WARNING: Unable to find enough employees for " +
                                day.getDisplayName() + " " + shift.getDisplayName() + " shift!");
                        break;
                    }

                    schedule.addEmployeeToShift(selectedEmployee, day, shift);
                    System.out.println("Assigned " + selectedEmployee.getName() +
                            " to fill staffing requirement on " + day.getDisplayName() +
                            " " + shift.getDisplayName());
                }
            }
        }
    }

    private Employee findAvailableEmployee(Day day) {
        List<Employee> availableEmployees = new ArrayList<>();
        List<Employee> randomSelectedEmployees = new ArrayList<>(employees);
        Collections.shuffle(randomSelectedEmployees);

        for (Employee employee : randomSelectedEmployees) {
            if (!employee.isWorkingOnDay(day) && !employee.hasMaxWorkDays()) {
                availableEmployees.add(employee);
            }
        }

        if (availableEmployees.isEmpty()) {
            return null;
        }

        // Sort by number of work days (ascending) to balance workload
        availableEmployees.sort(Comparator.comparingInt(Employee::getWorkDaysCount));

        // Return the employee with the fewest work days
        return availableEmployees.get(0);
    }

    private int getValidIntInput(int min, int max) {
        while (true) {
            try {
                String input = scanner.nextLine();
                int value = Integer.parseInt(input);
                if (value >= min && value <= max) {
                    return value;
                } else {
                    System.out.print("Please enter a number between " + min + " and " + max + ": ");
                }
            } catch (NumberFormatException e) {
                System.out.print("Invalid input. Please enter a number: ");
            }
        }
    }
}
