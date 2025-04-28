import java.util.Scanner;

public class BullyAlgorithm {
    static boolean[] processes;
    static int coordinator = -1;
    static int n = 0;

    // Function to start the election
    static void election(int id) {
        if (id < 0 || id >= n || !processes[id]) {
            System.out.println("Election cannot be started by P" + id + " because it is DOWN or invalid.");
            return;
        } else {
            System.out.println("P" + id + " started election");
            boolean higherProcess = false;
            // Search for higher numbered processes that are UP
            for (int i = id + 1; i < n; i++) {
                if (processes[i]) {
                    System.out.println("P" + id + " sends ELECTION message to P" + i);
                    higherProcess = true;
                    System.out.println("P" + i + " responds with OK to P" + id);
                    election(i); // Recursive call
                    return;
                }
            }
            // If no higher numbered process is found, this process becomes the coordinator
            if (!higherProcess) {
                coordinator = id;
                System.out.println("P" + id + " becomes the new coordinator.");
                // Inform all other processes that this process is the new coordinator
                for (int i = 0; i < n; i++) {
                    if (i != id && processes[i]) {
                        System.out.println("P" + id + " sends COORDINATOR message to P" + i);
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int state;

        while (true) {
            System.out.println("\n======= MENU =======");
            System.out.println("1. Create Processes");
            System.out.println("2. Display Status");
            System.out.println("3. DOWN a Process");
            System.out.println("4. UP a Process");
            System.out.println("5. START Election (Select process)");
            System.out.println("6. EXIT");
            System.out.print("Enter Choice: ");
            int choice = sc.nextInt();

            switch (choice) {
                case 1: {
                    System.out.println("Enter the number of processes:");
                    n = sc.nextInt();
                    processes = new boolean[n];
                    for (int i = 0; i < n; i++) {
                        processes[i] = true; // All processes are UP initially
                    }
                    coordinator = n - 1; // The highest numbered process is the initial coordinator
                    System.out.println(n + " processes created.");
                    System.out.println("Current Coordinator is: P" + coordinator);
                    break;
                }
                case 2: {
                    if (n <= 0) {
                        System.out.println("Currently there are no processes.");
                    } else {
                        for (int i = 0; i < n; i++) {
                            System.out.println("P" + i + " is " + (processes[i] ? "UP" : "DOWN"));
                        }
                        if (coordinator == -1) {
                            System.out.println("Currently there is NO Coordinator selected.");
                        } else {
                            System.out.println("Current Coordinator is P" + coordinator);
                        }
                    }
                    break;
                }
                case 3: {
                    if (n == 0) {
                        System.out.println("Currently there are no processes.");
                    } else {
                        System.out.print("Enter process number (0 to " + (n - 1) + "): ");
                        state = sc.nextInt();
                        if (state >= 0 && state < n) {
                            if (processes[state]) {
                                processes[state] = false;
                                System.out.println("P" + state + " is brought DOWN.");
                                if (state == coordinator) {
                                    System.out.println("P" + state + " was the Coordinator.");
                                    System.out.println("Starting Election to select new Coordinator...");
                                    // Automatically start election from the lowest UP process
                                    int newStarter = -1;
                                    for (int i = 0; i < n; i++) {
                                        if (processes[i]) {
                                            newStarter = i;
                                            break;
                                        }
                                    }
                                    if (newStarter != -1) {
                                        election(newStarter); // Start election from the lowest UP process
                                    } else {
                                        System.out.println("No processes are UP to start an election!");
                                        coordinator = -1; // No coordinator available
                                    }
                                }
                            } else {
                                System.out.println("P" + state + " is already DOWN.");
                            }
                        } else {
                            System.out.println("Invalid process number.");
                        }
                    }
                    break;
                }
                case 4: {
                    if (n == 0) {
                        System.out.println("Currently there are no processes.");
                    } else {
                        System.out.print("Enter process number (0 to " + (n - 1) + "): ");
                        state = sc.nextInt();
                        if (state >= 0 && state < n) {
                            if (!processes[state]) {
                                processes[state] = true;
                                System.out.println("P" + state + " is UP now.");
                                if (state > coordinator || coordinator == -1) {
                                    election(state); // Start election if this process is higher than the current coordinator
                                }
                            } else {
                                System.out.println("Process is already UP.");
                            }
                        } else {
                            System.out.println("Invalid process number.");
                        }
                    }
                    break;
                }
                case 5: {
                    // Manually start election from a chosen UP process
                    System.out.print("Enter process number to start election (0 to " + (n - 1) + "): ");
                    int starter = sc.nextInt();
                    if (starter >= 0 && starter < n) {
                        if (processes[starter]) {
                            election(starter);
                        } else {
                            System.out.println("Cannot start election! Process P" + starter + " is DOWN.");
                        }
                    } else {
                        System.out.println("Invalid process number.");
                    }
                    break;
                }
                case 6: {
                    System.out.println("Exiting the Program...");
                    sc.close();
                    return;
                }
                default: {
                    System.out.println("Invalid Input!");
                }
            }
        }
    }
}
