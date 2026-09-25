package com.xoris;

import com.xoris.enums.Priority;
import com.xoris.enums.TaskCategory;
import com.xoris.models.Program;
import com.xoris.models.Task;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;

public class Main {
    private static final Program program = new Program();
    private static boolean isRunning = true;

    static void main(String[] args) {
        clearConsole();
        while (isRunning) {
            clearConsole();
            try {
                callMenu();
            } catch (Exception e) {
                System.out.println("Unexpected error occurred: " + e.getMessage());
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException ie) {
                    Thread.currentThread().interrupt();
                }
            }
        }
    }

    private static int readInt(String prompt) {
        while (true) {
            try {
                return Integer.parseInt(IO.readln(prompt));
            } catch (NumberFormatException e) {
                IO.println("Invalid input! Please enter a valid number.");
            }
        }
    }

    private static LocalDate readDate(String prompt) {
        while (true) {
            try {
                return LocalDate.parse(IO.readln(prompt));
            } catch (DateTimeParseException e) {
                IO.println("Invalid date format! Please use YYYY-MM-DD.");
            }
        }
    }

    static void callMenu() throws InterruptedException {
        IO.println("=== TO DO LIST ===");
        IO.println("1 - Create Task");
        IO.println("2 - Find Task");
        IO.println("3 - Exit Program");

        int choose = readInt("Choose an option: ");
        try {
            switch (choose) {
                case 1 -> callCreationMenu();
                case 2 -> callSearchMenu();
                case 3 -> isRunning = false;
                default -> throw new IllegalStateException("Invalid option selected");
            }
        } catch (Exception e) {
            IO.println("Error: " + e.getMessage());
            Thread.sleep(1000);
            IO.println("RESETTING PROGRAM...");
            Thread.sleep(2000);
            clearConsole();
        }
    }

    static void callCreationMenu() {
        clearConsole();
        IO.println("=== CREATING NEW TASK ===");
        String shortDescription = IO.readln("Enter short description: ");
        if (shortDescription == null || shortDescription.trim().isEmpty()) {
            throw new IllegalArgumentException("Description cannot be empty");
        }
        clearConsole();
        String longDescription = IO.readln("Enter long description: ");
        clearConsole();

        IO.println("=== CHOOSE THE CATEGORY ===");
        IO.println("1 - STUDY");
        IO.println("2 - WORK");
        IO.println("3 - HOME");
        IO.println("4 - OTHER");

        int choose = readInt("Choose an option: ");
        TaskCategory category = switch (choose) {
            case 1 -> TaskCategory.STUDY;
            case 2 -> TaskCategory.WORK;
            case 3 -> TaskCategory.HOME;
            case 4 -> TaskCategory.OTHER;
            default -> throw new IllegalStateException("Invalid category option");
        };
        
        clearConsole();
        IO.println("=== CHOOSE THE PRIORITY ===");
        IO.println("1 - LOW");
        IO.println("2 - MEDIUM");
        IO.println("3 - HIGH");
        IO.println("4 - CRITICAL");

        choose = readInt("Choose an option: ");
        clearConsole();
        Priority priority = switch (choose) {
            case 1 -> Priority.LOW;
            case 2 -> Priority.MEDIUM;
            case 3 -> Priority.HIGH;
            case 4 -> Priority.CRITICAL;
            default -> throw new IllegalStateException("Invalid priority option");
        };

        LocalDate dueDate = readDate("Enter due date (YYYY-MM-DD): ");
        program.createTask(shortDescription, longDescription, category, priority, dueDate);
        clearConsole();
        IO.println("TASK CREATED SUCCESSFULLY");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    static void callSearchMenu() {
        clearConsole();
        IO.println("=== CHOOSE THE SEARCH WAY ===");
        IO.println("1 - SEARCH BY PRIORITY");
        IO.println("2 - SEARCH BY CATEGORY");
        IO.println("3 - SEARCH BY WORD");
        IO.println("4 - SEARCH BY PERIOD");
        IO.println("5 - SEARCH ALL");

        int choose = readInt("Choose an option: ");
        clearConsole();
        List<Task> tasks;
        switch (choose) {
            case 1 -> tasks = getTaskByPriorityMenu();
            case 2 -> tasks = getTaskByCategoryMenu();
            case 3 -> tasks = getTaskByWordMenu();
            case 4 -> tasks = getTaskByPeriodMenu();
            case 5 -> tasks = program.getAllTasks();
            default -> throw new IllegalStateException("Invalid option");
        }
        
        clearConsole();
        if (tasks == null || tasks.isEmpty()) {
            IO.println("NO TASK FOUND");
            try {
                Thread.sleep(1500);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        } else {
            IO.println("=== TASKS FOUND ===");
            for (int i = 0; i < tasks.size(); i++) {
                IO.println("=========");
                IO.println((i + 1) + "°:\n" + tasks.get(i));
                IO.println("=========");
            }
            callModificationMenu(tasks);
        }
    }

    public static List<Task> getTaskByPriorityMenu() {
        clearConsole();
        IO.println("=== CHOOSE THE PRIORITY ===");
        IO.println("1 - LOW");
        IO.println("2 - MEDIUM");
        IO.println("3 - HIGH");
        IO.println("4 - CRITICAL");

        int choose = readInt("Choose an option: ");
        return switch (choose) {
            case 1 -> program.getTasksByPriority(Priority.LOW);
            case 2 -> program.getTasksByPriority(Priority.MEDIUM);
            case 3 -> program.getTasksByPriority(Priority.HIGH);
            case 4 -> program.getTasksByPriority(Priority.CRITICAL);
            default -> throw new IllegalStateException("Invalid option");
        };
    }

    public static List<Task> getTaskByCategoryMenu() {
        IO.println("=== CHOOSE THE CATEGORY ===");
        IO.println("1 - STUDY");
        IO.println("2 - WORK");
        IO.println("3 - HOME");
        IO.println("4 - OTHER");

        int choose = readInt("Choose an option: ");
        return switch (choose) {
            case 1 -> program.getTasksByCategory(TaskCategory.STUDY);
            case 2 -> program.getTasksByCategory(TaskCategory.WORK);
            case 3 -> program.getTasksByCategory(TaskCategory.HOME);
            case 4 -> program.getTasksByCategory(TaskCategory.OTHER);
            default -> throw new IllegalStateException("Invalid option");
        };
    }

    public static List<Task> getTaskByWordMenu() {
        String word = IO.readln("DIGIT THE WORD TO SEARCH: ");
        if (word == null || word.trim().isEmpty() || word.contains(" ")) {
            clearConsole();
            throw new IllegalArgumentException("ERROR: INVALID WORD. Please enter a single word.");
        }
        return program.getTasksByWord(word);
    }

    public static List<Task> getTaskByPeriodMenu() {
        LocalDate start = readDate("START DATE (YYYY-MM-DD): ");
        LocalDate end = readDate("END DATE (YYYY-MM-DD): ");
        
        if (start.isAfter(end)) {
            throw new IllegalArgumentException("Start date cannot be after end date.");
        }

        return program.getTasksByPeriod(start, end);
    }

    public static void callModificationMenu(List<Task> tasks) {
        IO.println("=== WHAT WOULD YOU LIKE TO DO? ===");
        IO.println("1 - MARK AS IN PROGRESS");
        IO.println("2 - MARK AS COMPLETED");
        IO.println("3 - MODIFY DETAILS");
        IO.println("4 - CANCEL / BACK");

        int choose = readInt("Choose an option: ");
        if (choose == 4) return;

        int chosenTaskIndex = readInt("Choose a task by number: ") - 1;
        
        if (chosenTaskIndex < 0 || chosenTaskIndex >= tasks.size()) {
            throw new IndexOutOfBoundsException("Invalid task number selected.");
        }
        
        Task task = tasks.get(chosenTaskIndex);
        switch (choose) {
            case 1 -> program.markTaskAsInProgress(task);
            case 2 -> program.markTaskAsCompleted(task);
            case 3 -> callDetailsModificationMenu(task);
            default -> throw new IllegalStateException("Invalid option");
        }
    }

    public static void callDetailsModificationMenu(Task task) {
        clearConsole();
        IO.println("=== CHOOSE THE MODIFICATION TYPE");
        IO.println("1 - CHANGE SHORT DESCRIPTION");
        IO.println("2 - CHANGE LONG DESCRIPTION");
        IO.println("3 - CHANGE PRIORITY");
        IO.println("4 - CHANGE CATEGORY");
        IO.println("5 - CHANGE DUE DATE");

        int choose = readInt("Choose an option: ");
        clearConsole();
        switch (choose) {
            case 1 -> {
                String newDescription = IO.readln("New Short Description: ");
                program.updateShortDescription(task, newDescription);
            }
            case 2 -> {
                String newDescription = IO.readln("New Long Description: ");
                program.updateLongDescription(task, newDescription);
            }
            case 3 -> {
                IO.println("=== CHOOSE THE PRIORITY ===");
                IO.println("1 - LOW");
                IO.println("2 - MEDIUM");
                IO.println("3 - HIGH");
                IO.println("4 - CRITICAL");
                int c = readInt("Choose an option: ");

                switch (c) {
                    case 1 -> program.updatePriority(task, Priority.LOW);
                    case 2 -> program.updatePriority(task, Priority.MEDIUM);
                    case 3 -> program.updatePriority(task, Priority.HIGH);
                    case 4 -> program.updatePriority(task, Priority.CRITICAL);
                    default -> throw new IllegalStateException("Invalid option");
                }
            }
            case 4 -> {
                IO.println("=== CHOOSE THE CATEGORY ===");
                IO.println("1 - STUDY");
                IO.println("2 - WORK");
                IO.println("3 - HOME");
                IO.println("4 - OTHER");
                int c = readInt("Choose an option: ");

                switch (c) {
                    case 1 -> program.updateCategory(task, TaskCategory.STUDY);
                    case 2 -> program.updateCategory(task, TaskCategory.WORK);
                    case 3 -> program.updateCategory(task, TaskCategory.HOME);
                    case 4 -> program.updateCategory(task, TaskCategory.OTHER);
                    default -> throw new IllegalStateException("Invalid option");
                }
            }
            case 5 -> {
                var newDueDate = readDate("Choose new due date (YYYY-MM-DD): ");
                program.updateDueAt(task, newDueDate);
            }
            default -> throw new IllegalStateException("Invalid option");
        }
    }

    public static void clearConsole() {
        try {
            if (System.getProperty("os.name").contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                new ProcessBuilder("clear").inheritIO().start().waitFor();
            }
        } catch (Exception e) {
            // Fallback or log error if the platform doesn't support the command
            System.out.println(); // Just print a new line if clearing fails
        }
    }
}
