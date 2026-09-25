package com.xoris.interfaces;

import com.xoris.enums.Priority;
import com.xoris.enums.TaskCategory;
import com.xoris.models.Task;
import java.time.LocalDate;
import java.util.List;

public interface ProgramInterface {
  void createTask(
      String shortDescription,
      String longDescription,
      TaskCategory category,
      Priority priority,
      LocalDate due_at);

  List<Task> getAllTasks();

  List<Task> getTasksByCategory(TaskCategory category);

  List<Task> getTasksByPeriod(LocalDate start, LocalDate end);

  List<Task> getTasksByPriority(Priority priority);

  List<Task> getTasksByWord(String word);

  void deleteTask(Task task);

  void markTaskAsInProgress(Task task);

  void markTaskAsCompleted(Task task);
}
