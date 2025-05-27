# Employee Shift Scheduler

A Java application for managing employee shift schedules for a company that operates 7 days a week with morning, afternoon, and evening shifts.

## Features

- Collects employee names and shift preferences
- Assigns shifts based on employee preferences
- Ensures scheduling rules are followed:
  - No employee works more than one shift per day
  - Maximum 5 work days per week per employee
  - At least 2 employees per shift per day
- Resolves scheduling conflicts automatically
- Displays the final weekly schedule

## Requirements

- Java 11 or higher

## How to Build and Run

### Using the provided script

1. Navigate to the project directory in your terminal
2. Make the script executable if it's not already:
   ```zsh
   chmod +x run.sh
   ```
3. Run the script:
   ```zsh
   ./run.sh
   ```

### Manual compilation and execution

```zsh
# Compile the Java files
mkdir -p target/classes
javac -d target/classes src/main/java/shiftscheduler/*.java

# Run the application
cd target/classes
java shiftscheduler.ShiftSchedulerApp
```