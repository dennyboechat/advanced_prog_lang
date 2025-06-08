#include "Rider.h"
#include <iostream>

Rider::Rider(const std::string& id, const std::string& riderName)
    : riderID(id), name(riderName) {}

std::string Rider::getRiderID() const {
    return riderID;
}

std::string Rider::getName() const {
    return name;
}

void Rider::requestRide(Ride* ride) {
    requestedRides.push_back(ride);
    std::cout << "Ride " << ride->getRideID() << " requested by " << name << std::endl;
}

void Rider::viewRides() const {
    if (requestedRides.empty()) {
        std::cout << "No rides requested by this rider." << std::endl;
        return;
    }
    
    std::cout << "=== RIDE HISTORY FOR " << name << " ===" << std::endl;
    for (size_t i = 0; i < requestedRides.size(); ++i) {
        std::cout << "\nRide #" << (i + 1) << ":" << std::endl;
        requestedRides[i]->rideDetails();
    }
}

double Rider::calculateTotalSpent() const {
    double totalSpent = 0.0;
    for (const auto& ride : requestedRides) {
        totalSpent += ride->fare();
    }
    return totalSpent;
}
