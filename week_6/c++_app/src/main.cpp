#include <iostream>
#include <vector>
#include <memory>
#include "Ride.h"
#include "StandardRide.h"
#include "PremiumRide.h"
#include "Driver.h"
#include "Rider.h"

int main() {
    std::cout << "==== RIDE SHARING APPLICATION DEMO ====" << std::endl;
    
    // Create some rides of different types (using smart pointers for memory management)
    std::cout << "\nCreating rides..." << std::endl;
    
    std::vector<std::unique_ptr<Ride>> rides;
    
    // Create standard rides
    rides.push_back(std::make_unique<StandardRide>("S001", "123 Main St", "456 Oak Ave", 3.5));
    rides.push_back(std::make_unique<StandardRide>("S002", "789 Pine Rd", "101 Elm Blvd", 5.2));
    
    // Create premium rides
    rides.push_back(std::make_unique<PremiumRide>("P001", "202 Maple Dr", "303 Cedar Ln", 2.8));
    rides.push_back(std::make_unique<PremiumRide>("P002", "404 Birch St", "505 Walnut Ave", 7.1));
    
    // DEMONSTRATING POLYMORPHISM
    // Display details of all rides (polymorphic behavior with rideDetails())
    std::cout << "\n=== DEMONSTRATING POLYMORPHISM WITH RIDE DETAILS ===" << std::endl;
    for (const auto& ride : rides) {
        ride->rideDetails();
        std::cout << std::endl;
    }
    
    // DEMONSTRATING ENCAPSULATION
    // Create drivers
    std::cout << "\n=== CREATING DRIVERS ===" << std::endl;
    Driver driver1("D001", "John Smith");
    Driver driver2("D002", "Alice Johnson");
    
    // Driver info is encapsulated, and we access it through methods
    driver1.getDriverInfo();
    std::cout << std::endl;
    
    // Assign rides to drivers (note: passing raw pointers from unique_ptr using .get())
    std::cout << "\n=== ASSIGNING RIDES TO DRIVERS ===" << std::endl;
    driver1.addRide(rides[0].get());
    driver1.addRide(rides[2].get());
    driver2.addRide(rides[1].get());
    driver2.addRide(rides[3].get());
    
    // Display assigned rides
    driver1.displayAssignedRides();
    std::cout << std::endl;
    driver2.displayAssignedRides();
    
    // DEMONSTRATING INHERITANCE AND POLYMORPHISM FURTHER
    // Create riders
    std::cout << "\n=== CREATING RIDERS ===" << std::endl;
    Rider rider1("R001", "Bob Williams");
    Rider rider2("R002", "Carol Davis");
    
    // Request rides
    rider1.requestRide(rides[0].get());
    rider1.requestRide(rides[3].get());
    rider2.requestRide(rides[1].get());
    rider2.requestRide(rides[2].get());
    
    // View ride history
    std::cout << "\n=== VIEWING RIDE HISTORY ===" << std::endl;
    rider1.viewRides();
    std::cout << "\nTotal spent by " << rider1.getName() << ": $" << rider1.calculateTotalSpent() << std::endl;
    
    std::cout << std::endl;
    rider2.viewRides();
    std::cout << "\nTotal spent by " << rider2.getName() << ": $" << rider2.calculateTotalSpent() << std::endl;
    
    // Update driver ratings
    std::cout << "\n=== UPDATING DRIVER RATINGS ===" << std::endl;
    std::cout << "Updating " << driver1.getName() << "'s rating from " << driver1.getRating();
    driver1.updateRating(4.8);
    std::cout << " to " << driver1.getRating() << std::endl;
    
    std::cout << "Updating " << driver2.getName() << "'s rating from " << driver2.getRating();
    driver2.updateRating(4.5);
    std::cout << " to " << driver2.getRating() << std::endl;
    
    // Display final driver information with earnings
    std::cout << "\n=== FINAL DRIVER INFORMATION ===" << std::endl;
    driver1.getDriverInfo();
    std::cout << std::endl;
    driver2.getDriverInfo();
    
    std::cout << "\n==== END OF DEMONSTRATION ====" << std::endl;
    
    return 0;
}
