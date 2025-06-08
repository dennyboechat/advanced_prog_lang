Ride subclass: #PremiumRide
    instanceVariableNames: 'luxuryFee'
    classVariableNames: ''
    package: 'RideSharing'!

!PremiumRide methodsFor: 'initialization'!
initialize
    super initialize.
    baseFareRate := 2.0.  "Premium ride costs $2.00 per mile"
    luxuryFee := 5.0.     "Additional $5 luxury fee"
! !

!PremiumRide methodsFor: 'operations'!
fare
    "Premium fare calculation with luxury fee"
    ^ (super fare) + luxuryFee
! !
