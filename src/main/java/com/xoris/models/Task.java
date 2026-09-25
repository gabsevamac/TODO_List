package com.xoris.models;

import com.xoris.enums.Priority;
import com.xoris.enums.TaskCategory;
import com.xoris.enums.TaskStatus;
import java.time.LocalDate;

public class Task {
  private String shortDescription;
  private String longDescription;
  private TaskStatus status;
  private TaskCategory category;
  private Priority priority;
  private LocalDate created_at;
  private LocalDate updated_at;
  private LocalDate due_at;

  public Task() {}

  public Task(
      String shortDescription,
      String longDescription,
      TaskCategory category,
      Priority priority,
      LocalDate due_at) {
    this.shortDescription = shortDescription;
    this.longDescription = longDescription;
    this.category = category;
    this.priority = priority;
    this.created_at = LocalDate.now();
    this.updated_at = LocalDate.now();
    this.due_at = due_at;
    this.status = TaskStatus.NOT_STARTED;
  }

  public void markAsInProgress() {
    this.status = TaskStatus.IN_PROGRESS;
    updated_at = LocalDate.now();
  }

  public void markAsCompleted() {
    this.status = TaskStatus.COMPLETED;
    updated_at = LocalDate.now();
  }

  public void updateShortDescription(String shortDescription) {
    if (shortDescription == null || shortDescription.isBlank()) {
      throw new IllegalArgumentException("Short Description cannot be null or blank");
    }
    this.shortDescription = shortDescription;
    this.updated_at = LocalDate.now();
  }

  public TaskStatus getStatus() {
    return status;
  }

  public String getShortDescription() {
    return shortDescription;
  }

  public String getLongDescription() {
    return longDescription;
  }

  public void updateLongDescription(String longDescription) {
    if (longDescription == null || longDescription.isBlank()) {
      throw new IllegalArgumentException("Long Description cannot be null or blank");
    }
    this.longDescription = longDescription;
    this.updated_at = LocalDate.now();
  }

  public TaskCategory getCategory() {
    return category;
  }

  public void updateCategory(TaskCategory category) {
    if (category == null) {
      throw new IllegalArgumentException("Category cannot be null");
    }
    this.category = category;
    this.updated_at = LocalDate.now();
  }

  public Priority getPriority() {
    return priority;
  }

  public void updatePriority(Priority priority) {
    if (priority == null) {
      throw new IllegalArgumentException("Priority cannot be null");
    }
    this.priority = priority;
    this.updated_at = LocalDate.now();
  }

  public LocalDate getCreated_at() {
    return created_at;
  }

  public LocalDate getUpdated_at() {
    return updated_at;
  }

  public LocalDate getDueDate() {
    return due_at;
  }

  public void updateDueDate(LocalDate due_at) {
    if (due_at == null) {
      throw new IllegalArgumentException("Due Date cannot be null");
    }
    if (due_at.isBefore(LocalDate.now())) {
      throw new IllegalArgumentException("Due Date cannot be before current date");
    }
    this.due_at = due_at;
    this.updated_at = LocalDate.now();
  }

  @Override
  public String toString() {
    return "Short Description: "
        + getShortDescription()
        + "\n"
        + "Long Description: "
        + getLongDescription()
        + "\n"
        + "Status: "
        + getStatus()
        + "\n"
        + "Category: "
        + getCategory()
        + "\n"
        + "Due Date: "
        + getDueDate()
        + "\n"
        + "Created At: "
        + getCreated_at()
        + "\n"
        + "Updated At: "
        + getUpdated_at();
  }
}
