"RideSharingDemo.st - A demonstration of the Ride Sharing application"

"Create some riders"
rider1 := Rider new.
rider1 name: 'Alice Smith'.

rider2 := Rider new.
rider2 name: 'Bob Johnson'.

"Create some drivers"
driver1 := Driver new.
driver1 name: 'Carlos Rodriguez'.
driver1 rating: 4.8.

driver2 := Driver new.
driver2 name: 'Diana Chen'.
driver2 rating: 4.9.

"Create rides of different types (demonstrating inheritance)"
standardRide1 := StandardRide new.
standardRide1 pickupLocation: 'Main St & 1st Ave'.
standardRide1 dropoffLocation: 'Oak St & 10th Ave'.
standardRide1 distance: 5.2.

standardRide2 := StandardRide new.
standardRide2 pickupLocation: 'Pine St & 7th Ave'.
standardRide2 dropoffLocation: 'Elm St & 15th Ave'.
standardRide2 distance: 3.7.

premiumRide1 := PremiumRide new.
premiumRide1 pickupLocation: 'Central Plaza'.
premiumRide1 dropoffLocation: 'Airport Terminal B'.
premiumRide1 distance: 12.5.

premiumRide2 := PremiumRide new.
premiumRide2 pickupLocation: 'Grand Hotel'.
premiumRide2 dropoffLocation: 'Convention Center'.
premiumRide2 distance: 2.3.

"Demonstrate polymorphism by storing rides of different types in a collection"
allRides := OrderedCollection new.
allRides add: standardRide1; add: standardRide2; add: premiumRide1; add: premiumRide2.

"Polymorphically calculate fares for all rides"
Transcript show: 'Demonstrating Polymorphism with Ride Types'; cr.
allRides doWithIndex: [ :ride :index |
    Transcript show: 'Ride ', index asString, ' (', ride class name, ')'; cr.
    Transcript show: ride rideDetails; cr; cr.
].

"Calculate total fares using polymorphism"
totalFares := allRides inject: 0 into: [ :sum :ride | sum + ride fare ].
Transcript show: 'Total fares across all ride types: $', totalFares asString; cr; cr.

"Demonstrate encapsulation with the Driver class"
Transcript show: 'Demonstrating Encapsulation with Driver Class'; cr.
driver1 addRide: standardRide1.
driver1 addRide: premiumRide1.
driver2 addRide: standardRide2.
driver2 addRide: premiumRide2.

"Driver info shows data without exposing the underlying collection"
Transcript show: driver1 getDriverInfo; cr; cr.
Transcript show: driver2 getDriverInfo; cr; cr.

"Demonstrate Rider interaction"
Transcript show: 'Demonstrating Rider Functionality'; cr.
rider1 requestRide: standardRide1.
rider1 requestRide: premiumRide2.
rider2 requestRide: standardRide2.
rider2 requestRide: premiumRide1.

Transcript show: rider1 getRiderInfo; cr; cr.
Transcript show: rider2 getRiderInfo; cr; cr.

"Final demonstration of polymorphism - calculate total earnings for all drivers"
drivers := OrderedCollection new.
drivers add: driver1; add: driver2.
totalEarnings := drivers inject: 0 into: [ :sum :driver | sum + driver totalEarnings ].
Transcript show: 'Total earnings across all drivers: $', totalEarnings asString; cr.
