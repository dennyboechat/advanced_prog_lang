package shiftscheduler;

import java.util.Scanner;

/**
 * Main application class for the Employee Shift Scheduler
 */
public class ShiftSchedulerApp {
    public static void main(String[] args) {
        System.out.println("Employee Shift Scheduler");
        System.out.println("=======================");
        
        try (Scanner scanner = new Scanner(System.in)) {
            ShiftScheduler scheduler = new ShiftScheduler();
            scheduler.run();
        }
    }
}
