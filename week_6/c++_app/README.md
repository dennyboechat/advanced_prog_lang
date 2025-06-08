# Ride Sharing Application

A C++ application demonstrating Object-Oriented Programming principles (encapsulation, inheritance, and polymorphism) through a ride sharing system implementation.

## Features

- **Ride Class Hierarchy**: Base `Ride` class with specialized `StandardRide` and `PremiumRide` subclasses
- **Driver Management**: Track drivers, their ratings, and assigned rides
- **Rider Management**: Handle ride requests and ride history for riders
- **Polymorphic Behavior**: Demonstrate runtime polymorphism with different ride types

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

### Option 2: Running the executable directly

```bash
./bin/ride_sharing_app
```