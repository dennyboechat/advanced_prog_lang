#include "PremiumRide.h"
#include <iostream>

PremiumRide::PremiumRide(const std::string& id, const std::string& pickup, const std::string& dropoff, double dist)
    : Ride(id, pickup, dropoff, dist), premiumMultiplier(2.0) {
    baseFare = 5.0; // Premium base fare
}

double PremiumRide::fare() const {
    return baseFare + (distance * 1.5 * premiumMultiplier);
}

void PremiumRide::rideDetails() const {
    std::cout << "=== PREMIUM RIDE DETAILS ===" << std::endl;
    Ride::rideDetails(); // Call base class method to show common details
    std::cout << "Ride Type: Premium" << std::endl;
    std::cout << "Premium Features: Luxury vehicle, complimentary water, and Wi-Fi" << std::endl;
}
