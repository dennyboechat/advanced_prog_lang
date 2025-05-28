package main

import (
	"bufio"
	"fmt"
	"math/rand"
	"os"
	"strconv"
	"strings"
)

type Day int
type ShiftType string

const (
	Monday Day = iota
	Tuesday
	Wednesday
	Thursday
	Friday
	Saturday
	Sunday
)

const (
	Morning   ShiftType = "Morning"
	Afternoon ShiftType = "Afternoon"
	Evening   ShiftType = "Evening"
	None      ShiftType = "None"
)

func (d Day) String() string {
	days := []string{"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"}
	return days[d]
}

func AllDays() []Day {
	return []Day{Monday, Tuesday, Wednesday, Thursday, Friday, Saturday, Sunday}
}

func ShiftFromChar(char string) ShiftType {
	switch strings.ToUpper(char) {
	case "M":
		return Morning
	case "A":
		return Afternoon
	case "E":
		return Evening
	default:
		return None
	}
}

type Employee struct {
	Name        string
	Preferences map[Day][]ShiftType
	Assignments map[Day]ShiftType
	WorkDays    int
}

type Schedule map[Day]map[ShiftType][]*Employee

func init() {
}

func main() {
	fmt.Println("Employee Shift Scheduler")
	fmt.Println("------------------------")

	employees := getEmployeeInput()

	if len(employees) == 0 {
		fmt.Println("No employees entered. Exiting program.")
		return
	}

	fmt.Println("\nGenerating schedule...")

	schedule := createSchedule(employees)

	printSchedule(schedule)
}

func getEmployeeInput() []*Employee {
	scanner := bufio.NewScanner(os.Stdin)

	var numEmployees int
	for {
		fmt.Println("\nEntering employee data and shift preferences")
		fmt.Print("Enter the number of employees: ")
		scanner.Scan()
		input := strings.TrimSpace(scanner.Text())

		if input == "" {
			fmt.Println("Input cannot be empty. Please enter a number.")
			continue
		}

		var err error
		numEmployees, err = strconv.Atoi(input)
		if err != nil || numEmployees <= 0 {
			fmt.Println("Please enter a valid positive number.")
			continue
		}
		break
	}

	employees := make([]*Employee, 0, numEmployees)

	for i := 0; i < numEmployees; i++ {
		fmt.Printf("\nEnter name for employee %d: ", i+1)
		scanner.Scan()
		name := strings.TrimSpace(scanner.Text())

		if name == "" {
			fmt.Println("Name cannot be empty. Using default name.")
			name = fmt.Sprintf("Employee%d", i+1)
		}

		preferences := make(map[Day][]ShiftType)

		fmt.Println("\nEnter shift preferences. MORNING, AFTERNOON, EVENING (M/A/E or N for none)")

		for _, day := range AllDays() {
			for {
				fmt.Printf("%s: ", day)
				scanner.Scan()
				input := strings.ToUpper(strings.TrimSpace(scanner.Text()))

				// Default to None if empty
				if input == "" {
					fmt.Println("No input provided, marking as Not Available for this day.")
					preferences[day] = []ShiftType{}
					break
				}

				// Validate input contains only valid characters
				valid := true
				for _, char := range input {
					if char != 'M' && char != 'A' && char != 'E' && char != 'N' {
						valid = false
						break
					}
				}

				if !valid {
					fmt.Println("Invalid input. Please use only M, A, E, or N.")
					continue
				}

				// If N is entered, employee is not available
				if strings.Contains(input, "N") {
					preferences[day] = []ShiftType{}
					break
				}

				// Process each character as a shift preference
				dayPrefs := []ShiftType{}
				for _, char := range input {
					shift := ShiftFromChar(string(char))
					if shift != None {
						// Check for duplicates
						isDuplicate := false
						for _, existing := range dayPrefs {
							if existing == shift {
								isDuplicate = true
								break
							}
						}
						if !isDuplicate {
							dayPrefs = append(dayPrefs, shift)
						}
					}
				}

				preferences[day] = dayPrefs
				break
			}
		}

		employees = append(employees, &Employee{
			Name:        name,
			Preferences: preferences,
			Assignments: make(map[Day]ShiftType),
			WorkDays:    0,
		})
	}

	return employees
}

func createSchedule(employees []*Employee) Schedule {
	schedule := make(Schedule)
	for _, day := range AllDays() {
		schedule[day] = make(map[ShiftType][]*Employee)
		for _, shift := range []ShiftType{Morning, Afternoon, Evening} {
			schedule[day][shift] = []*Employee{}
		}
	}

	// First pass: Assign employees to preferred shifts
	for _, emp := range employees {
		for day, prefs := range emp.Preferences {
			// Skip if employee already assigned to this day
			if _, assigned := emp.Assignments[day]; assigned {
				continue
			}

			for _, pref := range prefs {
				if pref == None {
					continue
				}

				if emp.WorkDays < 5 && !isAssignedToDay(emp, day) {
					emp.Assignments[day] = pref
					emp.WorkDays++
					schedule[day][pref] = append(schedule[day][pref], emp)
					break
				}
			}
		}
	}

	// Ensure minimum staffing
	for _, day := range AllDays() {
		for _, shift := range []ShiftType{Morning, Afternoon, Evening} {
			// If fewer than 2 employees, find more
			for len(schedule[day][shift]) < 2 {
				assigned := false
				for _, emp := range shuffleEmployees(employees) {

					if emp.WorkDays < 5 && !isAssignedToDay(emp, day) {
						emp.Assignments[day] = shift
						emp.WorkDays++
						schedule[day][shift] = append(schedule[day][shift], emp)
						fmt.Printf("Note: %s was randomly assigned to %s shift on %s due to staffing needs\n",
							emp.Name, shift, day)
						assigned = true
						break
					}
				}

				if !assigned {
					fmt.Printf("Warning: Could not find enough employees for %s shift on %s\n",
						shift, day)
					break
				}
			}
		}
	}

	// Resolve conflicts for employees who weren't assigned their preferences
	for _, emp := range employees {
		for day, prefs := range emp.Preferences {
			if len(prefs) == 0 || isAssignedToDay(emp, day) {
				continue
			}

			// Try to assign to another shift on the same day
			if emp.WorkDays < 5 {
				for _, shift := range []ShiftType{Morning, Afternoon, Evening} {
					// Check if this is a non-preferred shift
					isPreferred := false
					for _, pref := range prefs {
						if pref == shift {
							isPreferred = true
							break
						}
					}

					// If not preferred, try to assign
					if !isPreferred {
						emp.Assignments[day] = shift
						emp.WorkDays++
						schedule[day][shift] = append(schedule[day][shift], emp)
						fmt.Printf("Note: %s was assigned to non-preferred %s shift on %s due to conflicts\n",
							emp.Name, shift, day)
						break
					}
				}
			}
		}
	}

	return schedule
}

func isAssignedToDay(emp *Employee, day Day) bool {
	_, assigned := emp.Assignments[day]
	return assigned
}

func shuffleEmployees(employees []*Employee) []*Employee {
	shuffled := make([]*Employee, len(employees))
	copy(shuffled, employees)

	rand.Shuffle(len(shuffled), func(i, j int) {
		shuffled[i], shuffled[j] = shuffled[j], shuffled[i]
	})

	return shuffled
}

func printSchedule(schedule Schedule) {
	fmt.Println("\n===== WEEKLY SHIFT SCHEDULE =====")

	for _, day := range AllDays() {
		fmt.Printf("\n%s:\n", day)

		for _, shift := range []ShiftType{Morning, Afternoon, Evening} {
			fmt.Printf("  %s: ", shift)

			employees := schedule[day][shift]
			if len(employees) == 0 {
				fmt.Println("No employees assigned")
			} else {
				for i, emp := range employees {
					if i > 0 {
						fmt.Print(", ")
					}
					fmt.Print(emp.Name)
				}
				fmt.Println()
			}
		}
	}
}
