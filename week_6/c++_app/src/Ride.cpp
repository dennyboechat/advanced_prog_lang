#include "Ride.h"
#include <iostream>

// Constructor implementation
Ride::Ride(const std::string& id, const std::string& pickup, const std::string& dropoff, double dist)
    : rideID(id), pickupLocation(pickup), dropoffLocation(dropoff), distance(dist), baseFare(2.0) {}

// Getter implementations
std::string Ride::getRideID() const {
    return rideID;
}

std::string Ride::getPickupLocation() const {
    return pickupLocation;
}

std::string Ride::getDropoffLocation() const {
    return dropoffLocation;
}

double Ride::getDistance() const {
    return distance;
}

// Base fare calculation
double Ride::fare() const {
    return baseFare + (distance * 1.5); // Base fare + $1.50 per mile
}

// Display ride details
void Ride::rideDetails() const {
    std::cout << "Ride ID: " << rideID << std::endl;
    std::cout << "Pickup: " << pickupLocation << std::endl;
    std::cout << "Dropoff: " << dropoffLocation << std::endl;
    std::cout << "Distance: " << distance << " miles" << std::endl;
    std::cout << "Fare: $" << fare() << std::endl;
}
