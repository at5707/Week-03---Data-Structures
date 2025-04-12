import java.util.*;

class Process {
    int pid;
    int burstTime;
    int remainingTime;
    int priority;
    int waitingTime = 0;
    int turnaroundTime = 0;
    Process next;

    public Process(int pid, int burstTime, int priority) {
        this.pid = pid;
        this.burstTime = burstTime;
        this.remainingTime = burstTime;
        this.priority = priority;
        this.next = null;
    }
}

public class RoundRobinScheduler {
    private Process head = null;
    public void addProcess(int pid, int burstTime, int priority) {
        Process newProcess = new Process(pid, burstTime, priority);
        if (head == null) {
            head = newProcess;
            newProcess.next = head;
        } else {
            Process temp = head;
            while (temp.next != head) {
                temp = temp.next;
            }
            temp.next = newProcess;
            newProcess.next = head;
        }
    }
    private void removeProcess(int pid) {
        if (head == null) return;

        Process curr = head, prev = null;
        if (head.pid == pid) {
            if (head.next == head) {
                head = null;
                return;
            }
            Process temp = head;
            while (temp.next != head)
                temp = temp.next;
            temp.next = head.next;
            head = head.next;
            return;
        }
        do {
            prev = curr;
            curr = curr.next;
        } while (curr != head && curr.pid != pid);

        if (curr.pid == pid) {
            prev.next = curr.next;
        }
    }
    public void displayQueue() {
        if (head == null) {
            System.out.println("No processes in queue.");
            return;
        }

        System.out.println("Current Process Queue:");
        Process temp = head;
        do {
            System.out.println("PID: " + temp.pid + ", Remaining: " + temp.remainingTime + ", Priority: " + temp.priority);
            temp = temp.next;
        } while (temp != head);
    }
    public void simulate(int quantum) {
        if (head == null) {
            System.out.println("No processes to execute.");
            return;
        }

        int time = 0;
        Map<Integer, Integer> completionTimes = new HashMap<>();
        Map<Integer, Integer> startTimes = new HashMap<>();

        Process current = head;

        while (head != null) {
            if (!startTimes.containsKey(current.pid)) {
                startTimes.put(current.pid, time);
            }

            if (current.remainingTime > quantum) {
                System.out.println("Executing PID: " + current.pid + " for " + quantum + " units");
                time += quantum;
                current.remainingTime -= quantum;
                current = current.next;
            } else {
                System.out.println("Executing PID: " + current.pid + " for " + current.remainingTime + " units (Finishing)");
                time += current.remainingTime;
                completionTimes.put(current.pid, time);
                current.remainingTime = 0;
                int pidToRemove = current.pid;
                current = current.next;
                removeProcess(pidToRemove);
            }

            displayQueue();
        }
        System.out.println("\nProcess Execution Summary:");
        double totalTAT = 0, totalWT = 0;

        for (Map.Entry<Integer, Integer> entry : completionTimes.entrySet()) {
            int pid = entry.getKey();
            int finishTime = entry.getValue();
            int startTime = startTimes.get(pid);
            int burstTime = finishTime - startTime;
            int waitingTime = burstTime - (finishTime - startTime);

            int turnaroundTime = finishTime - startTime;
            waitingTime = turnaroundTime - burstTime;

            System.out.println("PID: " + pid + " | Turnaround Time: " + turnaroundTime + " | Waiting Time: " + waitingTime);

            totalTAT += turnaroundTime;
            totalWT += waitingTime;
        }

        int n = completionTimes.size();
        System.out.printf("Average Turnaround Time: %.2f\n", totalTAT / n);
        System.out.printf("Average Waiting Time: %.2f\n", totalWT / n);
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        RoundRobinScheduler scheduler = new RoundRobinScheduler();

        int choice;
        do {
            System.out.println("\n--- Round Robin Scheduler ---");
            System.out.println("1. Add Process");
            System.out.println("2. Display Process Queue");
            System.out.println("3. Run Simulation");
            System.out.println("4. Exit");
            System.out.print("Choose: ");
            choice = sc.nextInt();

            int pid, burst, prio, quantum;

            switch (choice) {
                case 1:
                    System.out.print("Enter Process ID: ");
                    pid = sc.nextInt();
                    System.out.print("Enter Burst Time: ");
                    burst = sc.nextInt();
                    System.out.print("Enter Priority: ");
                    prio = sc.nextInt();
                    scheduler.addProcess(pid, burst, prio);
                    break;

                case 2:
                    scheduler.displayQueue();
                    break;

                case 3:
                    System.out.print("Enter Time Quantum: ");
                    quantum = sc.nextInt();
                    scheduler.simulate(quantum);
                    break;

                case 4:
                    System.out.println("Exiting...");
                    break;

                default:
                    System.out.println("Invalid choice.");
            }
        } while (choice != 4);

        sc.close();
    }
}