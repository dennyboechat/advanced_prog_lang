#include "Driver.h"
#include <iostream>

Driver::Driver(const std::string& id, const std::string& driverName)
    : driverID(id), name(driverName), rating(5.0) {} // Default rating is 5.0

std::string Driver::getDriverID() const {
    return driverID;
}

std::string Driver::getName() const {
    return name;
}

double Driver::getRating() const {
    return rating;
}

void Driver::addRide(Ride* ride) {
    assignedRides.push_back(ride);
}

void Driver::updateRating(double newRating) {
    if (newRating >= 1.0 && newRating <= 5.0) {
        rating = newRating;
    } else {
        std::cout << "Rating must be between 1.0 and 5.0" << std::endl;
    }
}

void Driver::getDriverInfo() const {
    std::cout << "=== DRIVER INFORMATION ===" << std::endl;
    std::cout << "Driver ID: " << driverID << std::endl;
    std::cout << "Name: " << name << std::endl;
    std::cout << "Rating: " << rating << " / 5.0" << std::endl;
    std::cout << "Total Rides: " << assignedRides.size() << std::endl;
    std::cout << "Total Earnings: $" << calculateTotalEarnings() << std::endl;
}

double Driver::calculateTotalEarnings() const {
    double totalEarnings = 0.0;
    for (const auto& ride : assignedRides) {
        totalEarnings += ride->fare();
    }
    return totalEarnings;
}

void Driver::displayAssignedRides() const {
    if (assignedRides.empty()) {
        std::cout << "No rides assigned to this driver." << std::endl;
        return;
    }
    
    std::cout << "=== RIDES ASSIGNED TO " << name << " ===" << std::endl;
    for (size_t i = 0; i < assignedRides.size(); ++i) {
        std::cout << "\nRide #" << (i + 1) << ":" << std::endl;
        assignedRides[i]->rideDetails();
    }
}
