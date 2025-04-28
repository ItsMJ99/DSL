import java.util.*;

public class TokenRing {
    int max_processes, coordinator;
    boolean[] processes;

    TokenRing(int max) {
        max_processes = max;
        processes = new boolean[max];

        for (int i = 0; i < max; i++) {
            processes[i] = true;
            System.out.println("P" + i + " created and UP");
        }
        coordinator = max - 1;
        System.out.println("Coordinator is initially P" + coordinator);
    }

    void display() {
        System.out.println("\nProcess Status:");
        for (int i = 0; i < max_processes; i++) {
            System.out.println("P" + i + " is " + (processes[i] ? "UP" : "DOWN"));
        }
        if (coordinator >= 0 && coordinator < max_processes && processes[coordinator]) {
            System.out.println("Current Coordinator is P" + coordinator);
        } else {
            System.out.println("No valid coordinator currently.");
        }
    }

    void downProcess(int process_id) {
        if (process_id < 0 || process_id >= max_processes) {
            System.out.println("Invalid Process ID!");
            return;
        }

        if (!processes[process_id]) {
            System.out.println("Process P" + process_id + " is already DOWN");
        } else {
            processes[process_id] = false;
            System.out.println("Process P" + process_id + " is brought DOWN");
        }
    }

    void upProcess(int process_id) {
        if (process_id < 0 || process_id >= max_processes) {
            System.out.println("Invalid Process ID!");
            return;
        }

        if (processes[process_id]) {
            System.out.println("Process P" + process_id + " is already UP");
        } else {
            processes[process_id] = true;
            System.out.println("Process P" + process_id + " is brought UP");
        }
    }

    void election(int starter_id) {
        if (starter_id < 0 || starter_id >= max_processes) {
            System.out.println("Invalid Process ID!");
            return;
        }

        if (!processes[starter_id]) {
            System.out.println("Process P" + starter_id + " is DOWN. Cannot initiate election.");
            return;
        }

        System.out.println("Election initiated by P" + starter_id);

        int current = starter_id;
        int highest = starter_id;
        List<Integer> electionList = new ArrayList<>(); // List to store participating process IDs
        electionList.add(starter_id);

        do {
            int next = (current + 1) % max_processes;

            // Find the next active process
            while (!processes[next]) {
                System.out.println("P" + next + " is DOWN. Skipping...");
                next = (next + 1) % max_processes;
                if (next == starter_id) break;  // full loop
            }

            if (next != starter_id) {
                System.out.println("P" + current + " passes the token to P" + next);
                electionList.add(next); // add process ID to list
                if (next > highest) {
                    highest = next;
                }
            }

            current = next;
        } while (current != starter_id);

        System.out.println("\nProcesses participated in election: " + electionList);
        coordinator = highest;
        System.out.println("New Coordinator elected: P" + coordinator);
    }

    public static void main(String[] args) {
        TokenRing ring = null;
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\n======= MENU =======");
            System.out.println("1. Create Processes");
            System.out.println("2. Display Processes");
            System.out.println("3. Down a Process");
            System.out.println("4. Up a Process");
            System.out.println("5. Start Election");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");
            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter number of processes: ");
                    int n = sc.nextInt();
                    ring = new TokenRing(n);
                    break;
                case 2:
                    if (ring != null)
                        ring.display();
                    else
                        System.out.println("Please create processes first.");
                    break;
                case 3:
                    if (ring != null) {
                        System.out.print("Enter process ID to bring DOWN: ");
                        int downId = sc.nextInt();
                        ring.downProcess(downId);
                    } else {
                        System.out.println("Please create processes first.");
                    }
                    break;
                case 4:
                    if (ring != null) {
                        System.out.print("Enter process ID to bring UP: ");
                        int upId = sc.nextInt();
                        ring.upProcess(upId);
                    } else {
                        System.out.println("Please create processes first.");
                    }
                    break;
                case 5:
                    if (ring != null) {
                        System.out.print("Enter process ID to start election: ");
                        int starter = sc.nextInt();
                        ring.election(starter);
                    } else {
                        System.out.println("Please create processes first.");
                    }
                    break;
                case 6:
                    System.out.println("Exiting Program...");
                    sc.close();
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice!");
            }
        }
    }
}
