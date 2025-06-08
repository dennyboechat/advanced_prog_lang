#include "PremiumRide.h"
#include <iostream>

// Constructor implementation
PremiumRide::PremiumRide(const std::string& id, const std::string& pickup, const std::string& dropoff, double dist)
    : Ride(id, pickup, dropoff, dist), premiumMultiplier(2.0) {
    // Premium ride has a higher base fare
    baseFare = 5.0; // Premium base fare
}

// Override fare calculation for premium rides
double PremiumRide::fare() const {
    // Premium rate: higher base fare + double the per-mile rate
    return baseFare + (distance * 1.5 * premiumMultiplier);
}

// Override ride details to include premium features
void PremiumRide::rideDetails() const {
    std::cout << "=== PREMIUM RIDE DETAILS ===" << std::endl;
    Ride::rideDetails(); // Call base class method to show common details
    std::cout << "Ride Type: Premium" << std::endl;
    std::cout << "Premium Features: Luxury vehicle, complimentary water, and Wi-Fi" << std::endl;
}
