package com.xoris.interfaces;

import com.xoris.enums.TaskCategory;
import com.xoris.models.Task;

import java.time.LocalDate;
import java.util.List;

public interface ProgramInterface {
    Task createTask(String shortDescription, String longDescription, TaskCategory category, int priority, LocalDate due_at);

    List<Task> getAllTasks();

    List<Task> getTasksByCategory(TaskCategory category);
}
