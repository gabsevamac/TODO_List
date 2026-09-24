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


    public Task() {
    }

    public Task(String shortDescription, String longDescription, TaskCategory category, Priority priority, LocalDate due_at) {
        this.shortDescription = shortDescription;
        this.longDescription = longDescription;
        this.category = category;
        this.priority = priority;
        this.created_at = LocalDate.now();
        this.updated_at = LocalDate.now();
        this.due_at = due_at;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public String getLongDescription() {
        return longDescription;
    }

    public void setLongDescription(String longDescription) {
        this.longDescription = longDescription;
    }

    public TaskCategory getCategory() {
        return category;
    }

    public void setCategory(TaskCategory category) {
        this.category = category;
    }

    public Priority getPriority() {
        return priority;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    public LocalDate getCreated_at() {
        return created_at;
    }

    public void setCreated_at(LocalDate created_at) {
        this.created_at = created_at;
    }

    public LocalDate getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(LocalDate updated_at) {
        this.updated_at = updated_at;
    }

    public LocalDate getDue_at() {
        return due_at;
    }

    public void setDue_at(LocalDate due_at) {
        this.due_at = due_at;
    }
}
