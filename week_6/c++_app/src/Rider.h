#ifndef RIDER_H
#define RIDER_H

#include <string>
#include <vector>
#include "Ride.h"

class Rider {
private:
    std::string riderID;
    std::string name;
    std::vector<Ride*> requestedRides;

public:
    Rider(const std::string& id, const std::string& riderName);
    
    ~Rider() = default;
    
    std::string getRiderID() const;
    std::string getName() const;
    
    void requestRide(Ride* ride);
    void viewRides() const;
    double calculateTotalSpent() const;
};

#endif
