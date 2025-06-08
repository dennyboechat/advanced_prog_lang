Object subclass: #Ride
    instanceVariableNames: 'rideID pickupLocation dropoffLocation distance baseFareRate'
    classVariableNames: ''
    package: 'RideSharing'!

!Ride methodsFor: 'initialization'!
initialize
    super initialize.
    rideID := UUID new asString.
    baseFareRate := 1.0.  "Default rate of $1 per mile"
! !

!Ride methodsFor: 'accessing'!
rideID
    ^ rideID
!

pickupLocation
    ^ pickupLocation
!

pickupLocation: aLocation
    pickupLocation := aLocation
!

dropoffLocation
    ^ dropoffLocation
!

dropoffLocation: aLocation
    dropoffLocation := aLocation
!

distance
    ^ distance
!

distance: aNumber
    distance := aNumber
! !

!Ride methodsFor: 'operations'!
fare
    "Calculate basic fare based on distance and rate"
    ^ (distance * baseFareRate) roundTo: 0.01
!

rideDetails
    ^ String streamContents: [ :s |
        s nextPutAll: 'Ride ID: ', rideID;
          cr; nextPutAll: 'Pickup: ', pickupLocation asString;
          cr; nextPutAll: 'Dropoff: ', dropoffLocation asString;
          cr; nextPutAll: 'Distance: ', distance asString, ' miles';
          cr; nextPutAll: 'Fare: $', self fare asString
      ]
! !
