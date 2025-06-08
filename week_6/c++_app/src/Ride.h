#ifndef RIDE_H
#define RIDE_H

#include <string>

class Ride {
protected:
    std::string rideID;
    std::string pickupLocation;
    std::string dropoffLocation;
    double distance; // in miles
    double baseFare;

public:
    Ride(const std::string& id, const std::string& pickup, const std::string& dropoff, double dist);
    
    virtual ~Ride() = default;
    
    std::string getRideID() const;
    std::string getPickupLocation() const;
    std::string getDropoffLocation() const;
    double getDistance() const;
    
    virtual double fare() const;
    virtual void rideDetails() const;
};

#endif
