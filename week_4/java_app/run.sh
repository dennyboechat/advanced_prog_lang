#!/bin/sh

# Check if Java is installed
if ! command -v java &> /dev/null; then
    echo "Java is not installed. Please install Java to run this application."
    exit 1
fi

# Create output directory
mkdir -p target/classes

# Compile the Java files
echo "Compiling Java files..."
javac -d target/classes src/main/java/shiftscheduler/*.java

# Check if compilation was successful
if [ $? -eq 0 ]; then
    echo "Compilation successful. Running the application..."
    cd target/classes
    java shiftscheduler.ShiftSchedulerApp
else
    echo "Compilation failed. Please check the error messages above."
fi
