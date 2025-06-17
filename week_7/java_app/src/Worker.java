import java.util.concurrent.*;
import java.io.StringWriter;
import java.io.PrintWriter;

/**
 * Worker - A worker thread that processes tasks from a shared queue.
 */
public class Worker implements Runnable {
    private final String workerId;
    private final BlockingQueue<Task> taskQueue;
    private final ResultManager resultManager;
    private final CountDownLatch completionLatch;

    private int tasksProcessed = 0;
    private int errorCount = 0;

    /**
     * Creates a new worker.
     * 
     * @param workerId        identifier for this worker
     * @param taskQueue       shared queue to take tasks from
     * @param resultManager   shared resource for saving results
     * @param completionLatch latch to signal when this worker is done
     */
    public Worker(String workerId, BlockingQueue<Task> taskQueue,
            ResultManager resultManager, CountDownLatch completionLatch) {
        this.workerId = workerId;
        this.taskQueue = taskQueue;
        this.resultManager = resultManager;
        this.completionLatch = completionLatch;
    }

    @Override
    public void run() {
        try {
            System.out.println(workerId + " started");
            resultManager.writeResult(workerId + " started - Thread: " + Thread.currentThread().getName());

            while (true) {
                Task task = taskQueue.take();

                if (task == Task.POISON_PILL) {
                    System.out.println(workerId + " received termination signal");
                    break;
                }

                try {
                    System.out.println(workerId + " processing task: " + task.getId());

                    String result = processTask(task);
                    tasksProcessed++;

                    resultManager.writeResult(workerId + " completed task " + task.getId() + ": " + result);

                } catch (Exception e) {
                    errorCount++;
                    System.err.println(workerId + " error processing task " + task.getId() + ": " + e.getMessage());

                    StringWriter sw = new StringWriter();
                    e.printStackTrace(new PrintWriter(sw));

                    resultManager.writeResult(workerId + " ERROR on task " + task.getId() +
                            ": " + e.getMessage());
                }
            }

            String summary = String.format("%s finished - Processed: %d tasks, Errors: %d",
                    workerId, tasksProcessed, errorCount);
            System.out.println(summary);
            resultManager.writeResult(summary);

        } catch (InterruptedException e) {
            System.err.println(workerId + " was interrupted: " + e.getMessage());
            Thread.currentThread().interrupt();
        } finally {
            completionLatch.countDown();
        }
    }

    /**
     * Processes a task and returns a result.
     * 
     * @param task the task to process
     * @return the processing result
     * @throws Exception if processing fails
     */
    private String processTask(Task task) throws Exception {
        // Simulate processing time based on task complexity
        int processingTime = task.getComplexity() * 200;
        Thread.sleep(processingTime);

        // Simulate random processing errors (10% chance)
        if (ThreadLocalRandom.current().nextInt(10) == 0) {
            throw new Exception("Simulated processing error");
        }

        return "Result: " + task.getData() + " transformed";
    }

    /**
     * Gets the worker's identifier.
     * 
     * @return the worker ID
     */
    public String getWorkerId() {
        return workerId;
    }

    /**
     * Gets the number of tasks processed by this worker.
     * 
     * @return task count
     */
    public int getTasksProcessed() {
        return tasksProcessed;
    }

    /**
     * Gets the number of errors encountered by this worker.
     * 
     * @return error count
     */
    public int getErrorCount() {
        return errorCount;
    }
}
