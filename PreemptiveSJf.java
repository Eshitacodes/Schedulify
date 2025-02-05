import java.util.*;
 

    class Process {
        int pid, arrivalTime, burstTime, remainingTime, completionTime, waitingTime, turnaroundTime;
        Process next;
    
        public Process(int pid, int arrivalTime, int burstTime) {
            this.pid = pid;
            this.arrivalTime = arrivalTime;
            this.burstTime = burstTime;
            this.remainingTime = burstTime;
            this.next = null;
        }
    }
    
    class SJFPreemptive {
        List<Process> processList = new ArrayList<>();
    
        public void addProcess(int pid, int arrivalTime, int burstTime) {
            processList.add(new Process(pid, arrivalTime, burstTime));
        }
    
        public void executeProcesses() {
            int currentTime = 0, completed = 0;
            int n = processList.size();
            double totalWaitingTime = 0, totalTurnaroundTime = 0;
            Process executingProcess = null;
    
            while (completed < n) {
                Process shortest = null;
                for (Process p : processList) {
                    if (p.arrivalTime <= currentTime && p.remainingTime > 0) {
                        if (shortest == null || p.remainingTime < shortest.remainingTime) {
                            shortest = p;
                        }
                    }
                }
    
                if (shortest == null) {
                    currentTime++; // No process available, increment time
                    continue;
                }
    
                shortest.remainingTime--;
                if (shortest.remainingTime == 0) {
                    completed++;
                    shortest.completionTime = currentTime + 1;
                    shortest.turnaroundTime = shortest.completionTime - shortest.arrivalTime;
                    shortest.waitingTime = shortest.turnaroundTime - shortest.burstTime;
                    totalWaitingTime += shortest.waitingTime;
                    totalTurnaroundTime += shortest.turnaroundTime;
                }
                currentTime++;
            }
    
            // Print results
            System.out.println("\nProcess ID  Arrival Time  Burst Time  Waiting Time  Turnaround Time");
            for (Process p : processList) {
                System.out.printf("%-11d %-13d %-10d %-12d %d%n", p.pid, p.arrivalTime, p.burstTime, p.waitingTime, p.turnaroundTime);
            }
    
            System.out.printf("Avg. waiting time = %.6f%n", totalWaitingTime / n);
            System.out.printf("Avg. turnaround time = %.6f%n", totalTurnaroundTime / n);
        }
    }
    
    public class PreemptiveSJf {
        public static void main(String[] args) {
            Scanner sc = new Scanner(System.in);
            SJFPreemptive scheduler = new SJFPreemptive();
    
            System.out.print("Enter the number of processes: ");
            int n = sc.nextInt();
    
            System.out.println("Enter process ID, arrival time, and burst time for each process:");
            for (int i = 0; i < n; i++) {
                int pid = sc.nextInt();
                int arrivalTime = sc.nextInt();
                int burstTime = sc.nextInt();
                scheduler.addProcess(pid, arrivalTime, burstTime);
            }
    
            scheduler.executeProcesses();
            sc.close();
        }
    }
    
    

