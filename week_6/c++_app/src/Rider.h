#ifndef RIDER_H
#define RIDER_H

#include <string>
#include <vector>
#include "Ride.h"

class Rider {
private:
    std::string riderID;
    std::string name;
    std::vector<Ride*> requestedRides; // List of rides requested by this rider

public:
    // Constructor
    Rider(const std::string& id, const std::string& riderName);
    
    // Destructor - not responsible for deleting Ride pointers as they are managed elsewhere
    ~Rider() = default;
    
    // Getters
    std::string getRiderID() const;
    std::string getName() const;
    
    // Methods
    void requestRide(Ride* ride); // Add a ride to the requested list
    void viewRides() const; // Display ride history
    double calculateTotalSpent() const; // Calculate total money spent on rides
};

#endif // RIDER_H
