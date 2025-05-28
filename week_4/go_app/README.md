# Employee Shift Scheduler

A Go application for managing employee schedules at a company. The company operates 7 days a week and allows employees to choose shifts in the morning, afternoon, or evening.

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

- Go 1.16 or higher

## How to Run the Application
```bash
cd /path/to/go_app
go run main.go
```