import java.util.Scanner;
import java.rmi.Naming;

class OperationsClient {
    public static void main(String[] args) {
        if (args.length < 2) {
            System.out.println("Invalid number of args : java OperationsClient <server_ip> <server_port>");
            System.exit(1);
        }

        try {
            String serverIP = args[0];
            int serverPort = Integer.parseInt(args[1]);

            String rmiURL = "rmi://" + serverIP + ":" + serverPort + "/OperationsService";
            Operations operate = (Operations) Naming.lookup(rmiURL);

            Scanner sc = new Scanner(System.in);

            while (true) {
                System.out.println("\n=== Operations Menu ===");
                System.out.println("1. Greet");
                System.out.println("2. Larger String");
                System.out.println("3. Count Vowels in a word");
                System.out.println("4. Factorial");
                System.out.println("5. Exit");
                System.out.print("Choose operation: ");
                int choice = sc.nextInt();
                sc.nextLine();

                switch (choice) {
                    case 1: { 
                        System.out.print("Enter your name: ");
                        String name = sc.nextLine();
                        String greeting = operate.Greet(name);
                        System.out.println("Server says: " + greeting);
                        break;
                    }
                    case 2: { 
                        System.out.print("Enter first string: ");
                        String str1 = sc.nextLine();
                        System.out.print("Enter second string: ");
                        String str2 = sc.nextLine();
                        String larger = operate.LargeString(str1, str2);
                        System.out.println("Lexicographically larger string: " + larger);
                        break;
                    }
                    case 3: { 
                        System.out.print("Enter a word: ");
                        String word = sc.nextLine();
                        int vowelCount = operate.CountVowels(word);
                        System.out.println("Number of vowels: " + vowelCount);
                        break;
                    }
                    case 4: { 
                        System.out.print("Enter a number: ");
                        int num = sc.nextInt();
                        int factorial = operate.Factorial(num);
                        System.out.println("Factorial: " + factorial);
                        break;
                    }
                    case 5: { 
                        System.out.println("Exiting...");
                        sc.close();
                        System.exit(0);
                        break;
                    }
                    default: {
                        System.out.println("Invalid Input! Please try again.");
                        break;
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("Client Exception: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
