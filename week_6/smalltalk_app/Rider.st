Object subclass: #Rider
    instanceVariableNames: 'riderID name requestedRides'
    classVariableNames: ''
    package: 'RideSharing'!

!Rider methodsFor: 'initialization'!
initialize
    super initialize.
    riderID := UUID new asString.
    requestedRides := OrderedCollection new.
! !

!Rider methodsFor: 'accessing'!
riderID
    ^ riderID
!

name
    ^ name
!

name: aString
    name := aString
! !

!Rider methodsFor: 'operations'!
requestRide: aRide
    "Add a ride to the rider's requested rides"
    requestedRides add: aRide.
    ^ aRide
!

viewRides
    "Returns a collection of ride details"
    ^ requestedRides collect: [ :ride | ride rideDetails ]
!

totalSpent
    "Calculate total amount spent on all rides"
    ^ requestedRides inject: 0 into: [ :sum :ride | sum + ride fare ]
!

getRiderInfo
    ^ String streamContents: [ :s |
        s nextPutAll: 'Rider ID: ', riderID;
          cr; nextPutAll: 'Name: ', name asString;
          cr; nextPutAll: 'Total Rides: ', requestedRides size asString;
          cr; nextPutAll: 'Total Spent: $', self totalSpent asString
      ]
! !
