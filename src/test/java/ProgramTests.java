import static org.junit.jupiter.api.Assertions.*;

import com.xoris.enums.Priority;
import com.xoris.enums.TaskCategory;
import com.xoris.models.Program;
import com.xoris.models.Task;
import java.time.LocalDate;
import java.util.List;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

public class ProgramTests {
  private static Program program;

  @Nested
  class SearchTests {
    @BeforeAll()
    static void setup() {
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
}
