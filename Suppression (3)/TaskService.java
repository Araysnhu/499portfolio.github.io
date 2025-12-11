import java.util.HashMap;
import java.util.Map;

public class TaskService {
    private Map<String, Task> tasks = new HashMap<>();

    public void addTask(Task task) {
        if (tasks.containsKey(task.getTaskId())) {
            throw new IllegalArgumentException("No duplicate IDs allowed. Please try again.");
        }
        tasks.put(task.getTaskId(), task);
    }

    public void deleteTask(String taskId) {
        if (!tasks.containsKey(taskId)) {
            throw new IllegalArgumentException("ID could not be found. Please ensure you have the correct input and try again.");
        }
        tasks.remove(taskId);
    }

    public void updateTaskName(String taskId, String name) {
        Task task = tasks.get(taskId);
        if (task == null) {
            throw new IllegalArgumentException("ID could not be found. Please ensure you have the correct input and try again.");
        }
        task.setName(name);
    }

    public void updateTaskDescription(String taskId, String description) {
        Task task = tasks.get(taskId);
        if (task == null) {
            throw new IllegalArgumentException("ID could not be found. Please ensure you have the correct input and try again.");
        }
        task.setDescription(description);
    }

    public Task getTask(String taskId) {
        return tasks.get(taskId);
    }
}