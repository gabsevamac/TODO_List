package models;

import com.xoris.enums.Priority;
import com.xoris.enums.TaskCategory;
import com.xoris.models.Task;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class TaskTests {

    @DisplayName("No Args Creation Test")
    @Test
    void shouldCreateTaskWithoutArgs() {
        Task task = new Task();
        assertNotNull(task);
    }

    @DisplayName("With Args Creation Test")
    @Test
    void shouldCreateTaskWithArgs() {
        Task task = new Task(
                "New Test Task", "This is a test task", TaskCategory.OTHER, Priority.CRITICAL, LocalDate.parse("2026-09-25")
        );

        assertNotNull(task);
    }
}
