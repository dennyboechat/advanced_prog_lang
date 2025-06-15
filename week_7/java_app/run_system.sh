#!/bin/zsh

# Script to compile and run the Data Processing System

echo "Removing old class files..."
rm -f *.class src/*.class 2>/dev/null

echo "Compiling Java files..."
javac -d . src/DataProcessingSystem.java src/Task.java src/Worker.java src/ResultManager.java Main.java

if [ $? -ne 0 ]; then
  echo "Compilation failed. Please fix the errors and try again."
  exit 1
fi

echo
echo "Running the Data Processing System..."
java Main

echo 
echo "Done!"
