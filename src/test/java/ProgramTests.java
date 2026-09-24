import com.xoris.enums.Priority;
import com.xoris.enums.TaskCategory;
import com.xoris.models.Program;
import com.xoris.models.Task;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ProgramTests {
    private static Program program;

    @Nested
    class SearchTests {
        @BeforeAll()
        static void setup() {
            program = new Program();
            program.setTasks(
                    List.of(
                            new Task("Test 1", "New Test 1", TaskCategory.STUDY, Priority.CRITICAL, LocalDate.parse("2026-09-30")),
                            new Task("Test 2", "New Test 2", TaskCategory.HOME, Priority.LOW, LocalDate.parse("2026-09-29")),
                            new Task("Test 3", "New Test 3", TaskCategory.OTHER, Priority.MEDIUM, LocalDate.parse("2026-10-30")),
                            new Task("Test 4", "New Test 4", TaskCategory.WORK, Priority.HIGH, LocalDate.parse("2026-10-03"))
                    )
            );
        }

        @DisplayName("Get All tasks")
        @Test
        void shouldReturnAllTasks() {
            List<Task> tasks = program.getAllTasks();
            assertAll(
                    () -> {
                        assertNotNull(tasks);
                        assertFalse(tasks.isEmpty());
                    }
            );
        }

        @Test
        void shouldReturnHighestPriority() {
            List<Task> tasks = program.getTasksByPriority(Priority.HIGH);
            boolean check = !tasks.isEmpty() && tasks.stream()
                    .allMatch(task -> task.getPriority().equals(Priority.HIGH));
            
            assertTrue(check);
        }

        @Test
        void shouldReturnHomeCategory() {
            List<Task> tasks = program.getTasksByCategory(TaskCategory.HOME);
            boolean check = !tasks.isEmpty() && tasks.stream().allMatch(task -> task.getCategory().equals(TaskCategory.HOME));
            assertTrue(check);
        }
    }
}
