#ifndef PREMIUM_RIDE_H
#define PREMIUM_RIDE_H

#include "Ride.h"

class PremiumRide : public Ride {
private:
    double premiumMultiplier; // Premium fare multiplier
    
public:
    // Constructor
    PremiumRide(const std::string& id, const std::string& pickup, const std::string& dropoff, double dist);
    
    // Override fare calculation for premium ride
    double fare() const override;
    
    // Override ride details to show it's a premium ride
    void rideDetails() const override;
};

#endif // PREMIUM_RIDE_H
