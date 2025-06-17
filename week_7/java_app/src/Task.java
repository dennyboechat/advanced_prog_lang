/**
 * Task - Represents a data item that needs to be processed.
 */
public class Task {
    // Special task to signal worker threads to terminate
    public static final Task POISON_PILL = new Task(-1, "POISON", 0);
    
    private final int id;
    private final String data;
    private final int complexity; // Affects processing time
    
    /**
     * Creates a new task with the specified parameters.
     * 
     * @param id unique identifier for the task
     * @param data the data that needs processing
     * @param complexity indicates processing difficulty (higher means longer time)
     */
    public Task(int id, String data, int complexity) {
        this.id = id;
        this.data = data;
        this.complexity = complexity;
    }
    
    /**
     * Gets the task's ID.
     * 
     * @return task ID
     */
    public int getId() {
        return id;
    }
    
    /**
     * Gets the task's data.
     * 
     * @return data to process
     */
    public String getData() {
        return data;
    }
    
    /**
     * Gets the task's complexity.
     * 
     * @return complexity level
     */
    public int getComplexity() {
        return complexity;
    }
    
    @Override
    public String toString() {
        return "Task{id=" + id + ", data='" + data + "', complexity=" + complexity + "}";
    }
}
