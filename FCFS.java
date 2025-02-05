import java.util.Scanner;

class Process {
    int processId, burstTime, arrivalTime, waitingTime, turnaroundTime;
    Process next;

    public Process(int processId, int burstTime, int arrivalTime) {
        this.processId = processId;
        this.burstTime = burstTime;
        this.arrivalTime = arrivalTime;
        this.next = null;
    }
}

class FCFScalculate {
    Process head;

    public void addProcess(int processId, int burstTime, int arrivalTime) {
        Process newProcess = new Process(processId, burstTime, arrivalTime);
        if (head == null) {
            head = newProcess;
        } else {
            Process temp = head;
            while (temp.next != null) {
                temp = temp.next;
            }
            temp.next = newProcess;
        }
    }

    public void calculateTimes() {
        Process temp = head;
        int currentTime = 0;
        int totalWaitingTime = 0, totalTurnaroundTime = 0;
        int count = 0;

        System.out.println("Process ID  Burst Time  Waiting Time  TurnAround Time  Arrival");
        while (temp != null) {
            temp.waitingTime = Math.max(0, currentTime - temp.arrivalTime);
            temp.turnaroundTime = temp.waitingTime + temp.burstTime;
            currentTime += temp.burstTime;

            totalWaitingTime += temp.waitingTime;
            totalTurnaroundTime += temp.turnaroundTime;
            count++;

            System.out.printf("%-11d %-10d %-12d %-15d %d%n",
                    temp.processId, temp.burstTime, temp.waitingTime, temp.turnaroundTime, temp.arrivalTime);

            temp = temp.next;
        }

        System.out.printf("Avg. waiting time= %.6f%n", (double) totalWaitingTime / count);
        System.out.printf("Avg. turnaround time= %.6f%n", (double) totalTurnaroundTime / count);
    }
}

public class FCFS {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        FCFScalculate fcfs = new FCFScalculate();

        System.out.println("Enter the number of processes: ");
        int n = sc.nextInt();

        System.out.println("Enter process id of all the processes: ");
        int[] processIds = new int[n];
        for (int i = 0; i < n; i++) {
            processIds[i] = sc.nextInt();
        }

        System.out.println("Enter burst time of all the processes: ");
        int[] burstTimes = new int[n];
        for (int i = 0; i < n; i++) {
            burstTimes[i] = sc.nextInt();
        }

        System.out.println("Enter arrival time of all the processes: ");
        int[] arrivalTimes = new int[n];
        for (int i = 0; i < n; i++) {
            arrivalTimes[i] = sc.nextInt();
        }

        for (int i = 0; i < n; i++) {
            fcfs.addProcess(processIds[i], burstTimes[i], arrivalTimes[i]);
        }

        fcfs.calculateTimes();
        sc.close();
    }
}
