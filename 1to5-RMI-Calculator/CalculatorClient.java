import java.rmi.Naming;
import java.util.Scanner;

public class CalculatorClient {
    public static void main(String[] args) {
        if (args.length < 2) {
            System.out.println("Usage: java CalculatorClient <server_ip> <port>");
            System.exit(1);
        }

        try {
            String serverIP = args[0];
            int serverPort = Integer.parseInt(args[1]);

            String rmiURL = "rmi://" + serverIP + ":" + serverPort + "/CalculatorService";
            Calculator calc = (Calculator) Naming.lookup(rmiURL);

            Scanner sc = new Scanner(System.in);

            while (true) {
                System.out.println("\n=== Calculator Menu ===");
                System.out.println("1. Add");
                System.out.println("2. Subtract");
                System.out.println("3. Multiply");
                System.out.println("4. Divide");
                System.out.println("5. Power");
                System.out.println("6. Exit");
                System.out.print("Choose operation: ");
                int choice = sc.nextInt();

                if (choice == 6) {
                    System.out.println("Exiting...");
                    break;
                }

                System.out.print("Enter first number: ");
                double a = sc.nextDouble();
                System.out.print("Enter second number: ");
                double b = sc.nextDouble();

                double result = 0;
                switch (choice) {
                    case 1:
                        result = calc.add(a, b);
                        break;
                    case 2:
                        result = calc.subtract(a, b);
                        break;
                    case 3:
                        result = calc.multiply(a, b);
                        break;
                    case 4:
                        result = calc.divide(a, b);
                        break;
                    case 5:
                        result = calc.power(a, b);
                        break;
                    default:
                        System.out.println("Invalid choice.");
                        continue;
                }

                System.out.println("Result: " + result);
            }

            sc.close();
        } catch (Exception e) {
            System.out.println("Client Exception: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
