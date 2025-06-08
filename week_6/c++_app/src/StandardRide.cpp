#include "StandardRide.h"
#include <iostream>

// Constructor implementation
StandardRide::StandardRide(const std::string& id, const std::string& pickup, const std::string& dropoff, double dist)
    : Ride(id, pickup, dropoff, dist) {
    // Standard ride uses the base fare from Ride
}

// Override fare calculation for standard rides
double StandardRide::fare() const {
    return Ride::fare(); // Standard rate: base fare + $1.50 per mile
}

// Override ride details to include ride type
void StandardRide::rideDetails() const {
    std::cout << "=== STANDARD RIDE DETAILS ===" << std::endl;
    Ride::rideDetails(); // Call base class method to show common details
    std::cout << "Ride Type: Standard" << std::endl;
}
