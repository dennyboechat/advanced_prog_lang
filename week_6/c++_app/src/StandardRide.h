#ifndef STANDARD_RIDE_H
#define STANDARD_RIDE_H

#include "Ride.h"

class StandardRide : public Ride {
public:
    StandardRide(const std::string& id, const std::string& pickup, const std::string& dropoff, double dist);
    
    double fare() const override;
    
    void rideDetails() const override;
};

#endif
