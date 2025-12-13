import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class TaskTest {

    @Test
    public void testTaskCreation() {
        Task task = new Task("1", "Basketball", "Shoot some hoops.");
        assertEquals("1", task.getTaskId());
        assertEquals("Basketball", task.getName());
        assertEquals("Shoot some hoops.", task.getDescription());
    }

    @Test
    public void testTaskNameUpdate() {
        Task task = new Task("1", "Basketball", "Shoot some hoops.");
        task.setName("Baseball");
        assertEquals("Baseball", task.getName());
    }

    @Test
    public void testTaskDescriptionUpdate() {
        Task task = new Task("1", "Basketball", "Shoot some hoops.");
        task.setDescription("Lets play ball");
        assertEquals("Lets play ball", task.getDescription());
    }

    @Test
    public void testTaskInvalidId() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Task(null, "Basketball", "Shoot some hoops.");
        });
        assertThrows(IllegalArgumentException.class, () -> {
            new Task("12345678901", "Basketball", "Shoot some hoops.");
        });
    }

    @Test
    public void testTaskInvalidName() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Task("1", null, "Shoot some hoops.");
        });
        assertThrows(IllegalArgumentException.class, () -> {
            new Task("1", "Chosen name exceeds 20 character limit!", "Shoot some hoops..");
        });
    }

    @Test
    public void testTaskInvalidDescription() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Task("1", "Basketball", null);
        });
        assertThrows(IllegalArgumentException.class, () -> {
            new Task("1", "Basketball", "Chosen name exceeds character 50 limit!");
        });
    }
}