package com.xoris.enums;

public enum TaskStatus {
    NOT_STARTED("Not Started"),
    IN_PROGRESS("In Progress"),
    COMPLETED("Completed");

    private String description;

    TaskStatus(String description) {
        this.description = description;
    }
}
