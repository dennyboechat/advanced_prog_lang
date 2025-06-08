Object subclass: #Driver
    instanceVariableNames: 'driverID name rating assignedRides'
    classVariableNames: ''
    package: 'RideSharing'!

!Driver methodsFor: 'initialization'!
initialize
    super initialize.
    driverID := UUID new asString.
    rating := 5.0.  "Default rating"
    assignedRides := OrderedCollection new.  "Private collection of rides"
! !

!Driver methodsFor: 'accessing'!
driverID
    ^ driverID
!

name
    ^ name
!

name: aString
    name := aString
!

rating
    ^ rating
!

rating: aNumber
    "Ensure rating is between 1.0 and 5.0"
    rating := (aNumber max: 1.0) min: 5.0
! !

!Driver methodsFor: 'operations'!
addRide: aRide
    "Add a ride to the driver's assigned rides - demonstrates encapsulation"
    assignedRides add: aRide.
    ^ aRide
!

completedRidesCount
    "Return the number of completed rides"
    ^ assignedRides size
!

totalEarnings
    "Calculate total earnings from all rides"
    ^ assignedRides inject: 0 into: [ :sum :ride | sum + ride fare ]
!

getDriverInfo
    ^ String streamContents: [ :s |
        s nextPutAll: 'Driver ID: ', driverID;
          cr; nextPutAll: 'Name: ', name asString;
          cr; nextPutAll: 'Rating: ', rating asString, ' stars';
          cr; nextPutAll: 'Completed Rides: ', self completedRidesCount asString;
          cr; nextPutAll: 'Total Earnings: $', self totalEarnings asString
      ]
!

viewAssignedRides
    "Provides access to ride details without exposing the collection itself"
    ^ assignedRides collect: [ :ride | ride rideDetails ]
! !
