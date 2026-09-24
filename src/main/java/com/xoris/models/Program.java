package com.xoris.models;

import com.xoris.enums.Priority;
import com.xoris.enums.TaskCategory;
import com.xoris.interfaces.ProgramInterface;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Program implements ProgramInterface {
  private static List<Task> tasks = new ArrayList<>();

  public Program() {}

  public void setTasks(List<Task> tasks) {
    Program.tasks = tasks;
  }

  @Override
  public Task createTask(
      String shortDescription,
      String longDescription,
      TaskCategory category,
      Priority priority,
      LocalDate due_at) {
    return new Task(shortDescription, longDescription, category, priority, due_at);
  }

  @Override
  public List<Task> getAllTasks() {
    return tasks;
  }

  @Override
  public List<Task> getTasksByCategory(TaskCategory category) {
    return tasks.stream().filter(task -> task.getCategory().equals(category)).toList();
  }

  @Override
  public List<Task> getTasksByPeriod(LocalDate start, LocalDate end) {
    return tasks.stream()
        .filter(task -> task.getDue_at().isBefore(end) && task.getDue_at().isAfter(start))
        .toList();
  }

  @Override
  public List<Task> getTasksByPriority(Priority priority) {
    return tasks.stream().filter(task -> task.getPriority().equals(priority)).toList();
  }

  @Override
  public List<Task> getTasksByWord(String word) {
    return tasks.stream().filter(task -> task.getShortDescription().contains(word)).toList();
  }

  @Override
  public void deleteTask(Task task) {
    tasks.remove(task);
  }

  @Override
  public void markTaskAsInProgress(Task task) {
    tasks.stream().filter(t -> t.equals(task)).findFirst().ifPresent(Task::markAsInProgress);
  }

  @Override
  public void markTaskAsCompleted(Task task) {
    tasks.stream().filter(t -> t.equals(task)).findFirst().ifPresent(Task::markAsCompleted);
  }
}
