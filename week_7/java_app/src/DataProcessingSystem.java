import java.util.concurrent.*;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * DataProcessingSystem - Main class that sets up and coordinates the parallel data processing.
 */
public class DataProcessingSystem {
    private final int numWorkers;
    private final BlockingQueue<Task> taskQueue;
    private final ResultManager resultManager;
    private final ExecutorService executorService;
    private final CountDownLatch completionLatch;
    
    private final AtomicInteger totalTasksSubmitted = new AtomicInteger(0);
    
    /**
     * Creates a new DataProcessingSystem.
     * 
     * @param numWorkers number of worker threads
     * @param outputFilePath path to the output file
     */
    public DataProcessingSystem(int numWorkers, String outputFilePath) {
        this.numWorkers = numWorkers;
        this.taskQueue = new LinkedBlockingQueue<>();
        this.resultManager = new ResultManager(outputFilePath);
        this.executorService = Executors.newFixedThreadPool(numWorkers);
        this.completionLatch = new CountDownLatch(numWorkers);
    }
    
    /**
     * Submits a task for processing.
     * 
     * @param task the task to process
     */
    public void submitTask(Task task) {
        taskQueue.add(task);
        totalTasksSubmitted.incrementAndGet();
    }
    
    /**
     * Starts the worker threads.
     */
    public void startProcessing() {
        System.out.println("Starting " + numWorkers + " worker threads");
        
        for (int i = 0; i < numWorkers; i++) {
            String workerId = "Worker-" + (i + 1);
            Worker worker = new Worker(workerId, taskQueue, resultManager, completionLatch);
            executorService.submit(worker);
        }
    }
    
    /**
     * Signals that no more tasks will be submitted and waits for completion.
     * 
     * @param timeoutSeconds maximum time to wait
     * @return true if all workers completed, false if timeout occurred
     * @throws InterruptedException if interrupted while waiting
     */
    public boolean waitForCompletion(int timeoutSeconds) throws InterruptedException {
        System.out.println("No more tasks. Sending termination signal to workers...");
        
        // Add poison pills to signal workers to terminate
        for (int i = 0; i < numWorkers; i++) {
            taskQueue.add(Task.POISON_PILL);
        }
        
        System.out.println("Waiting for workers to complete...");
        boolean completed = completionLatch.await(timeoutSeconds, TimeUnit.SECONDS);
        
        executorService.shutdown();
        try {
            if (!executorService.awaitTermination(5, TimeUnit.SECONDS)) {
                System.err.println("Executor did not terminate in time, forcing shutdown");
                executorService.shutdownNow();
            }
        } catch (InterruptedException e) {
            executorService.shutdownNow();
            Thread.currentThread().interrupt();
        }
        
        resultManager.finalizeResults();
        
        return completed;
    }
    
    /**
     * Main method to demonstrate the data processing system.
     */
    public static void main(String[] args) {
        try {
            int numWorkers = 4;
            int numTasks = 20;
            String outputFile = "processing_results.txt";
            
            System.out.println("=== Parallel Data Processing System ===");
            System.out.println("Workers: " + numWorkers + ", Tasks: " + numTasks);
            
            DataProcessingSystem system = new DataProcessingSystem(numWorkers, outputFile);
            
            System.out.println("Generating and submitting " + numTasks + " tasks");
            Random random = new Random();
            for (int i = 1; i <= numTasks; i++) {
                int complexity = random.nextInt(3) + 1;  // Complexity 1-3
                Task task = new Task(i, "Data-" + i, complexity);
                system.submitTask(task);
            }
            
            system.startProcessing();
            
            boolean allCompleted = system.waitForCompletion(30);  // 30 second timeout
            
            if (!allCompleted) {
                System.out.println("Processing did not complete within timeout period");
            } else {
                System.out.println("All processing completed successfully");
            }
            
            System.out.println("Results written to: " + outputFile);
            
        } catch (Exception e) {
            System.err.println("Error in main: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
