import java.util.*;

public class Main {
    public static void main(String[] args) {
        List<Process> processes = new ArrayList<>();
        processes.add(new Process("P1", 10, 4));
        processes.add(new Process("P2", 13, 1));
        processes.add(new Process("P3", 7, 3));
        processes.add(new Process("P4", 15, 2));
        processes.add(new Process("P5", 6, 3));
        processes.add(new Process("P6", 10, 4));
        processes.sort((p1, p2) -> {
            if (p1.priority != p2.priority)
                return p1.priority - p2.priority; // Since Priority: Lower value has higher priority
            else if (p1.burstTime != p2.burstTime) return p1.burstTime - p2.burstTime; // SJF: Lower burst time first
            else return processes.indexOf(p1) - processes.indexOf(p2); // Doing FCFS
        });

        int currentTime = 0;
        for (Process p : processes) {
            p.waitingTime = currentTime;
            currentTime += p.burstTime;
            p.turnaroundTime = p.waitingTime + p.burstTime;
        }

        System.out.println("Process\tBurst Time\tPriority\tWaiting Time\tTurnaround Time");
        for (Process p : processes) {
            System.out.println(p.name + "\t\t\t" + p.burstTime + "\t\t\t" + p.priority + "\t\t\t" + p.waitingTime + "\t\t\t\t" + p.turnaroundTime);
        }
    }
}

class Process {
    String name;
    int burstTime;
    int priority;
    int waitingTime;
    int turnaroundTime;

    public Process(String name, int burstTime, int priority) {
        this.name = name;
        this.burstTime = burstTime;
        this.priority = priority;
    }
}
