#ifndef DRIVER_H
#define DRIVER_H

#include <string>
#include <vector>
#include "Ride.h"

class Driver {
private:
    std::string driverID;
    std::string name;
    double rating;
    std::vector<Ride*> assignedRides; // List of completed rides (using pointers to allow polymorphism)

public:
    // Constructor
    Driver(const std::string& id, const std::string& driverName);
    
    // Destructor - not responsible for deleting Ride pointers as they are managed elsewhere
    ~Driver() = default;
    
    // Getters
    std::string getDriverID() const;
    std::string getName() const;
    double getRating() const;
    
    // Methods
    void addRide(Ride* ride); // Add a completed ride
    void updateRating(double newRating); // Update driver rating
    void getDriverInfo() const; // Display driver information
    double calculateTotalEarnings() const; // Calculate total earnings from all rides
    void displayAssignedRides() const; // Display all assigned rides
};

#endif // DRIVER_H
