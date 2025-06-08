Ride subclass: #StandardRide
    instanceVariableNames: ''
    classVariableNames: ''
    package: 'RideSharing'!

!StandardRide methodsFor: 'initialization'!
initialize
    super initialize.
    baseFareRate := 1.2.  "Standard ride costs $1.20 per mile"
! !

!StandardRide methodsFor: 'operations'!
fare
    "Standard fare calculation with base rate"
    ^ super fare
! !
