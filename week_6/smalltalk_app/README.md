# Ride Sharing Application in Smalltalk

This application demonstrates a simple Ride Sharing system implemented in Smalltalk.

## Prerequisites

To run this application, you need a Smalltalk environment. Recommended option is using [Pharo](https://pharo.org/download)

## Running the Application

### Using Pharo

1. Download and install Pharo from https://pharo.org/download
2. Launch Pharo
3. Load the files in this order:
   - Open the Playground (Ctrl+O+W)
   - For each file, use:
   ```smalltalk
   FileStream fileIn: '/path/to/Ride.st'.
   FileStream fileIn: '/path/to/StandardRide.st'.
   FileStream fileIn: '/path/to/PremiumRide.st'.
   FileStream fileIn: '/path/to/Driver.st'.
   FileStream fileIn: '/path/to/Rider.st'.
   ```
4. Run the demo by executing:
   ```smalltalk
   FileStream fileIn: '/path/to/RideSharingDemo.st'.
   ```
5. View the results in the Transcript window (Ctrl+O+T)