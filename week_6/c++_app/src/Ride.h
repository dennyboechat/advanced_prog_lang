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
    // Constructor
    Ride(const std::string& id, const std::string& pickup, const std::string& dropoff, double dist);
    
    // Virtual destructor for proper cleanup in derived classes
    virtual ~Ride() = default;
    
    // Getters
    std::string getRideID() const;
    std::string getPickupLocation() const;
    std::string getDropoffLocation() const;
    double getDistance() const;
    
    // Virtual methods to be overridden by derived classes
    virtual double fare() const; // Calculate fare based on distance
    virtual void rideDetails() const; // Display ride information
};

#endif // RIDE_H
