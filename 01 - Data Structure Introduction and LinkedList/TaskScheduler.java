import java.util.Scanner;

class Task {
    int taskId;
    String taskName;
    int priority;
    String dueDate;
    Task next;

    Task(int taskId, String taskName, int priority, String dueDate) {
        this.taskId = taskId;
        this.taskName = taskName;
        this.priority = priority;
        this.dueDate = dueDate;
        this.next = null;
    }
}

public class TaskScheduler {
    private Task head = null;
    private Task current = null;

    // Add at beginning
    public void addAtBeginning(int id, String name, int priority, String dueDate) {
        Task newTask = new Task(id, name, priority, dueDate);
        if (head == null) {
            head = newTask;
            newTask.next = head;
            current = head;
        } else {
            Task temp = head;
            while (temp.next != head)
                temp = temp.next;

            newTask.next = head;
            temp.next = newTask;
            head = newTask;
        }
        System.out.println("Task added at beginning.");
    }

    // Add at end
    public void addAtEnd(int id, String name, int priority, String dueDate) {
        Task newTask = new Task(id, name, priority, dueDate);
        if (head == null) {
            head = newTask;
            newTask.next = head;
            current = head;
        } else {
            Task temp = head;
            while (temp.next != head)
                temp = temp.next;

            temp.next = newTask;
            newTask.next = head;
        }
        System.out.println("Task added at end.");
    }

    // Add at specific position
    public void addAtPosition(int position, int id, String name, int priority, String dueDate) {
        if (position <= 1 || head == null) {
            addAtBeginning(id, name, priority, dueDate);
            return;
        }

        Task newTask = new Task(id, name, priority, dueDate);
        Task temp = head;
        int index = 1;

        while (index < position - 1 && temp.next != head) {
            temp = temp.next;
            index++;
        }

        newTask.next = temp.next;
        temp.next = newTask;
        System.out.println("Task added at position " + position + ".");
    }

    // Remove by Task ID
    public void removeById(int id) {
        if (head == null) {
            System.out.println("Task list is empty.");
            return;
        }

        Task temp = head, prev = null;

        // Only one task
        if (head.next == head && head.taskId == id) {
            head = null;
            current = null;
            System.out.println("Task removed.");
            return;
        }

        do {
            if (temp.taskId == id) {
                if (temp == head) {
                    // Find last node
                    Task last = head;
                    while (last.next != head)
                        last = last.next;
                    head = head.next;
                    last.next = head;
                } else {
                    prev.next = temp.next;
                }

                // Update current pointer if needed
                if (current == temp) {
                    current = current.next;
                }

                System.out.println("Task with ID " + id + " removed.");
                return;
            }

            prev = temp;
            temp = temp.next;
        } while (temp != head);

        System.out.println("Task not found.");
    }

    // View current task and move to next
    public void viewAndMoveNext() {
        if (current == null) {
            System.out.println("No tasks available.");
            return;
        }

        System.out.println("\n--- Current Task ---");
        displayTask(current);
        current = current.next;
    }

    // Display all tasks
    public void displayAll() {
        if (head == null) {
            System.out.println("No tasks to display.");
            return;
        }

        Task temp = head;
        System.out.println("\n--- Task List ---");
        do {
            displayTask(temp);
            temp = temp.next;
        } while (temp != head);
    }

    // Search by priority
    public void searchByPriority(int priority) {
        if (head == null) {
            System.out.println("No tasks to search.");
            return;
        }

        Task temp = head;
        boolean found = false;

        do {
            if (temp.priority == priority) {
                displayTask(temp);
                found = true;
            }
            temp = temp.next;
        } while (temp != head);

        if (!found)
            System.out.println("No tasks found with priority " + priority);
    }

    // Helper to display a task
    private void displayTask(Task t) {
        System.out.println("Task ID   : " + t.taskId);
        System.out.println("Task Name : " + t.taskName);
        System.out.println("Priority  : " + t.priority);
        System.out.println("Due Date  : " + t.dueDate);
        System.out.println("-----------");
    }

    // Main
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        TaskScheduler scheduler = new TaskScheduler();
        int choice;

        do {
            System.out.println("\n--- Task Scheduler ---");
            System.out.println("1. Add Task at Beginning");
            System.out.println("2. Add Task at End");
            System.out.println("3. Add Task at Position");
            System.out.println("4. Remove Task by ID");
            System.out.println("5. View Current Task and Move Next");
            System.out.println("6. Display All Tasks");
            System.out.println("7. Search by Priority");
            System.out.println("8. Exit");
            System.out.print("Enter your choice: ");

            while (!sc.hasNextInt()) {
                System.out.print("Please enter a valid number: ");
                sc.next();
            }

            choice = sc.nextInt();
            sc.nextLine(); // Clear buffer

            switch (choice) {
                case 1:
                case 2:
                case 3:
                    System.out.print("Enter Task ID: ");
                    int id = sc.nextInt(); sc.nextLine();
                    System.out.print("Enter Task Name: ");
                    String name = sc.nextLine();
                    System.out.print("Enter Priority: ");
                    int priority = sc.nextInt(); sc.nextLine();
                    System.out.print("Enter Due Date: ");
                    String date = sc.nextLine();
                    if (choice == 1) scheduler.addAtBeginning(id, name, priority, date);
                    else if (choice == 2) scheduler.addAtEnd(id, name, priority, date);
                    else {
                        System.out.print("Enter Position: ");
                        int pos = sc.nextInt(); sc.nextLine();
                        scheduler.addAtPosition(pos, id, name, priority, date);
                    }
                    break;
                case 4:
                    System.out.print("Enter Task ID to remove: ");
                    int removeId = sc.nextInt(); sc.nextLine();
                    scheduler.removeById(removeId);
                    break;
                case 5:
                    scheduler.viewAndMoveNext();
                    break;
                case 6:
                    scheduler.displayAll();
                    break;
                case 7:
                    System.out.print("Enter Priority to search: ");
                    int searchPriority = sc.nextInt(); sc.nextLine();
                    scheduler.searchByPriority(searchPriority);
                    break;
                case 8:
                    System.out.println("Exiting Task Scheduler.");
                    break;
                default:
                    System.out.println("Invalid choice.");
            }

        } while (choice != 8);

        sc.close();
    }
}
