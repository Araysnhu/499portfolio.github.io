import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class TaskService {
    // ENHANCEMENT 1: Use ConcurrentHashMap for thread safety
    private final Map<String, Task> tasks = new ConcurrentHashMap<>();

    public void addTask(Task task) {
        if (task == null || task.getTaskId() == null) {
            throw new IllegalArgumentException("Invalid task data.");
        }

        // ENHANCEMENT 2: Atomic Operation
        // prevents race conditions where two threads try to add the same ID simultaneously.
        if (tasks.putIfAbsent(task.getTaskId(), task) != null) {
            throw new IllegalArgumentException("No duplicate IDs allowed. Please try again.");
        }
    }

    public void deleteTask(String taskId) {
        // ENHANCEMENT 3: Robust checking
        // Check if ID exists and remove it in one step if possible, 
        // though strictly we need to know if it existed to throw the exception.
        if (taskId == null || tasks.remove(taskId) == null) {
             throw new IllegalArgumentException("ID could not be found. Please ensure you have the correct input and try again.");
        }
    }

    public void updateTaskName(String taskId, String name) {
        Task task = getTaskOrThrow(taskId);
        task.setName(name);
    }

    public void updateTaskDescription(String taskId, String description) {
        Task task = getTaskOrThrow(taskId);
        task.setDescription(description);
    }

    public Task getTask(String taskId) {
        return tasks.get(taskId);
    }

    // ENHANCEMENT 4: Helper method to reduce code duplication
    private Task getTaskOrThrow(String taskId) {
        Task task = tasks.get(taskId);
        if (task == null) {
            throw new IllegalArgumentException("ID could not be found. Please ensure you have the correct input and try again.");
        }
        return task;
    }
}