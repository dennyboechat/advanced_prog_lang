# Ride Sharing Application

A C++ application demonstrating Object-Oriented Programming principles (encapsulation, inheritance, and polymorphism) through a ride sharing system implementation.

## Features

- **Ride Class Hierarchy**: Base `Ride` class with specialized `StandardRide` and `PremiumRide` subclasses
- **Driver Management**: Track drivers, their ratings, and assigned rides
- **Rider Management**: Handle ride requests and ride history for riders
- **Polymorphic Behavior**: Demonstrate runtime polymorphism with different ride types

## Project Structure

```
├── bin/               # Compiled binaries
├── obj/               # Object files
├── src/               # Source code
│   ├── Driver.cpp     # Driver class implementation
│   ├── Driver.h       # Driver class declaration
│   ├── main.cpp       # Main application file
│   ├── PremiumRide.cpp # Premium ride implementation
│   ├── PremiumRide.h  # Premium ride declaration
│   ├── Ride.cpp       # Base ride class implementation
│   ├── Ride.h         # Base ride class declaration
│   ├── Rider.cpp      # Rider class implementation
│   ├── Rider.h        # Rider class declaration
│   ├── StandardRide.cpp # Standard ride implementation
│   └── StandardRide.h # Standard ride declaration
└── Makefile           # Build system
```

## Requirements

- C++17 compatible compiler (g++ recommended)
- make

## Building the Application

To build the application, run:

```bash
make all
```

This will compile the source code and create an executable in the `bin` directory.

## Running the Application

You can run the application in one of two ways:

### Option 1: Using the provided Makefile target

```bash
make run
```

This will build the application if needed and then execute it.

### Option 2: Running the executable directly

```bash
./bin/ride_sharing_app
```

## Cleaning the Project

To clean the compiled objects and binaries:

```bash
make clean
```

## OOP Principles Demonstrated

### Encapsulation
- Private data members with public interfaces
- Protected access for inheritance

### Inheritance
- `StandardRide` and `PremiumRide` inherit from `Ride`
- Override behavior while reusing base functionality

### Polymorphism
- Virtual methods for dynamic dispatch
- Storing different ride types in a single collection
- Runtime behavior determination

## Sample Output

The application demonstrates:
- Creating drivers and riders
- Requesting different types of rides
- Calculating fares differently based on ride type
- Managing ride assignments
- Displaying ride details and history
