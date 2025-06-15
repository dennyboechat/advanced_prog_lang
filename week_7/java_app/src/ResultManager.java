package src;

import java.io.*;
import java.nio.file.*;
import java.util.concurrent.locks.ReentrantLock;
import java.util.Date;
import java.text.SimpleDateFormat;

/**
 * ResultManager - Handles saving results to a shared resource (file).
 * Provides proper synchronization to avoid concurrent access issues.
 */
public class ResultManager {
    private final String outputFilePath;
    private final ReentrantLock fileLock = new ReentrantLock();
    private final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss.SSS");
    
    /**
     * Creates a new ResultManager that writes to the specified file.
     * 
     * @param outputFilePath path to the output file
     */
    public ResultManager(String outputFilePath) {
        this.outputFilePath = outputFilePath;
        
        // Initialize output file (create or truncate if exists)
        try {
            Files.write(Paths.get(outputFilePath), new byte[0],
                    StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
            
            // Write header
            writeResult("=== Data Processing Results ===");
            writeResult("Started at " + dateFormat.format(new Date()));
            writeResult("============================");
        } catch (IOException e) {
            System.err.println("Error initializing output file: " + e.getMessage());
        }
    }
    
    /**
     * Writes a result to the output file with proper synchronization.
     * 
     * @param result the result to write
     */
    public void writeResult(String result) {
        // Use a lock to ensure only one thread writes at a time
        fileLock.lock();
        try {
            // Write to file with timestamp
            try (BufferedWriter writer = Files.newBufferedWriter(
                    Paths.get(outputFilePath), StandardOpenOption.APPEND)) {
                writer.write(dateFormat.format(new Date()) + " - " + result);
                writer.newLine();
            } catch (IOException e) {
                System.err.println("Error writing to output file: " + e.getMessage());
            }
        } finally {
            fileLock.unlock();
        }
    }
    
    /**
     * Completes the result file by writing a footer.
     */
    public void finalizeResults() {
        writeResult("============================");
        writeResult("Processing completed at " + dateFormat.format(new Date()));
    }
    
    /**
     * Gets the output file path.
     * 
     * @return the path to the output file
     */
    public String getOutputFilePath() {
        return outputFilePath;
    }
}
