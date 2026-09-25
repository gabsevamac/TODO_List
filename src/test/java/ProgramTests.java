import com.xoris.enums.Priority;
import com.xoris.enums.TaskCategory;
import com.xoris.enums.TaskStatus;
import com.xoris.models.Program;
import com.xoris.models.Task;
import org.junit.jupiter.api.BeforeEach;
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
        @BeforeEach()
        void setup() {
            program = new Program();
            program.setTasks(
                    List.of(
                            new Task(
                                    "Test 1 - World Building",
                                    "New Test 1",
                                    TaskCategory.STUDY,
                                    Priority.CRITICAL,
                                    LocalDate.parse("2026-09-30")),
                            new Task(
                                    "Test 2 - World Structures",
                                    "New Test 2",
                                    TaskCategory.HOME,
                                    Priority.LOW,
                                    LocalDate.parse("2026-09-29")),
                            new Task(
                                    "Test 3 - Other Testing",
                                    "New Test 3",
                                    TaskCategory.OTHER,
                                    Priority.MEDIUM,
                                    LocalDate.parse("2026-10-30")),
                            new Task(
                                    "Test 4 - Random Bullshit",
                                    "New Test 4",
                                    TaskCategory.WORK,
                                    Priority.HIGH,
                                    LocalDate.parse("2026-10-03"))));
        }

        @DisplayName("Get all tasks")
        @Test
        void shouldReturnAllTasks() {
            List<Task> tasks = program.getAllTasks();
            assertAll(
                    () -> {
                        assertNotNull(tasks);
                        assertFalse(tasks.isEmpty());
                    });
        }

        @DisplayName("Get all High Priority")
        @Test
        void shouldReturnHighestPriority() {
            List<Task> tasks = program.getTasksByPriority(Priority.HIGH);
            boolean check =
                    !tasks.isEmpty()
                            && tasks.stream().allMatch(task -> task.getPriority().equals(Priority.HIGH));

            assertTrue(check);
        }

        @DisplayName("Get all Home category")
        @Test
        void shouldReturnHomeCategory() {
            List<Task> tasks = program.getTasksByCategory(TaskCategory.HOME);
            boolean check =
                    !tasks.isEmpty()
                            && tasks.stream().allMatch(task -> task.getCategory().equals(TaskCategory.HOME));
            assertTrue(check);
        }

        @DisplayName("Get all with 'World' on shortDescription")
        @Test
        void shouldReturnAllWithWorldOnShortDescription() {
            List<Task> tasks = program.getTasksByWord("World");
            boolean check =
                    !tasks.isEmpty()
                            && tasks.stream()
                            .allMatch(
                                    task ->
                                            task.getShortDescription().toLowerCase().contains("World".toLowerCase()));

            assertTrue(check);
        }
    }

    @Nested
    class ModificationTests {
        @BeforeEach()
        void setup() {
            program = new Program();
            program.setTasks(
                    List.of(
                            new Task(
                                    "Test 1 - World Building",
                                    "New Test 1",
                                    TaskCategory.STUDY,
                                    Priority.CRITICAL,
                                    LocalDate.parse("2026-09-30")),
                            new Task(
                                    "Test 2 - World Structures",
                                    "New Test 2",
                                    TaskCategory.HOME,
                                    Priority.LOW,
                                    LocalDate.parse("2026-09-29")),
                            new Task(
                                    "Test 3 - Other Testing",
                                    "New Test 3",
                                    TaskCategory.OTHER,
                                    Priority.MEDIUM,
                                    LocalDate.parse("2026-10-30")),
                            new Task(
                                    "Test 4 - Random Bullshit",
                                    "New Test 4",
                                    TaskCategory.WORK,
                                    Priority.HIGH,
                                    LocalDate.parse("2026-10-03"))));
        }

        @DisplayName("Mark Task As In Progress")
        @Test
        void shouldMarkTaskAsInProgress() {
            Task task = program.getAllTasks().getFirst();
            program.markTaskAsInProgress(task);
            boolean check = task.getStatus().equals(TaskStatus.IN_PROGRESS);

            assertTrue(check);
        }

        @DisplayName("Mark Task As Completed")
        @Test
        void shouldMarkTaskAsCompleted() {
            Task task = program.getAllTasks().getFirst();
            program.markTaskAsCompleted(task);
            boolean check = task.getStatus().equals(TaskStatus.COMPLETED);

            assertTrue(check);
        }

        @DisplayName("Update Short Description")
        @Test
        void shouldUpdateShortDescription() {
            Task task = program.getAllTasks().getFirst();
            String newDescription = "New Test 1";
            program.updateShortDescription(task, newDescription);

            assertEquals(newDescription, task.getShortDescription());
        }

        @DisplayName("Update Long Description")
        @Test
        void shouldUpdateLongDescription() {
            Task task = program.getAllTasks().getFirst();
            String newDescription = "New Test 1 long Description";
            program.updateLongDescription(task, newDescription);

            assertEquals(newDescription, task.getLongDescription());
        }

        @DisplayName("Update Priority")
        @Test
        void shouldUpdatePriority() {
            Task task = program.getAllTasks().getFirst();
            Priority newPriority = Priority.MEDIUM;

            program.updatePriority(task, newPriority);
            assertEquals(newPriority, task.getPriority());
        }

        @DisplayName("Update Category")
        @Test
        void shouldUpdateCategory() {
            Task task = program.getAllTasks().getFirst();
            TaskCategory newCategory = TaskCategory.OTHER;
            program.updateCategory(task, newCategory);
            assertEquals(newCategory, task.getCategory());
        }

        @DisplayName("Update Due Date")
        @Test
        void shouldUpdateDueDate() {
            Task task = program.getAllTasks().getFirst();
            LocalDate newDueDate = LocalDate.parse("2027-01-01");
            program.updateDueAt(task, newDueDate);
            assertEquals(newDueDate, task.getDueDate());
        }
    }
}
