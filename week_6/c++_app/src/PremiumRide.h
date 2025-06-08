#ifndef PREMIUM_RIDE_H
#define PREMIUM_RIDE_H

#include "Ride.h"

class PremiumRide : public Ride {
private:
    double premiumMultiplier; // Premium fare multiplier
    
public:
    PremiumRide(const std::string& id, const std::string& pickup, const std::string& dropoff, double dist);
    
    double fare() const override;
    
    void rideDetails() const override;
};

#endif
