#ifndef STANDARD_RIDE_H
#define STANDARD_RIDE_H

#include "Ride.h"

class StandardRide : public Ride {
public:
    // Constructor inherits from Ride
    StandardRide(const std::string& id, const std::string& pickup, const std::string& dropoff, double dist);
    
    // Override fare calculation for standard ride
    double fare() const override;
    
    // Override ride details to show it's a standard ride
    void rideDetails() const override;
};

#endif // STANDARD_RIDE_H
