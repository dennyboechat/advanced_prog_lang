#ifndef DRIVER_H
#define DRIVER_H

#include <string>
#include <vector>
#include "Ride.h"

class Driver {
private:
    std::string driverID;
    std::string name;
    double rating;
    std::vector<Ride*> assignedRides;

public:
    Driver(const std::string& id, const std::string& driverName);
    
    ~Driver() = default;
    
    std::string getDriverID() const;
    std::string getName() const;
    double getRating() const;
    
    void addRide(Ride* ride);
    void updateRating(double newRating);
    void getDriverInfo() const;
    double calculateTotalEarnings() const;
    void displayAssignedRides() const;
};

#endif
