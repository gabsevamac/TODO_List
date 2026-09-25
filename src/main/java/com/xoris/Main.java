package com.xoris;

import com.xoris.enums.Priority;
import com.xoris.enums.TaskCategory;
import com.xoris.models.Program;
import com.xoris.models.Task;
import java.time.LocalDate;
import java.util.List;

public class Main {
  private static final Program program = new Program();
  private static boolean isRunning = true;

  static void main(String[] args) {
    clearConsole();
    while (isRunning) {
      callMenu();
    }
  }

  static void callMenu() {
    IO.println("=== TO DO LIST ===");
    IO.println("1 - Create Task");
    IO.println("2 - Find Task");
    IO.println("3 - Exit Program");

    Integer choose = Integer.parseInt(IO.readln("Choose an option: "));

    switch (choose) {
      case 1 -> callCreationMenu();
      case 2 -> callSearchMenu();
      case 3 -> isRunning = false;
    }
  }

  static void callCreationMenu() {
    clearConsole();
    IO.println("=== CREATING NEW TASK ===");
    String shortDescription = IO.readln("Enter short description: ");
    clearConsole();
    String longDescription = IO.readln("Enter long description: ");
    clearConsole();

    IO.println("=== CHOOSE THE CATEGORY ===");
    IO.println("1 - STUDY");
    IO.println("2 - WORK");
    IO.println("3 - HOME");
    IO.println("4 - OTHER");

    var choose = Integer.parseInt(IO.readln("Choose an option: "));
    TaskCategory category = null;
    switch (choose) {
      case 1 -> category = TaskCategory.STUDY;
      case 2 -> category = TaskCategory.WORK;
      case 3 -> category = TaskCategory.HOME;
      case 4 -> category = TaskCategory.OTHER;
    }
    clearConsole();
    IO.println("=== CHOOSE THE PRIORITY ===");
    IO.println("1 - LOW");
    IO.println("2 - MEDIUM");
    IO.println("3 - HIGH");
    IO.println("4 - CRITICAL");

    choose = Integer.parseInt(IO.readln("Choose an option: "));
    clearConsole();
    Priority priority = null;
    switch (choose) {
      case 1 -> priority = Priority.LOW;
      case 2 -> priority = Priority.MEDIUM;
      case 3 -> priority = Priority.HIGH;
      case 4 -> priority = Priority.CRITICAL;
    }

    LocalDate dueDate = LocalDate.parse(IO.readln("Enter due date (YYYY-MM-DD): "));
    program.createTask(shortDescription, longDescription, category, priority, dueDate);
    clearConsole();
    IO.println("TASK CREATED SUCCESSFULLY");
  }

  static void callSearchMenu() {
    clearConsole();
    IO.println("=== CHOOSE THE SEARCH WAY ===");
    IO.println("1 - SEARCH BY PRIORITY");
    IO.println("2 - SEARCH BY CATEGORY");
    IO.println("3 - SEARCH BY WORD");
    IO.println("4 - SEARCH BY PERIOD");
    IO.println("5 - SEARCH ALL");

    var choose = Integer.parseInt(IO.readln("Choose an option: "));
    clearConsole();
    List<Task> tasks = null;
    switch (choose) {
      case 1 -> tasks = getTaskByPriorityMenu();
      case 2 -> tasks = getTaskByCategoryMenu();
      case 3 -> tasks = getTaskByWordMenu();
      case 4 -> tasks = getTaskByPeriodMenu();
      case 5 -> tasks = program.getAllTasks();
    }
    clearConsole();
    if (tasks == null || tasks.isEmpty()) {
      IO.println("NO TASK FOUND");
    } else {
      IO.println("=== TASKS FOUNDED ===");
      for (int i = 0; i < tasks.size(); i++) {
        IO.println("=========");
        IO.println(i + 1 + "°:\n" + tasks.get(i));
        IO.println("=========");
      }
    }

    callModificationMenu(tasks);
  }

  public static List<Task> getTaskByPriorityMenu() {
    clearConsole();
    IO.println("=== CHOOSE THE PRIORITY ===");
    IO.println("1 - LOW");
    IO.println("2 - MEDIUM");
    IO.println("3 - HIGH");
    IO.println("4 - CRITICAL");

    var choose = Integer.parseInt(IO.readln("Choose an option: "));
    switch (choose) {
      case 1:
        return program.getTasksByPriority(Priority.LOW);
      case 2:
        program.getTasksByPriority(Priority.MEDIUM);
      case 3:
        program.getTasksByPriority(Priority.HIGH);
      case 4:
        program.getTasksByPriority(Priority.CRITICAL);
      default:
        clearConsole();
        IO.println("ERROR: INVALID CHOOSE PRIORITY");
        return null;
    }
  }

  public static List<Task> getTaskByCategoryMenu() {
    IO.println("=== CHOOSE THE CATEGORY ===");
    IO.println("1 - STUDY");
    IO.println("2 - WORK");
    IO.println("3 - HOME");
    IO.println("4 - OTHER");

    var choose = Integer.parseInt(IO.readln("Choose an option: "));

    switch (choose) {
      case 1:
        return program.getTasksByCategory(TaskCategory.STUDY);
      case 2:
        return program.getTasksByCategory(TaskCategory.WORK);
      case 3:
        return program.getTasksByCategory(TaskCategory.HOME);
      case 4:
        return program.getTasksByCategory(TaskCategory.OTHER);
      default:
        clearConsole();
        IO.println("ERROR: INVALID CHOOSE CATEGORY");
        return null;
    }
  }

  public static List<Task> getTaskByWordMenu() {
    String word = IO.readln("DIGIT THE WORD TO SEARCH: ");
    if (word.contains(" ")) {
      clearConsole();
      throw new RuntimeException("ERROR: INVALID WORD");
    }
    return program.getTasksByWord(word);
  }

  public static List<Task> getTaskByPeriodMenu() {
    LocalDate start = LocalDate.parse(IO.readln("START DATE (YYYY-MM-DD): "));
    LocalDate end = LocalDate.parse(IO.readln("END DATE (YYYY-MM-DD): "));

    return program.getTasksByPeriod(start, end);
  }

  public static void callModificationMenu(List<Task> tasks) {
    IO.println("=== WHAT WOULD YOU LIKE TO DO? ===");
    IO.println("1 - MARK AS IN PROGRESS");
    IO.println("2 - MARK AS COMPLETED");
    IO.println("3 - MODIFY DETAILS");

    int choose = Integer.parseInt(IO.readln("Choose an option: "));
    int chosenTask = Integer.parseInt(IO.readln("Choose a task by number: "));
    Task task = tasks.get(chosenTask);
    switch (choose) {
      case 1 -> {
        program.markTaskAsInProgress(task);
      }
      case 2 -> {
        program.markTaskAsCompleted(task);
      }

      case 3 -> {
        callDetailsModificationMenu(task);
      }
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

    int choose = Integer.parseInt(IO.readln("Choose an option: "));
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
        var c = Integer.parseInt(IO.readln("Choose an option: "));

        switch (c) {
          case 1 -> program.updatePriority(task, Priority.LOW);
          case 2 -> program.updatePriority(task, Priority.MEDIUM);
          case 3 -> program.updatePriority(task, Priority.HIGH);
          case 4 -> program.updatePriority(task, Priority.CRITICAL);
        }
      }

      case 4 -> {
        IO.println("=== CHOOSE THE CATEGORY ===");
        IO.println("1 - STUDY");
        IO.println("2 - WORK");
        IO.println("3 - HOME");
        IO.println("4 - OTHER");
        var c = Integer.parseInt(IO.readln("Choose an option: "));

        switch (c) {
          case 1 -> program.updateCategory(task, TaskCategory.STUDY);
          case 2 -> program.updateCategory(task, TaskCategory.WORK);
          case 3 -> program.updateCategory(task, TaskCategory.HOME);
          case 4 -> program.updateCategory(task, TaskCategory.OTHER);
        }
      }
      case 5 -> {
        var newDueDate = LocalDate.parse(IO.readln("Choose new due date (YYYY-MM-DD): "));
        program.updateDueAt(task, newDueDate);
      }
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
      e.printStackTrace();
    }
  }
}
