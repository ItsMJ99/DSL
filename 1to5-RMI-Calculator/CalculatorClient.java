import java.rmi.Naming; // For looking up the remote object
import java.util.Scanner; // For user input

public class CalculatorClient {
    public static void main(String[] args) {
        // Check if server IP and port are provided
        if (args.length < 2) {
            System.out.println("Usage: java CalculatorClient <server_ip> <port>");
            System.exit(1); // Exit if not enough arguments
        }

        try {
            // Read server IP and port from command-line arguments
            String serverIP = args[0];
            int serverPort = Integer.parseInt(args[1]);

            // Build the RMI URL to access the remote Calculator service
            String rmiURL = "rmi://" + serverIP + ":" + serverPort + "/CalculatorService";

            // Look up the remote object (connect to server)
            Calculator calc = (Calculator) Naming.lookup(rmiURL);

            // Create a Scanner object for taking user input
            Scanner sc = new Scanner(System.in);

            while (true) {
                // Display the calculator menu
                System.out.println("\n=== Calculator Menu ===");
                System.out.println("1. Add");
                System.out.println("2. Subtract");
                System.out.println("3. Multiply");
                System.out.println("4. Divide");
                System.out.println("5. Power");
                System.out.println("6. Exit");
                System.out.print("Choose operation: ");
                int choice = sc.nextInt(); // Read user's choice

                // If user chooses to exit
                if (choice == 6) {
                    System.out.println("Exiting...");
                    break; // Exit the loop
                }

                // Ask user for two numbers
                System.out.print("Enter first number: ");
                double a = sc.nextDouble();
                System.out.print("Enter second number: ");
                double b = sc.nextDouble();

                double result = 0;

                // Call the appropriate remote method based on user's choice
                switch (choice) {
                    case 1:
                        result = calc.add(a, b); // Remote method call
                        break;
                    case 2:
                        result = calc.subtract(a, b); // Remote method call
                        break;
                    case 3:
                        result = calc.multiply(a, b); // Remote method call
                        break;
                    case 4:
                        result = calc.divide(a, b); // Remote method call
                        break;
                    case 5:
                        result = calc.power(a, b); // Remote method call
                        break;
                    default:
                        System.out.println("Invalid choice.");
                        continue; // Skip to next loop iteration
                }

                // Display the result received from server
                System.out.println("Result: " + result);
            }

            // Close the scanner when done
            sc.close();
        } catch (Exception e) {
            // Handle any exceptions (like server not found, etc.)
            System.out.println("Client Exception: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
